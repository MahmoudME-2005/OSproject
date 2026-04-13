/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import java.util.ArrayList;
import java.util.List;

import mahmoudehabmoheb.osproject.Process;

/**
 *
 * @author Mahmoud Ehab
 */

public class Priority extends Scheduler
{
    private boolean isPreemptive;
    private List<Process> processes;

    public Priority()
    {
        this(false); // Default to non-preemptive
    }

    public Priority(boolean isPreemptive)
    {
        super();
        this.processes = new ArrayList<>();
        this.isPreemptive = isPreemptive;
    }

    public List<Process> getprocesses()
    {
        return this.processes;
    }

    public void add_Process(Process p)
    {
        p.set_arrivalTime(this.currentTime);
        this.processes.add(p);
    }

    @Override
    public void schedule()
    {
        if (isPreemptive)
        {
            // Preemptive priority scheduling (lower number = higher priority)
            while (completedProcesses.size() < processes.size())
            {
                Process highestPriorityProcess = null;

                for (Process p : processes) {
                    if (!completedProcesses.contains(p))
                    {
                        if (highestPriorityProcess == null || p.get_priority() < highestPriorityProcess.get_priority())
                        {
                            highestPriorityProcess = p;
                        }
                    }
                }

                if (currentProcess != null && highestPriorityProcess != null && highestPriorityProcess.get_priority() < currentProcess.get_priority())
                {
                    currentProcess = highestPriorityProcess;
                }
                else if (currentProcess == null && highestPriorityProcess != null)
                {
                    currentProcess = highestPriorityProcess;
                }

                if (currentProcess != null)
                {
                    currentProcess.set_remainingBurstTime(currentProcess.get_remainingBurstTime() - 1);

                    try
                    {
                        Thread.sleep(1000); // 1 second per time unit
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        break;
                    }

                    currentTime++;

                    if (currentProcess.get_remainingBurstTime() == 0)
                    {
                        currentProcess.set_finishedTime(currentTime);
                        completedProcesses.add(currentProcess);
                        currentProcess = null;
                    }
                }
                else
                {

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    currentTime++;
                }
            }
        } // Non-preemptive priority scheduling
        else
        {
            while (completedProcesses.size() < processes.size())
            {
                Process highestPriorityProcess = null;

                for (Process p : processes)
                {
                    if (!completedProcesses.contains(p))
                    {
                        if (highestPriorityProcess == null || p.get_priority() < highestPriorityProcess.get_priority())
                        {
                            highestPriorityProcess = p;
                        }
                    }
                }

                if (highestPriorityProcess != null)
                {
                    currentProcess = highestPriorityProcess;

                    // Execute the process for its full burst time, sleeping 1 second per time unit
                    for (int i = 0; i < highestPriorityProcess.get_initialBurstTime(); i++)
                    {
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            break;
                        }
                        currentTime++;
                    }

                    highestPriorityProcess.set_finishedTime(currentTime);
                    completedProcesses.add(highestPriorityProcess);
                }
                else
                {
                    // No process, advance time
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        Thread.currentThread().interrupt();
                        break;
                    }
                    
                    currentTime++;
                }
            }
        }
    }
}
