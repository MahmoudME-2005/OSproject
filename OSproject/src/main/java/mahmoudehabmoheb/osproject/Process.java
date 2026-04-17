package mahmoudehabmoheb.osproject;

// You can edit this class to your liking I'll handle it's final form in the end.

import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Process
{
    private int id;
    private int initialBurstTime;
    private int arrivalTime;
    private IntegerProperty remainingBurstTime;
    private int finishedTime;
    private int priority;
    private static int counter = 0;
//    private static IntegerProperty counter = new SimpleIntegerProperty(0);
//    private static int dataOfLastAddedProcess[] = new int[2];
    
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
//        Process.increment_counter();
//        this.id = Process.counter.get();
        this.initialBurstTime = initialBurstTime;
        this.remainingBurstTime = new SimpleIntegerProperty(this.initialBurstTime);
        this.arrivalTime = 0;
        this.finishedTime = 0;
        this.priority = priority;
//        Process.dataOfLastAddedProcess[0] = this.initialBurstTime;
//        Process.dataOfLastAddedProcess[1] = this.priority;
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
                // 1. Create a latch with a count of 1
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try
            {
                this.remainingBurstTime.set(remainingBurstTime);
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
    
//    public static void set_counter(int count)
//    {
//        // 1. Create a latch with a count of 1
//        CountDownLatch latch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            try
//            {
//                Process.counter.set(count);
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
//    
//    public static void set_dataOfLastAddedProcess(int initialBurstTime, int priority)
//    {
//        Process.dataOfLastAddedProcess[0] = initialBurstTime;
//        Process.dataOfLastAddedProcess[1] = priority;
//    }
//    
//    public static void increment_counter()
//    {
//        // 1. Create a latch with a count of 1
//        CountDownLatch latch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            try
//            {
//                Process.counter.set(Process.counter.get() + 1);
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
//    
//    public static void decrement_counter()
//    {
//        // 1. Create a latch with a count of 1
//        CountDownLatch latch = new CountDownLatch(1);
//
//        Platform.runLater(() -> {
//            try
//            {
//                Process.counter.set(Process.counter.get() - 1);
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
        return this.remainingBurstTime.get();
    }
    
    public IntegerProperty get_remainingBurstTimeProperty()
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
    
//    public static int get_counter()
//    {
//        return Process.counter.get();
//    }
//    
//    public static IntegerProperty get_counterProperty()
//    {
//        return Process.counter;
//    }
//    
//    public static int[] get_dataOfLastAddedProcess()
//    {
//        return Process.dataOfLastAddedProcess;
//    }
    
    @Override
    public String toString()
    {
        return ("P" + this.id); 
    }
}