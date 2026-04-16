/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Queue;
import java.util.ArrayDeque;
import javafx.application.Platform;

/**
 *
 * @author Mahmoud Ehab
 */
public class RoundRobin extends Scheduler<Queue<Process>>
{
    private int quantum;
    
    public RoundRobin() // Defaults to time quantum of 3 seconds.
    {
        this(3);
    }
    
    public RoundRobin(int quantum)
    {
        super();
        this.quantum = quantum;
        this.readyQueue = new ArrayDeque();
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
        while (this.isRunning)
        {
            if (!this.readyQueue.isEmpty())
            {
                this.currentProcess.set(this.readyQueue.poll());

                for (int i = 0; i < this.quantum; i++)
                {
                    try
                    {
                        if (this.isDynamic)
                        {
                            Thread.sleep(1000);
                        }
                    }
                    catch (InterruptedException ex)
                    {
                        System.out.println("Process Executing");
                    }

                    increment_currentTime();
                    this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);

                    if (this.currentProcess.get().get_remainingBurstTime() == 0)
                    {
                        this.currentProcess.get().set_finishedTime(this.currentTime.get());
                        this.completedProcesses.add(this.currentProcess.get());
                        this.calculate_averageWaitingTime();
                        this.calculate_averageTurnAroundTime();
                        continue;
                    }
                }

                this.readyQueue.add(this.currentProcess.get());
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
    
    public void set_quantum(int quantum)
    {
        this.quantum = quantum;
    }
    
    public int get_quantum()
    {
        return this.quantum;
    }
    
    @Override
    public void set_observableReadyQueue()
    {
        Platform.runLater(() -> this.observableReadyQueue.setAll(this.readyQueue));
    }
}
