/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject;

/**
 *
 * @author Mahmoud Ehab
 */

// You can edit this class to your liking I'll handle it's final form in the end.
public class Process {
    private int initialBurstTime;
    private int arrivalTime;
    private int remainingBurstTime;
    private int finishedTime;
    
    public Process(int initialBurstTime)
    {
        this.initialBurstTime = initialBurstTime;
        this.arrivalTime = 0;
        this.remainingBurstTime = this.initialBurstTime;
        this.finishedTime = 0;
    }
    
    public void set_initialBurstTime(int initialBurstTime)
    {
        this.initialBurstTime = initialBurstTime;
    }
    
    public void set_remainingBurstTime(int remainingBurstTime)
    {
        this.remainingBurstTime = remainingBurstTime;
    }
    
    public void set_arrivatTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
    
    public void set_finishedTime(int finishedTime)
    {
        this.finishedTime = finishedTime;
    }
    
    public int get_initialBurstTime()
    {
        return this.initialBurstTime;
    }
    
    public int get_remainingBurstTime()
    {
        return this.initialBurstTime;
    }
    
    public int get_arrivalTime()
    {
        return this.arrivalTime;
    }
    
    public int get_finishedTime()
    {
        return this.finishedTime;
    }
}
