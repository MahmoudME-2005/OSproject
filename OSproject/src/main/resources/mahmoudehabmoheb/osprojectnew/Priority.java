/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;

import mahmoudehabmoheb.osproject.Process;

/**
 *
 * @author Mahmoud Ehab
 */

public class Priority extends Scheduler<List<Process>>
{
    private boolean isPreemptive;

    public Priority()
    {
        this(false); // Default to non-preemptive
    }

    public Priority(boolean isPreemptive)
    {
        super();
        this.readyQueue = new ArrayList<>();
        this.isPreemptive = isPreemptive;
    }

    public List<Process> get_readyQueue()
    {
        return this.readyQueue;
    }
    
    @Override
    public void add_Process(Process p)
    {
        p.set_arrivalTime(this.currentTime.get());
        this.readyQueue.add(p);
        set_observableReadyQueue();
    }

    @Override
    public void schedule()
    {
        if (isPreemptive)
        {
            // Preemptive priority scheduling (lower number = higher priority)
            while (completedProcesses.size() < readyQueue.size())
            {
                Process highestPriorityProcess = null;

                for (Process p : this.readyQueue) {
                    if (!completedProcesses.contains(p))
                    {
                        if (highestPriorityProcess == null || p.get_priority() < highestPriorityProcess.get_priority())
                        {
                            highestPriorityProcess = p;
                        }
                    }
                }

                if (this.currentProcess != null && highestPriorityProcess != null && highestPriorityProcess.get_priority() < this.currentProcess.get().get_priority())
                {
                    this.currentProcess.set(highestPriorityProcess);
                }
                else if (currentProcess == null && highestPriorityProcess != null)
                {
                    this.currentProcess.set(highestPriorityProcess);
                }

                if (this.currentProcess != null)
                {
                    this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);

                    try
                    {
                        if (this.isDynamic)
                        {
                            Thread.sleep(1000); // 1 second per time unit
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
                        this.currentProcess = null;
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
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                    else
                    {
                        continue;
                    }
                }
            }
        } // Non-preemptive priority scheduling
        else
        {
            while (this.completedProcesses.size() < this.readyQueue.size())
            {
                Process highestPriorityProcess = null;

                for (Process p : this.readyQueue)
                {
                    if (!this.completedProcesses.contains(p))
                    {
                        if (highestPriorityProcess == null || p.get_priority() < highestPriorityProcess.get_priority())
                        {
                            highestPriorityProcess = p;
                        }
                    }
                }

                if (highestPriorityProcess != null)
                {
                    this.currentProcess.set(highestPriorityProcess);

                    // Execute the process for its full burst time, sleeping 1 second per time unit
                    for (int i = 0; i < highestPriorityProcess.get_initialBurstTime(); i++)
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
                    }

                    highestPriorityProcess.set_finishedTime(this.currentTime.get());
                    this.completedProcesses.add(highestPriorityProcess);
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
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                else
                {
                    continue;
                }
                }
            }
        }
    }
    
    public void set_preemptive(boolean p)
    {
        this.isPreemptive = p;
    }
    
    public boolean is_preemptive()
    {
        return this.isPreemptive;
    }
    
    @Override
    public void set_observableReadyQueue()
    {
        Platform.runLater(() -> this.observableReadyQueue.setAll(this.readyQueue));
    }
}
