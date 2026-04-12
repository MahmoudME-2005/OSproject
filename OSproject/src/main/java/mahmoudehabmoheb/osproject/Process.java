/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject;

/**
 *
 * @author Mahmoud Ehab
 */
public class Process {
    private int burstTime;
    private int arrivalTime;
    private int finishedTime;
    
    public void set_burstTime(int burstTime)
    {
        this.burstTime = burstTime;
    }
    
    public void set_arrivatTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }
    
    public void set_finishedTime(int finishedTime)
    {
        this.finishedTime = finishedTime;
    }
    
    public int get_burstTime()
    {
        return this.burstTime;
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
