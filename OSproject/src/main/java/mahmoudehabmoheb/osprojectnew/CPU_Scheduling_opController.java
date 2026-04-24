package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import mahmoudehabmoheb.osproject.shedulers.Priority;
import mahmoudehabmoheb.osproject.shedulers.SJF;
import mahmoudehabmoheb.osproject.Process;
import javafx.scene.input.MouseEvent;

public class CPU_Scheduling_opController extends SceneController
{
    @FXML private TextField algorithmTxt, cpuTxt;
    @FXML private ListView readyQueueTxt;
    @FXML private TextField avgWaitTxt, avgTurnTxt, totalExecTxt;
    @FXML private ProgressBar p1Bar, p2Bar, p3Bar, p4Bar, p5Bar, p6Bar, p7Bar, p8Bar, p9Bar, p10Bar;
    @FXML private Text p1Burst, p2Burst, p3Burst, p4Burst, p5Burst, p6Burst, p7Burst, p8Burst, p9Burst, p10Burst;
    @FXML private Text p1RemainingBurst, p2RemainingBurst, p3RemainingBurst, p4RemainingBurst, p5RemainingBurst, p6RemainingBurst, p7RemainingBurst, p8RemainingBurst, p9RemainingBurst, p10RemainingBurst;
    @FXML private Text p1Priority, p2Priority, p3Priority, p4Priority, p5Priority, p6Priority, p7Priority, p8Priority, p9Priority, p10Priority;
    @FXML private VBox chartContainer; // Match the ID from FXML
    
    private String[] colors = {"#f34336", "#9c27b0", "#2196f3", "#009688", "#4caf50", "#ffeb3b", "#ff9800", "#795548", "#607d8b", "#FFFFFF", "#000000"};

    private int initialTime;
    
    private StackedBarChart<Number, String> gantChart;
    private NumberAxis xAxis;
    private CategoryAxis yAxis;
    
