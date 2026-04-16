/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Queue;
import java.util.ArrayDeque;

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
        p.set_arrivalTime(this.currentTime);
        this.readyQueue.add(p);
    }
    
    @Override
    public void schedule()
    {
        while (!this.readyQueue.isEmpty())
        {
            this.currentProcess.set(this.readyQueue.poll());
            
            try
            {
                for (int i = 0; i < this.currentProcess.get().get_initialBurstTime(); i++)
                {
                    Thread.sleep(1000);
                    this.currentTime++;
                }
            }
            catch (InterruptedException ex)
            {
                System.out.println("Process Executing");
            }
            
            this.currentProcess.get().set_remainingBurstTime(0);
            this.currentProcess.get().set_finishedTime(this.currentTime);
            this.completedProcesses.add(this.currentProcess.get());
            this.calculate_averageWaitingTime();
            this.calculate_averageTurnAroundTime();
        }
    }
}
