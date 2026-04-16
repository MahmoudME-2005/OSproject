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
import javafx.geometry.Orientation;
import mahmoudehabmoheb.osproject.shedulers.Priority;
import mahmoudehabmoheb.osproject.shedulers.SJF;
import mahmoudehabmoheb.osproject.Process;

public class CPU_Scheduling_opController extends SceneController
{
    @FXML private TextField algorithmTxt, cpuTxt;
    @FXML private ListView readyQueueTxt;
    @FXML private TextField avgWaitTxt, avgTurnTxt, totalExecTxt;
    @FXML private ProgressBar p1Bar, p2Bar, p3Bar, p4Bar, p5Bar, p6Bar, p7Bar, p8Bar, p9Bar, p10Bar;
    @FXML private Text p1Burst, p2Burst, p3Burst, p4Burst, p5Burst, p6Burst, p7Burst, p8Burst, p9Burst, p10Burst;
    @FXML private Text p1RemainingBurst, p2RemainingBurst, p3RemainingBurst, p4RemainingBurst, p5RemainingBurst, p6RemainingBurst, p7RemainingBurst, p8RemainingBurst, p9RemainingBurst, p10RemainingBurst;
    @FXML private Text p1Priority, p2Priority, p3Priority, p4Priority, p5Priority, p6Priority, p7Priority, p8Priority, p9Priority, p10Priority;
    
    @FXML
    private void initialize()
    {
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
        
        this.avgWaitTxt.setText("" + 0);
        
        this.avgTurnTxt.setText("" + 0);
        
        this.totalExecTxt.setText("" + 0);
        
        CPU_Scheduling_opController.scheduler.get_currentTimeProperty().addListener((obs, oldValue, newValue) -> {
            this.totalExecTxt.setText("" + newValue);
            
            if (CPU_Scheduling_opController.scheduler.get_currentProcess() != null)
            {
//                CPU_Scheduling_opController.scheduler.get_currentProcess().get_remainingBurstTimeProperty().addListener((burstObs, burstOldValue, burstNewValue) -> {
//                    switch (CPU_Scheduling_opController.scheduler.get_currentProcess().get_id())
//                    {
//                        case 1:
//                            this.p1RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 2:
//                            this.p2RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 3:
//                            this.p3RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 4:
//                            this.p4RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 5:
//                            this.p5RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 6:
//                            this.p6RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 7:
//                            this.p7RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 8:
//                            this.p8RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 9:
//                            this.p9RemainingBurst.setText("" + burstNewValue);
//                            break;
//                        case 10:
//                            this.p10RemainingBurst.setText("" + burstNewValue);
//                            break;      
//                    }
//                });
            }
        });
        
        CPU_Scheduling_opController.scheduler.get_currentProcessProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null)
            {
                this.cpuTxt.setText("Idle");
            }
            else
            {
                this.cpuTxt.setText("P" + newValue.get_id());
                
                newValue.get_remainingBurstTimeProperty().addListener((burstObs, burstOldValue, burstNewValue) -> {
                    switch (CPU_Scheduling_opController.scheduler.get_currentProcess().get_id())
                    {
                        case 1:
                            this.p1RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 2:
                            this.p2RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 3:
                            this.p3RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 4:
                            this.p4RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 5:
                            this.p5RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 6:
                            this.p6RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 7:
                            this.p7RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 8:
                            this.p8RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 9:
                            this.p9RemainingBurst.setText("" + burstNewValue);
                            break;
                        case 10:
                            this.p10RemainingBurst.setText("" + burstNewValue);
                            break;      
                    }
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

    private ProgressBar getBarForProcess(String id)
    {
        switch(id)
        {
            case "P1": return p1Bar; case "P2": return p2Bar;
            case "P3": return p3Bar; case "P4": return p4Bar;
            case "P5": return p5Bar; case "P6": return p6Bar;
            case "P7": return p7Bar; default: return null;
        }
    }

    private Text getTextForProcess(String id)
    {
        switch(id) {
            case "P1": return p1Burst; case "P2": return p2Burst;
            case "P3": return p3Burst; case "P4": return p4Burst;
            case "P5": return p5Burst; case "P6": return p6Burst;
            case "P7": return p7Burst; default: return null;
        }
    }
}