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
import mahmoudehabmoheb.osproject.shedulers.Priority;
import mahmoudehabmoheb.osproject.shedulers.SJF;

public class CPU_Scheduling_opController extends SceneController
{

    @FXML private TextField algorithmTxt, cpuTxt, readyQueueTxt;
    @FXML private TextField avgWaitTxt, avgTurnTxt, totalExecTxt;
    @FXML private ProgressBar p1Bar, p2Bar, p3Bar, p4Bar, p5Bar, p6Bar, p7Bar;
    @FXML private Text p1Burst, p2Burst, p3Burst, p4Burst, p5Burst, p6Burst, p7Burst;
    
    @FXML
    private void initialize()
    {
        switch (CPU_Scheduling_opController.algo) {
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
        
        CPU_Scheduling_opController.scheduler.get_currentTimeProperty().addListener((obs, oldValue, newValue) -> {
            this.totalExecTxt.setText("" + newValue);
        });
        
        CPU_Scheduling_opController.scheduler.get_currentProcessProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null || newValue.get_id() == 0)
            {
                this.cpuTxt.setText("Idle");
            }
            else
            {
                this.cpuTxt.setText("P" + newValue.get_id());
            }
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