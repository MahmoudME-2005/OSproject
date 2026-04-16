package mahmoudehabmoheb.osproject;

// You can edit this class to your liking I'll handle it's final form in the end.
public class Process
{
    private int id;
    private int initialBurstTime;
    private int arrivalTime;
    private int remainingBurstTime;
    private int finishedTime;
    private int priority;
    private static int counter = 0;
    
    public Process()
    {
        this(5, 0);
    }
    
    public Process(int initialBurstTime)
    {
        this(initialBurstTime, 0);
    }
    
    public Process(int initialBurstTime, int priority)
    {
        Process.counter++;
        this.id = Process.counter;
        this.initialBurstTime = initialBurstTime;
        this.remainingBurstTime = this.initialBurstTime;
        this.arrivalTime = 0;
        this.finishedTime = 0;
        this.priority = priority;
    }
    
    // This setter shouldn't be used unless necessary process id's will be set automatically inorder.
    public void set_id(int id)
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
    
    public static void set_counter(int count)
    {
        Process.counter = count;
    }

    public int get_id()
    {
        return this.id;
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
    
    public int get_priority()
    {

        return this.priority;
    }
    
    public static int get_counter()
    {
        return Process.counter;
    }
}