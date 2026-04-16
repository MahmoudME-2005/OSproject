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
public class FCFS extends Scheduler<Queue<Process>>
{
    public FCFS()
    {
        super();
        this.readyQueue = new ArrayDeque<>();
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

                try
                {
                    for (int i = 0; i < this.currentProcess.get().get_initialBurstTime(); i++)
                    {
                        if (this.isDynamic)
                        {
                            Thread.sleep(1000);     
                        }
                        
                        increment_currentTime();
                    }
                }
                catch (InterruptedException ex)
                {
                    System.out.println("Process Executing");
                }

                this.currentProcess.get().set_remainingBurstTime(0);
                this.currentProcess.get().set_finishedTime(this.currentTime.get());
                this.completedProcesses.add(this.currentProcess.get());
                this.calculate_averageWaitingTime();
                this.calculate_averageTurnAroundTime();
            }
            else
            {
                Platform.runLater(() -> this.currentProcess.set(null));
                
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
    
    @Override
    public void set_observableReadyQueue()
    {
        Platform.runLater(() -> this.observableReadyQueue.setAll(this.readyQueue));
    }
}
