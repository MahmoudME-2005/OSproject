package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;

/**
 * SJF Scheduler
 * Supports Preemptive (SRTF) and Non-Preemptive scheduling without modifying the original Process class.
 */
public class SJF extends Sheduler
{
    private boolean isPreemptive;
    private PriorityQueue<Process> readyQueue;

    public SJF(boolean isPreemptive)
    {
        super();
        this.isPreemptive = isPreemptive;
        
        // Sorting using Mahmoud's getter methods
        this.readyQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::get_remainingBurstTime).thenComparingInt(Process::get_arrivalTime)
        );
    }

    public void addProcess(Process p)
    {
        readyQueue.add(p);
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
            if (this.readyQueue.peek().get_arrivalTime() <= this.currentTime)
            {
                this.currentProcess = this.readyQueue.poll();
            }
        }

        return this.currentProcess;
    }

    public List<Process> getCompletedProcesses()
    {
        return this.completedProcesses;
    }
}