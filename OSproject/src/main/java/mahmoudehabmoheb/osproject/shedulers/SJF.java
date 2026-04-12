package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * SJF Scheduler
 * Supports Preemptive (SRTF) and Non-Preemptive scheduling without modifying the original Process class.
 */
public class SJF implements Shedulable {

    private PriorityQueue<Process> readyQueue;
    private Process currentProcess;
    private boolean isPreemptive;
    private int currentTime;

    // We use a Map to track remaining times without needing to alter Process.java
    private Map<Process, Integer> remainingTimes;
    private List<Process> completedProcesses;

    public SJF(boolean isPreemptive) {
        this.isPreemptive = isPreemptive;
        this.currentTime = 0;
        this.remainingTimes = new HashMap<>();
        this.completedProcesses = new ArrayList<>();

        // PriorityQueue sorts by Shortest Remaining Time. Tie-breaker: Arrival Time.
        this.readyQueue = new PriorityQueue<>(11,
            Comparator.comparingInt((Process p) -> remainingTimes.getOrDefault(p, 0)).thenComparingInt(Process::get_arrivalTime)
        );
    }

    public void addProcess(Process p) {
        // Log the initial burst time when the process enters the system
        remainingTimes.put(p, (Integer) p.get_burstTime());
        readyQueue.add(p);
    }

    @Override
    public void schedule() {
        // Runs the algorithm instantly to the end for the "Instant Evaluation" requirement
        while (currentProcess != null || !readyQueue.isEmpty()) {
            tick(currentTime);
            currentTime++;
        }
    }

    public Process tick(int time) {
        this.currentTime = time;

        // 1. Execute current process
        if (currentProcess != null) {
            int timeLeft = remainingTimes.get(currentProcess) - 1;
            remainingTimes.put(currentProcess, timeLeft);

            // If finished
            if (timeLeft == 0) {
                currentProcess.set_finishedTime(this.currentTime);
                completedProcesses.add(currentProcess);
                currentProcess = null; // Free CPU
            }
        }

        // 2. Preemption Check
        if (isPreemptive && currentProcess != null && !readyQueue.isEmpty()) {
            Process shortestInQueue = readyQueue.peek();
            if (remainingTimes.get(shortestInQueue) < remainingTimes.get(currentProcess)) {
                readyQueue.add(currentProcess);
                currentProcess = null;
            }
        }

        // 3. Load next shortest process
        if (currentProcess == null && !readyQueue.isEmpty()) {
            if (readyQueue.peek().get_arrivalTime() <= this.currentTime) {
                currentProcess = readyQueue.poll();
            }
        }

        return currentProcess;
    }

    public List<Process> getCompletedProcesses() {
        return completedProcesses;
    }
}