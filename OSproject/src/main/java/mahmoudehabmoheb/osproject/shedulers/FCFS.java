/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osproject.shedulers;

import mahmoudehabmoheb.osproject.Process;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Mahmoud Ehab
 */
public class FCFS extends Sheduler {
    ArrayList<Process> processes;
    ArrayList<Double>waiting;
    ArrayList<Double>turnaround;
    double waitingTime=0;
    double turnaroundTime=0;
    ArrayList<String> gantt;

    public FCFS(ArrayList<Process> processes){
        this.processes=processes;
        waiting=new ArrayList<>();
        turnaround=new ArrayList<>();
        gantt=new ArrayList<>();
    }
    @Override
    public void schedule() {

        processes.sort(Comparator.comparingInt(p -> p.get_arrivalTime()));

        int currentTime = 0;
        int completedProcesses = 0;
        int totalProcesses = processes.size();
        int currentIdx = 0;

        System.out.println("--- start ---");

        while (completedProcesses < totalProcesses) {
            Process current = null;


            if (currentIdx < totalProcesses) {
                Process p = processes.get(currentIdx);
                if (p.get_arrivalTime() <= currentTime) {
                    current = p;
                }
            }

            if (current != null) {

                gantt.add("P" + current.getId());


                int nextRemaining = current.get_remainingBurstTime() - 1;
                current.set_remainingBurstTime(nextRemaining);

                System.out.println("Time " + currentTime + ": Executing P" + current.getId() +
                        " (Remaining: " + current.get_remainingBurstTime() + ")");


                if (current.get_remainingBurstTime() <= 0) {
                    completedProcesses++;
                    currentIdx++;

                    double finishTime = currentTime + 1;
                    double taTime = finishTime - current.get_arrivalTime();
                    double wTime = taTime - current.get_initialBurstTime();

                    turnaround.add(taTime);
                    waiting.add(wTime);
                    System.out.println(">> [Done] P" + current.getId() + " finished at " + finishTime);
                }
            } else {

                gantt.add("Idle");
                System.out.println("Time " + currentTime + ": Idle...");
            }

            currentTime++;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("--- done ---");
    }
    public double avgwaitingtime(){
        double sum=0;
        for(double a:waiting){
            sum+=a;
        }
        return sum/waiting.size();
    } public double avgturnaroundtime(){
        double sum=0;
        for(double a:turnaround){
            sum+=a;
        }
        return sum/turnaround.size();
    }

}
