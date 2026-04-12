package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class SJF implements Shedulable {

    private PriorityQueue<Process> readyQueue;
    private Process currentProcess;
    private boolean isPreemptive;
    private int currentTime;
    private List<Process> completedProcesses;

    public SJF(boolean isPreemptive) {
        this.isPreemptive = isPreemptive;
        this.currentTime = 0;
        this.completedProcesses = new ArrayList<>();

        // Sorting using Mahmoud's getter methods
        this.readyQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::get_remainingBurstTime)
                        .thenComparingInt(Process::get_arrivalTime)
        );
    }

    public void addProcess(Process p) {
        readyQueue.add(p);
    }

    @Override
    public void schedule() {
        while (currentProcess != null || !readyQueue.isEmpty()) {
            try {
                tick(currentTime);
                Thread.sleep(1000);
                currentTime++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public Process tick(int time) {
        this.currentTime = time;

        // 1. Execute current process
        if (currentProcess != null) {
            currentProcess.set_remainingBurstTime(currentProcess.get_remainingBurstTime() - 1);

            if (currentProcess.get_remainingBurstTime() == 0) {
                currentProcess.set_finishedTime(this.currentTime + 1);
                currentProcess.set_turnaroundTime(currentProcess.get_finishedTime() - currentProcess.get_arrivalTime());
                currentProcess.set_waitingTime(currentProcess.get_turnaroundTime() - currentProcess.get_initialBurstTime());

                completedProcesses.add(currentProcess);
                currentProcess = null;
            }
        }

        // 2. Preemption Check
        if (isPreemptive && currentProcess != null && !readyQueue.isEmpty()) {
            if (readyQueue.peek().get_remainingBurstTime() < currentProcess.get_remainingBurstTime()) {
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