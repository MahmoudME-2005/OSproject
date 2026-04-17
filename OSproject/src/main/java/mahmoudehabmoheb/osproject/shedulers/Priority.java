package mahmoudehabmoheb.osproject.shedulers;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.application.Platform;
import mahmoudehabmoheb.osproject.Process;

/**
 * Priority scheduler implementation.
 * Lower integer priority means higher scheduling priority.
 */
public class Priority extends Scheduler<PriorityQueue<Process>> {
    private boolean isPreemptive;

    public Priority() {
        this(false); // Default to non-preemptive
    }

    public Priority(boolean isPreemptive) {
        super();
        this.isPreemptive = isPreemptive;
        this.readyQueue = new PriorityQueue<>(
                Comparator.comparingInt(Process::get_priority)
                        .thenComparingInt(Process::get_arrivalTime)
                        .thenComparingInt(Process::get_id));
    }

    public PriorityQueue<Process> get_readyQueue() {
        return this.readyQueue;
    }

    @Override
    public void add_Process(Process p) {
        p.set_arrivalTime(this.currentTime.get());
        this.readyQueue.add(p);
        set_observableReadyQueue();
    }

    @Override
    public void schedule() {
        if (isPreemptive) {
            // Preemptive priority scheduling
            while (!readyQueue.isEmpty() || this.currentProcess.get() != null)
            {
                while (!readyQueue.isEmpty() && completedProcesses.contains(readyQueue.peek()))
                {
                    readyQueue.poll();
                }

                if (this.currentProcess.get() != null && readyQueue.peek() != null && readyQueue.peek().get_priority() < this.currentProcess.get().get_priority())
                {
                    readyQueue.add(this.currentProcess.get());
                    this.currentProcess.set(readyQueue.poll());
                }
                else if (this.currentProcess.get() == null && readyQueue.peek() != null) {
                    this.currentProcess.set(readyQueue.peek());
                    readyQueue.poll();
                }

                if (this.currentProcess.get() != null) {
                    this.currentProcess.get()
                            .set_remainingBurstTime(this.currentProcess.get().get_remainingBurstTime() - 1);

                    try {
                        if (this.isDynamic) {
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }

                    increment_currentTime();

                    if (this.currentProcess.get().get_remainingBurstTime() == 0) {
                        this.currentProcess.get().set_finishedTime(this.currentTime.get());
                        this.completedProcesses.add(this.currentProcess.get());
                        this.currentProcess.set(null);
                    }
                } else {
                    if (this.isDynamic) {
                        try {
                            Thread.sleep(1000);
                            increment_currentTime();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }
        } else {
            // Non-preemptive priority scheduling
            while (!this.readyQueue.isEmpty() || this.currentProcess.get() != null) {
                // Skip completed processes
                while (!this.readyQueue.isEmpty() && this.completedProcesses.contains(this.readyQueue.peek())) {
                    this.readyQueue.poll();
                }

                if (!this.readyQueue.isEmpty()) {
                    Process p = this.readyQueue.poll();
                    this.currentProcess.set(p);

                    for (int i = 0; i < p.get_initialBurstTime(); i++) {
                        try {
                            if (this.isDynamic) {
                                Thread.sleep(1000);
                            }
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                        increment_currentTime();
                    }

                    p.set_finishedTime(this.currentTime.get());
                    this.completedProcesses.add(p);
                } else {
                    if (this.isDynamic) {
                        try {
                            Thread.sleep(1000);
                            increment_currentTime();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }
        }
    }

    public void set_preemptive(boolean p) {
        this.isPreemptive = p;
    }

    public boolean is_preemptive() {
        return this.isPreemptive;
    }

    @Override
    public void set_observableReadyQueue() {
        Platform.runLater(() -> this.observableReadyQueue.setAll(readyQueue));
    }
}