    @FXML
    private void initialize()
    {   
        xAxis = new NumberAxis();
        yAxis = new CategoryAxis();

        xAxis.setLabel("Time");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(20); // Initial view
        xAxis.setTickUnit(1);

        yAxis.setLabel("CPU");
        yAxis.setCategories(FXCollections.observableArrayList("CPU 1"));

        // 2. Create Chart manually
        gantChart = new StackedBarChart<>(xAxis, yAxis);
        gantChart.setAnimated(false);
        gantChart.setCategoryGap(0); // Make bars touch
        gantChart.setLegendVisible(false);

        // 3. Set the chart to fill the VBox
        VBox.setVgrow(gantChart, javafx.scene.layout.Priority.ALWAYS);
        chartContainer.getChildren().add(gantChart);
        switch (CPU_Scheduling_opController.algo)
        {
            case 0:
                algorithmTxt.setPromptText("FCFS");
                break;
            case 1:
                if (((SJF)CPU_Scheduling_opController.scheduler).is_preemptive())
                {
                    this.algorithmTxt.setPromptText("SJF (Preemptive)");
                }
                else
                {
                    this.algorithmTxt.setPromptText("SJF");
                }
                break;
            case 2:
                if (((Priority)CPU_Scheduling_opController.scheduler).is_preemptive())
                {
                    this.algorithmTxt.setPromptText("Priority (Preemptive)");
                }
                else
                {
                    this.algorithmTxt.setPromptText("Priority");
                }
                break;
            default:
                this.algorithmTxt.setPromptText("Round Robin");
                break;
        }

        for (Process P : (Iterable<Process>) (CPU_Scheduling_opController.scheduler.get_readyQueue()))
        {
            switch (P.get_id())
            {
                case 1:
                    this.p1RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p1Burst.setText("" + P.get_initialBurstTime());
                    this.p1Priority.setText("" + P.get_priority());
                    break;
                case 2:
                    this.p2RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p2Burst.setText("" + P.get_initialBurstTime());
                    this.p2Priority.setText("" + P.get_priority());
                    break;
                case 3:
                    this.p3RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p3Burst.setText("" + P.get_initialBurstTime());
                    this.p3Priority.setText("" + P.get_priority());
                    break;
                case 4:
                    this.p4RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p4Burst.setText("" + P.get_initialBurstTime());
                    this.p4Priority.setText("" + P.get_priority());
                    break;
                case 5:
                    this.p5RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p5Burst.setText("" + P.get_initialBurstTime());
                    this.p5Priority.setText("" + P.get_priority());
                    break;
                case 6:
                    this.p6RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p6Burst.setText("" + P.get_initialBurstTime());
                    this.p6Priority.setText("" + P.get_priority());
                    break;
                case 7:
                    this.p7RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p7Burst.setText("" + P.get_initialBurstTime());
                    this.p7Priority.setText("" + P.get_priority());
                    break;
                case 8:
                    this.p8RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p8Burst.setText("" + P.get_initialBurstTime());
                    this.p8Priority.setText("" + P.get_priority());
                    break;
                case 9:
                    this.p9RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p9Burst.setText("" + P.get_initialBurstTime());
                    this.p9Priority.setText("" + P.get_priority());
                    break;
                case 10:
                    this.p10RemainingBurst.setText("" + P.get_initialBurstTime());
                    this.p10Burst.setText("" + P.get_initialBurstTime());
                    this.p10Priority.setText("" + P.get_priority());
                    break;      
            }
        }

        this.readyQueueTxt.setItems(CPU_Scheduling_opController.scheduler.get_observableReadyQueue());
        this.readyQueueTxt.setCellFactory(lv -> new ListCell<Process>() {
            @Override
            protected void updateItem(Process p, boolean empty) {
                super.updateItem(p, empty);
                if (empty || p == null) {
                    setText(null);
                } else {
                    setText("P" + p.get_id());
                }
            }
        });
        
        this.avgWaitTxt.setText("" + 0);
        
        this.avgTurnTxt.setText("" + 0);
        
        this.totalExecTxt.setText("" + 0);
        
        CPU_Scheduling_opController.scheduler.get_currentTimeProperty().addListener((obs, oldValue, newValue) -> {
            this.totalExecTxt.setText("" + newValue);
            
            this.xAxis.setUpperBound((newValue.intValue()/20 + 1) * 20);
            this.xAxis.setLowerBound((newValue.intValue()/20) * 20);
        });
        
        CPU_Scheduling_opController.scheduler.get_currentProcessProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null)
            {
                this.cpuTxt.setText("Idle");
                this.initialTime = CPU_Scheduling_opController.scheduler.get_currentTimeProperty().get();
            }
            else
            {
                if (oldValue == null)
                {
                    addIdleTime(CPU_Scheduling_opController.scheduler.get_currentTimeProperty().get());
                }
                
                this.cpuTxt.setText("P" + newValue.get_id());
                
                
                newValue.get_remainingBurstTimeProperty().addListener((burstObs, burstOldValue, burstNewValue) -> {
                    switch (newValue.get_id())
                    {
                        case 1:
                            this.p1RemainingBurst.setText("" + burstNewValue);
                            this.p1Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 2:
                            this.p2RemainingBurst.setText("" + burstNewValue);
                            this.p2Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 3:
                            this.p3RemainingBurst.setText("" + burstNewValue);
                            this.p3Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 4:
                            this.p4RemainingBurst.setText("" + burstNewValue);
                            this.p4Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 5:
                            this.p5RemainingBurst.setText("" + burstNewValue);
                            this.p5Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 6:
                            this.p6RemainingBurst.setText("" + burstNewValue);
                            this.p6Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 7:
                            this.p7RemainingBurst.setText("" + burstNewValue);
                            this.p7Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 8:
                            this.p8RemainingBurst.setText("" + burstNewValue);
                            this.p8Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 9:
                            this.p9RemainingBurst.setText("" + burstNewValue);
                            this.p9Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;
                        case 10:
                            this.p10RemainingBurst.setText("" + burstNewValue);
                            this.p10Bar.setProgress(1 - ((double) newValue.get_remainingBurstTime()/newValue.get_initialBurstTime()));
                            break;      
                    }
                    
                    addBurst("P" + newValue.get_id(), newValue.get_id(),1);
                });
            }
        });
        
        CPU_Scheduling_opController.scheduler.get_averageWaitingTimeProperty().addListener((obs, oldValue, newValue) -> {
            this.avgWaitTxt.setText("" + newValue);
        });
        
        CPU_Scheduling_opController.scheduler.get_averageTurnAroundTimeProperty().addListener((obs, oldValue, newValue) -> {
            this.avgTurnTxt.setText("" + newValue);
        });
        
        Thread thread = new Thread(() -> {
            CPU_Scheduling_opController.scheduler.set_isRunning(true);
            CPU_Scheduling_opController.scheduler.schedule();
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    // THIS IS THE BUTTON METHOD
    @FXML
    private void handleAddProcess()
    {
        try
        {
            String fxmlFile;
            
            if (this.algo == 2)
            {
                fxmlFile = "/fxml/AddProcessPriority.fxml";
            }
            else
            {
                fxmlFile = "/fxml/AddProcessStandard.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add New Process");
            stage.setScene(new Scene(root, 333, 140));
            stage.initModality(Modality.NONE); // Change to NONE so you can click main window too
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void addBurst(String processName, int id, int duration)
    {
        Platform.runLater(() -> {
            XYChart.Series<Number, String> series = new XYChart.Series<>();
            series.setName(processName);

            XYChart.Data<Number, String> data = new XYChart.Data<>(duration, "CPU 1");
            series.getData().add(data);

            gantChart.getData().add(series);

            // --- THE COLOR FIX ---
            // We must wait for the node to be created to style it
            if (data.getNode() != null)
            {
                String color = colors[id % colors.length]; // Cycle colors if IDs > 10
                data.getNode().setStyle("-fx-bar-fill: " + color + "; -fx-background-color: " + color + ";");
            }
        });
    }
    
    public void addIdleTime(int finalTime)
    {   
        Platform.runLater(() -> {
            XYChart.Series<Number, String> idle = new XYChart.Series<>();
            XYChart.Data<Number, String> data = new XYChart.Data<>(finalTime - this.initialTime, "CPU 1");
            idle.getData().add(data);
            gantChart.getData().add(idle);

            // Make the idle block invisible
            data.getNode().setStyle("-fx-bar-fill: transparent; -fx-background-color: transparent;");
        });
    }

   @FXML
    private void handleBackAction() // Changed ActionEvent to MouseEvent
    {
        Process.set_counter(0);
        CPU_Scheduling_opController.scheduler.set_isRunning(false);
        switchToScene("/fxml/Modes.fxml");
    }

    @FXML
    private void handleBackActionhome() // Changed ActionEvent to MouseEvent
    {
        Process.set_counter(0);
        CPU_Scheduling_opController.scheduler.set_isRunning(false);
        switchToScene("/fxml/Landing_Page.fxml");
    }
}