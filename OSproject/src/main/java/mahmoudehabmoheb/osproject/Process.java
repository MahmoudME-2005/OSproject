package mahmoudehabmoheb.osproject;

// You can edit this class to your liking I'll handle it's final form in the end.
public class Process {
    private String id; // Needed to identify which process is running
    private int initialBurstTime;
    private int arrivalTime;
    private int remainingBurstTime;
    private int finishedTime;

    // Added for final outputs
    private int waitingTime;
    private int turnaroundTime;

    // Added a constructor to make creating processes much easier
    public Process(String id, int arrivalTime, int initialBurstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.initialBurstTime = initialBurstTime;
        this.remainingBurstTime = initialBurstTime;
    }

    public void set_initialBurstTime(int initialBurstTime) {
        this.initialBurstTime = initialBurstTime;
    }

    public void set_remainingBurstTime(int remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    // Fixed typo: was set_arrivatTime
    public void set_arrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void set_finishedTime(int finishedTime) {
        this.finishedTime = finishedTime;
    }

    public int get_initialBurstTime() {
        return this.initialBurstTime;
    }

    // Fixed logic bug: was returning initialBurstTime
    public int get_remainingBurstTime() {
        return this.remainingBurstTime;
    }

    public int get_arrivalTime() {
        return this.arrivalTime;
    }

    public int get_finishedTime() {
        return this.finishedTime;
    }

    // --- Added methods for new variables ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int get_waitingTime() { return waitingTime; }
    public void set_waitingTime(int waitingTime) { this.waitingTime = waitingTime; }

    public int get_turnaroundTime() { return turnaroundTime; }
    public void set_turnaroundTime(int turnaroundTime) { this.turnaroundTime = turnaroundTime; }
}