package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * SJF Scheduler
 * Supports Preemptive (SRTF) and Non-Preemptive scheduling without modifying the original Process class.
 */
public class SJF extends Scheduler
{
    private boolean isPreemptive;
    private PriorityQueue<Process> readyQueue;

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

    public void add_Process(Process p)
    {
        p.set_arrivalTime(this.currentTime);
        this.readyQueue.add(p);
    }

    @Override
    public void schedule()
    {
        while (currentProcess != null || !readyQueue.isEmpty())
        {
            try 
            {
                tick(currentTime);
                Thread.sleep(1000);
                currentTime++;
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public Process tick(int time)
    {
        this.currentTime = time;

        // 1. Execute current process
        if (this.currentProcess != null)
        {
            this.currentProcess.set_remainingBurstTime(this.currentProcess.get_remainingBurstTime() - 1);

            if (this.currentProcess.get_remainingBurstTime() == 0) 
            {
                this.currentProcess.set_finishedTime(this.currentTime + 1);
                this.completedProcesses.add(this.currentProcess);
                this.calculate_averageWaitingTime();
                this.calculate_averageTurnAroundTime();
                this.currentProcess = null; // Free CPU
            }
        }

        // 2. Preemption Check
        if (this.isPreemptive && this.currentProcess != null && !this.readyQueue.isEmpty())
        {
            if (this.readyQueue.peek().get_remainingBurstTime() < this.currentProcess.get_remainingBurstTime())
            {
                this.readyQueue.add(this.currentProcess);
                this.currentProcess = null;
            }
        }

        // 3. Load next shortest process
        if (this.currentProcess == null && !this.readyQueue.isEmpty())
        {
            this.currentProcess = this.readyQueue.poll();
        }

        return this.currentProcess;
    }
}