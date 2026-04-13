package mahmoudehabmoheb.osproject;

// You can edit this class to your liking I'll handle it's final form in the end.
public class Process {
    private String id;
    private int initialBurstTime;
    private int arrivalTime;
    private int remainingBurstTime;
    private int finishedTime;
    private int priority;
    
    public Process(int initialBurstTime)
    {
        this.id = id;
        this.initialBurstTime = initialBurstTime;
        this.arrivalTime = 0;
        this.remainingBurstTime = this.initialBurstTime;
        this.finishedTime = 0;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public void set_initialBurstTime(int initialBurstTime)
    {
        this.initialBurstTime = initialBurstTime;
    }

    public void set_remainingBurstTime(int remainingBurstTime) 
    {
        this.remainingBurstTime = remainingBurstTime;
    }

    public void set_arrivalTime(int arrivalTime) 
    {
        this.arrivalTime = arrivalTime;
    }

    public void set_finishedTime(int finishedTime) 
    {
        this.finishedTime = finishedTime;
    }

    public String getId()
    {
        return id;
    }
    
    public int get_initialBurstTime() 
    {
        return this.initialBurstTime;
    }

    public int get_remainingBurstTime() 
    {
        return this.remainingBurstTime;
    }

    public int get_arrivalTime()
    {
        return this.arrivalTime;
    }

    public int get_finishedTime()
    {
        return this.finishedTime;
    }
    public int get_priority() {

        return this.priority;
    }
    
}