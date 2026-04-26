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
    
    public FCFS(FCFS original)
    {
        super(original);
        
        this.readyQueue = new ArrayDeque<>();
        
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
        Platform.runLater(() -> this.observableReadyQueue.setAll(this.readyQueue));
    }
    
    @Override
    public void schedule()
    {   
        while (this.isRunning)
        {
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
                        
                        increment_currentTime();
                    }
                    catch (InterruptedException ex)
                    {
                        System.out.println("Process Executing");
                    }
                    
                    this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);
                }
                
                this.currentProcess.get().set_finishedTime(this.currentTime.get());
                this.completedProcesses.add(this.currentProcess.get());
                this.calculate_averageWaitingTime();
                this.calculate_averageTurnAroundTime();
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
}
