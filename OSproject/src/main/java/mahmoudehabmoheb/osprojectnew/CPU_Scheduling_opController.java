package mahmoudehabmoheb.osprojectnew;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javafx.application.Platform;

public class CPU_Scheduling_opController
{

    @FXML private TextField algorithmTxt, cpuTxt, readyQueueTxt;
    @FXML private TextField avgWaitTxt, avgTurnTxt, totalExecTxt;
    @FXML private ProgressBar p1Bar, p2Bar, p3Bar, p4Bar, p5Bar, p6Bar, p7Bar;
    @FXML private Text p1Burst, p2Burst, p3Burst, p4Burst, p5Burst, p6Burst, p7Burst;

    private Queue<ProcessModel> readyQueue = new LinkedList<>(); // I will remove this in a minute but reading the code
    private Timeline simulationTimeline;
    private boolean isRunning = false;

    public void setupSimulation(String algo)
    {
        algorithmTxt.setText(algo);
//        for (int i = 1; i <= count; i++) {
//            readyQueue.add(new ProcessModel("P" + i, (int)(Math.random() * 8 + 3))); 
//        }
        updateReadyQueueUI();
        startSimulation();
    }

    private void startSimulation() {
        if (!isRunning) {
            isRunning = true;
            runNextProcess();
        }
    }

    private void runNextProcess() {
        if (readyQueue.isEmpty()) {
            cpuTxt.setText("IDLE");
            isRunning = false;
            calculateAverages();
            return;
        }

        ProcessModel current = readyQueue.poll();
        updateReadyQueueUI();
        cpuTxt.setText("Running: " + current.getId());

        ProgressBar currentBar = getBarForProcess(current.getId());
        Text currentText = getTextForProcess(current.getId());

        simulationTimeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            double newValue = current.remainingTimeProperty().get() - 0.1;
            current.remainingTimeProperty().set(Math.max(0, newValue));
            
            double progress = (current.getBurstTime() - newValue) / current.getBurstTime();
            if (currentBar != null) currentBar.setProgress(progress);
            if (currentText != null) currentText.setText(String.format("%.1fs", newValue));

            if (newValue <= 0) {
                simulationTimeline.stop();
                runNextProcess();
            }
        }));
        simulationTimeline.setCycleCount(Timeline.INDEFINITE);
        simulationTimeline.play();
    }

    
    
    // THIS IS THE BUTTON METHOD
    @FXML
    private void handleAddProcess() {
        try {
            String currentAlgo = algorithmTxt.getText();
            String fxmlFile = (currentAlgo != null && currentAlgo.equalsIgnoreCase("Priority")) 
                              ? "/fxml/AddProcessPriority.fxml" 
                              : "/fxml/AddProcessStandard.fxml";

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            
            // Link this controller to the popup so it can send data back
            AddProcessStandardController popupController = loader.getController();
            popupController.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Add New Process");
            stage.setScene(new Scene(root, 333, 140));
            stage.initModality(Modality.NONE); // Change to NONE so you can click main window too
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewProcessFromPopup(String burst) {
        int newId = 7; // simplified logic for example
        ProcessModel p = new ProcessModel("P" + newId, (int) Double.parseDouble(burst));
        readyQueue.add(p);
        updateReadyQueueUI();
        
        // If simulation had finished, restart it
        if (!isRunning) {
            startSimulation();
        }
    }

    private void updateReadyQueueUI() {
        StringBuilder sb = new StringBuilder();
        for (ProcessModel p : readyQueue) sb.append(p.getId()).append(" ");
        readyQueueTxt.setText(sb.toString());
    }

    private void calculateAverages() { /* ... unchanged ... */ }

    private ProgressBar getBarForProcess(String id) {
        switch(id)
        {
            case "P1": return p1Bar; case "P2": return p2Bar;
            case "P3": return p3Bar; case "P4": return p4Bar;
            case "P5": return p5Bar; case "P6": return p6Bar;
            case "P7": return p7Bar; default: return null;
        }
    }

    private Text getTextForProcess(String id) {
        switch(id) {
            case "P1": return p1Burst; case "P2": return p2Burst;
            case "P3": return p3Burst; case "P4": return p4Burst;
            case "P5": return p5Burst; case "P6": return p6Burst;
            case "P7": return p7Burst; default: return null;
        }
    }
    
}