package mahmoudehabmoheb.osproject.shedulers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import javafx.application.Platform;
import mahmoudehabmoheb.osproject.Process;

/**
 * Priority scheduler implementation.
 * Lower integer priority means higher scheduling priority.
 */
public class Priority extends Scheduler<PriorityQueue<Process>> {
    private boolean isPreemptive;

    public Priority()
    {
        this(false); // Default to non-preemptive
    }

    public Priority(boolean isPreemptive)
    {
        super();
        this.isPreemptive = isPreemptive;
        this.readyQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::get_priority)
                        .thenComparingInt(Process::get_arrivalTime)
                        .thenComparingInt(Process::get_id));
    }

    @Override
    public void add_Process(Process p)
    {
        p.set_arrivalTime(this.currentTime.get());
        this.readyQueue.add(p);
    }
    
   @Override
    public void schedule()
    {
        while (this.isRunning)
        {
            if (isPreemptive)
            {
                // Preemptive priority scheduling
                if (!this.readyQueue.isEmpty() || this.currentProcess.get() != null)
                {
                    while (!this.readyQueue.isEmpty() && this.completedProcesses.contains(this.readyQueue.peek()))
                    {
                        this.readyQueue.poll();
                        set_observableReadyQueue();
                    }

                    if (this.currentProcess.get() != null && readyQueue.peek() != null && readyQueue.peek().get_priority() < this.currentProcess.get().get_priority())
                    {
                        this.readyQueue.add(this.currentProcess.get());
                        set_currentProcess(this.readyQueue.poll());
                        set_observableReadyQueue();
                    }
                    else if (this.currentProcess.get() == null && this.readyQueue.peek() != null)
                    {
                        set_currentProcess(this.readyQueue.poll());
                        set_observableReadyQueue();
                    }

                    if (this.currentProcess.get() != null)
                    {
                        this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);

                        try
                        {
                            if (this.isDynamic)
                            {
                                Thread.sleep(1000);
                            }
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            break;
                        }

                        increment_currentTime();

                        if (this.currentProcess.get().get_remainingBurstTime() == 0)
                        {
                            this.currentProcess.get().set_finishedTime(this.currentTime.get());
                            this.completedProcesses.add(this.currentProcess.get());
                            this.calculate_averageWaitingTime();
                            this.calculate_averageTurnAroundTime();
                            set_currentProcess(null);
                        }
                    }
                }
                else
                {
                    set_currentProcess(null);

                    if (this.isDynamic)
                    {
                        try
                        {
                            Thread.sleep(1000);
                            increment_currentTime();
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }
            else
            {
                // Non-preemptive priority scheduling
                if (!this.readyQueue.isEmpty() || this.currentProcess.get() != null)
                {
                    // Skip completed processes
                    while (!this.readyQueue.isEmpty() && this.completedProcesses.contains(this.readyQueue.peek()))
                    {
                        this.readyQueue.poll();
                        set_observableReadyQueue();
                    }
                    
                    if (!this.readyQueue.isEmpty())
                    {
                        set_currentProcess(this.readyQueue.poll());
                        set_observableReadyQueue();

                        for (int i = 0; i < this.currentProcess.get().get_initialBurstTime(); i++)
                        {
                            try
                            {
                                if (this.isDynamic)
                                {
                                    Thread.sleep(1000);
                                }
                            }
                            catch (InterruptedException e)
                            {
                                Thread.currentThread().interrupt();
                                break;
                            }

                            increment_currentTime();
                            this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);
                        }

                        this.currentProcess.get().set_finishedTime(this.currentTime.get());
                        this.completedProcesses.add(this.currentProcess.get());
                        this.calculate_averageWaitingTime();
                        this.calculate_averageTurnAroundTime();
                        set_currentProcess(null);
                    }
                }
                else
                {
                    set_currentProcess(null);

                    if (this.isDynamic)
                    {
                        try
                        {
                            Thread.sleep(1000);
                            increment_currentTime();
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }
        }
    }
        
    public void set_preemptive(boolean p) {
        this.isPreemptive = p;
    }

    public boolean is_preemptive() {
        return this.isPreemptive;
    }
    
    @Override
    public void set_observableReadyQueue()
    {
        List<Process> sortedList = new ArrayList<>(this.readyQueue);

        sortedList.sort(Comparator.comparingInt(Process::get_remainingBurstTime));
        
        Platform.runLater(() -> this.observableReadyQueue.setAll(sortedList));
    }
}
