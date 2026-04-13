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
public class RoundRobin extends Scheduler {
    private Queue<Process> readyQueue;
    
    public RoundRobin()
    {
        super();
        readyQueue = new ArrayDeque<>();
    }
    
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
            this.currentProcess = this.readyQueue.poll();
            
            try
            {
                Thread.sleep(1000);   
            }
            catch (InterruptedException ex)
            {
                System.out.println("Process Executing");
            }
            
            this.currentTime++;
            this.currentProcess.set_remainingBurstTime(this.currentProcess.get_remainingBurstTime() - 1);
            
            if (this.currentProcess.get_remainingBurstTime() == 0)
            {
                this.currentProcess.set_finishedTime(this.currentTime);
                this.completedProcesses.add(this.currentProcess);
                this.calculate_averageWaitingTime();
                this.calculate_averageTurnAroundTime();
                continue;
            }
            
            this.readyQueue.add(this.currentProcess);
        }
    }
}
