/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Mahmoud Ehab
 */

public abstract class Scheduler<T>
{
    protected ObjectProperty<Process> currentProcess;
    protected IntegerProperty currentTime;
    protected List<Process> completedProcesses;
    protected T readyQueue;
    protected ObservableList<Process> observableReadyQueue;
    protected DoubleProperty averageWaitingTime;
    protected DoubleProperty averageTurnAroundTime;
    protected boolean isDynamic;
    protected boolean isRunning;
    
    public Scheduler()
    {
        this.currentProcess = new SimpleObjectProperty<>();
        this.currentTime = new SimpleIntegerProperty(0);
        this.completedProcesses = new ArrayList<>();
        this.averageWaitingTime = new SimpleDoubleProperty(0.0);
        this.averageTurnAroundTime = new SimpleDoubleProperty(0.0);
        this.isDynamic = false;
        this.isRunning = false;
    }
    
    public abstract void add_Process(Process P);
    
    public abstract void schedule();
    
    public double calculate_averageWaitingTime()
    {
        int result = 0;
        Process tempProcess;
        
        for (int i = 0; i < this.completedProcesses.size(); i++)
        {
            tempProcess = this.completedProcesses.get(i);
            result += (tempProcess.get_finishedTime() - tempProcess.get_arrivalTime() - tempProcess.get_initialBurstTime());
        }
        
        final double calculatedResult = (double) result/this.completedProcesses.size();
        
        set_averagetWaitingTime(calculatedResult);
        return this.averageWaitingTime.get();
    }
    
    public double calculate_averageTurnAroundTime()
    {
        int result = 0;
        Process tempProcess;
        
        for (int i = 0; i < this.completedProcesses.size(); i++)
        {
            tempProcess = this.completedProcesses.get(i);
            result += (tempProcess.get_finishedTime() - tempProcess.get_arrivalTime());
        }
        
        final double calculatedResult = (double) result/this.completedProcesses.size();
        
        set_averageTurnAroundTime(calculatedResult);
        return this.averageTurnAroundTime.get();
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
        // 1. Create a latch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try
            {
                this.currentProcess.set(P);
            }
            finally
            {
                // 2. This runs AFTER the UI is updated
                latch.countDown(); 
            }
        });

        try
        {
            // 3. The background thread STOPS here until countDown() is called
            latch.await(); 
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public ObjectProperty<Process> get_currentProcessProperty()
    {
        return this.currentProcess;
    }
    
    public void set_dynamic(boolean dynamic)
    {
        this.isDynamic = dynamic;
    }
    
    public boolean get_dynamic()
    {
        return this.isDynamic;
    }
    
    public void set_isRunning(boolean running)
    {
        this.isRunning = running;
    }
    
    public boolean get_isRunning()
    {
        return this.isRunning;
    }
    
    public abstract void set_observableReadyQueue();
    
    public ObservableList<Process> get_observableReadyQueue()
    {
        return this.observableReadyQueue;
    }
    
    public IntegerProperty get_currentTimeProperty()
    {
        return this.currentTime;
    }
    
    public void increment_currentTime()
    {
        // 1. Create a latch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);
        int nextTime = this.currentTime.get() + 1;

        Platform.runLater(() -> {
            try
            {
                this.currentTime.set(nextTime);
            }
            finally
            {
                // 2. This runs AFTER the UI is updated
                latch.countDown(); 
            }
        });

        try
        {
            // 3. The background thread STOPS here until countDown() is called
            latch.await(); 
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public DoubleProperty get_averageWaitingTimeProperty()
    {
        return this.averageWaitingTime;
    }
    
    public DoubleProperty get_averageTurnAroundTimeProperty()
    {
        return this.averageTurnAroundTime;
    }
    
    public void set_averagetWaitingTime(double averageWaitingTime)
    {
        // 1. Create a latch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try
            {
                this.averageWaitingTime.set(averageWaitingTime);
            }
            finally
            {
                // 2. This runs AFTER the UI is updated
                latch.countDown(); 
            }
        });

        try
        {
            // 3. The background thread STOPS here until countDown() is called
            latch.await(); 
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public void set_averageTurnAroundTime(double averageTurnAroundTime)
    {
        // 1. Create a latch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try
            {
                this.averageTurnAroundTime.set(averageTurnAroundTime);
            }
            finally
            {
                // 2. This runs AFTER the UI is updated
                latch.countDown(); 
            }
        });

        try
        {
            // 3. The background thread STOPS here until countDown() is called
            latch.await(); 
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
