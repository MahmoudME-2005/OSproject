/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.concurrent.CountDownLatch;
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
                // 1. Remove from queue and update UI
                this.currentProcess.set(this.readyQueue.poll());
                set_observableReadyQueue(); 

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
                        Thread.currentThread().interrupt();
                        break;
                    }

                    increment_currentTime();
                    this.currentProcess.get().set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);

                    if (this.currentProcess.get().get_remainingBurstTime() == 0)
                    {
                        this.currentProcess.get().set_finishedTime(this.currentTime.get());
                        this.completedProcesses.add(this.currentProcess.get());
                        this.calculate_averageWaitingTime();
                        this.calculate_averageTurnAroundTime();
                        break;
                    }
                }

                // 2. If process isn't finished, add it back and update UI
                if (this.currentProcess.get().get_remainingBurstTime() != 0)
                {
                    this.readyQueue.add(this.currentProcess.get());  
                    set_observableReadyQueue(); 
                }
                else
                {
                    // Ensure the currentProcess property is cleared when done
                    this.set_currentProcess(null);
                    continue;
                }
            }
            else
            {
                this.set_currentProcess(null);
                
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
    
//    @Override
//    public void set_observableReadyQueue()
//    {
//        // 1. Create a latch with a count of 1
//        CountDownLatch latch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            try
//            {
//                this.observableReadyQueue.setAll(this.readyQueue);
//            }
//            finally
//            {
//                // 2. This runs AFTER the UI is updated
//                latch.countDown(); 
//            }
//        });
//
//        try
//        {
//            // 3. The background thread STOPS here until countDown() is called
//            latch.await(); 
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//    }
}
