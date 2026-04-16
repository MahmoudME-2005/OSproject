/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Mahmoud Ehab
 */

public abstract class Scheduler<T>
{
    protected ObjectProperty<Process> currentProcess;
    protected int currentTime;
    protected List<Process> completedProcesses;
    protected T readyQueue;
    protected int averageWaitingTime;
    protected int averageTurnAroundTime;
    protected boolean dynamic;
    
    public Scheduler()
    {
        this.currentProcess = new SimpleObjectProperty<>();
        this.currentTime = 0;
        this.completedProcesses = new ArrayList<>();
        this.averageWaitingTime = 0;
        this.averageTurnAroundTime = 0;
        this.dynamic = false;
    }
    
    public abstract void add_Process(Process P);
    
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
    
    public Process get_currentProcess()
    {
        return this.currentProcess.get();
    }
    
    public void set_currentProcess(Process P)
    {
        this.currentProcess.set(P);
    }
    
    public ObjectProperty<Process> get_currentProcessProperty()
    {
        return this.currentProcess;
    }
    
    public void set_dynamic(boolean dynamic)
    {
        this.dynamic = dynamic;
    }
    
    public boolean get_dynamic()
    {
        return this.dynamic;
    }
}
