package mahmoudehabmoheb.osproject.shedulers;

import java.util.ArrayList;
import mahmoudehabmoheb.osproject.Process;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import javafx.application.Platform;

/**
 * SJF Scheduler
 * Supports Preemptive (SRTF) and Non-Preemptive scheduling without modifying the original Process class.
 */
public class SJF extends Scheduler<PriorityQueue<Process>>
{
    private boolean isPreemptive;

    public SJF() // Defaults to non-preemptive scheduling.
    {
        this(false);
    }
    
    public SJF(boolean isPreemptive)
    {
        super();
        this.isPreemptive = isPreemptive;
        
        // Sorting using Mahmoud's getter methods
        this.readyQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::get_remainingBurstTime).thenComparingInt(Process::get_id)
        );
    }
    
    public SJF(SJF original)
    {
        super(original);
        this.isPreemptive = original.isPreemptive;
        
        this.readyQueue = new PriorityQueue<>(original.readyQueue.comparator());
        
        for (Process p : original.readyQueue)
        {
            this.readyQueue.add(new Process(p));
        }
    }

    @Override
    public void add_Process(Process p)
    {
        p.set_arrivalTime(this.currentTime.get());
        this.readyQueue.add(p);
        set_observableReadyQueue();
    }
    
    @Override
    public void set_observableReadyQueue()
    {
        List<Process> sortedList = new ArrayList<>(this.readyQueue);

        sortedList.sort(Comparator.comparingInt(Process::get_remainingBurstTime));
        
        Platform.runLater(() -> this.observableReadyQueue.setAll(sortedList));
    }

    @Override
    public void schedule()
    {
        while (this.isRunning)
        {
            if (this.currentProcess.get() != null || !this.readyQueue.isEmpty())
            {
                try 
                {
                    tick();

                    if (this.isDynamic)
                    {
                        Thread.sleep(1000);
                    }

                    increment_currentTime();
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            else
            {
                if (this.isDynamic)
                {
                    try
                    {
                        Thread.sleep(1000);
                        increment_currentTime();
                    }
                    catch (InterruptedException ex)
                    {
                        System.out.println(ex.getMessage());
                    }
                }
                else
                {
                    continue;
                }
            }
        }
    }

 public Process tick()
    {
        // 1. Execute current process
        if (this.currentProcess.get() != null)
        {
            this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);

            if (this.currentProcess.get().get_remainingBurstTime() == 0) 
            {
                this.currentProcess.get().set_finishedTime(this.currentTime.get());
                this.completedProcesses.add(this.currentProcess.get());
                this.calculate_averageWaitingTime();
                this.calculate_averageTurnAroundTime();
                set_currentProcess(null); // Free CPU
            }
        }

        // 2. Preemption Check (SRTF)
        if (this.isPreemptive && this.currentProcess.get() != null && !this.readyQueue.isEmpty())
        {
            if (this.readyQueue.peek().get_remainingBurstTime() < this.currentProcess.get().get_remainingBurstTime())
            {
                this.readyQueue.add(this.currentProcess.get());
                set_currentProcess(null);
                set_observableReadyQueue(); // Update UI: Process returned to queue
            }
        }

        // 3. Load next shortest process
        if (this.currentProcess.get() == null && !this.readyQueue.isEmpty())
        {
            set_currentProcess(this.readyQueue.poll());
            set_observableReadyQueue(); // Update UI: Process removed from queue
        }

        return this.currentProcess.get();
    }
 
    public void set_preemptive(boolean p)
    {
        this.isPreemptive = p;
    }
    
    public boolean is_preemptive()
    {
        return this.isPreemptive;
    }
}