/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import java.util.ArrayList;
import java.util.List;

import mahmoudehabmoheb.osproject.Process;

/**
 *
 * @author Mahmoud Ehab
 */
public abstract class Scheduler {
    protected Process currentProcess;
    protected int currentTime;
    protected List<Process> completedProcesses;
    protected int averageWaitingTime;
    protected int averageTurnAroundTime;
    
    public Scheduler()
    {
        this.currentProcess = null;
        this.currentTime = 0;
        this.completedProcesses = new ArrayList<>();
        this.averageWaitingTime = 0;
        this.averageTurnAroundTime = 0;
    }
    
    public abstract void schedule();
    
    public int calculate_averageWaitingTime()
    {
        int result = 0;
        Process tempProcess;
        
        for (int i = 0; i < this.completedProcesses.size(); i++)
        {
            tempProcess = this.completedProcesses.get(i);
            result += tempProcess.get_finishedTime() - tempProcess.get_arrivalTime() - tempProcess.get_initialBurstTime();
        }
        
        this.averageWaitingTime = result/this.completedProcesses.size();
        return this.averageWaitingTime;
    }
    
    public int calculate_averageTurnAroundTime()
    {
        int result = 0;
        Process tempProcess;
        
        for (int i = 0; i < this.completedProcesses.size(); i++)
        {
            tempProcess = this.completedProcesses.get(i);
            result += tempProcess.get_finishedTime() - tempProcess.get_arrivalTime();
        }
        
        this.averageTurnAroundTime = result/this.completedProcesses.size();
        return this.averageTurnAroundTime;
    }
    
    public List<Process> get_completedProcesses()
    {
        return this.completedProcesses;
    }
}
