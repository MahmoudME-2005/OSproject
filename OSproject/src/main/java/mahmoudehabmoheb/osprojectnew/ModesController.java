/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import mahmoudehabmoheb.osproject.shedulers.FCFS;
import mahmoudehabmoheb.osproject.shedulers.Priority;
import mahmoudehabmoheb.osproject.shedulers.SJF;

/**
 * FXML Controller class
 *
 * @author Salsbil
 */
public class ModesController extends SceneController
{
    @FXML private Button staticModeBtn, dynamicModeBtn, backBtn;
    
    @FXML
    private void handleStaticMode()
    {
        ModesController.scheduler.set_dynamic(false);
        switchToScene("/fxml/CPU_Scheduling_op.fxml");
    }
    
    @FXML
    private void handleDynamicMode()
    {
        ModesController.scheduler.set_dynamic(true);
        switchToScene("/fxml/CPU_Scheduling_op.fxml");
    }
    
    @FXML
    private void handleBack()
    {
        if (ModesController.scheduler instanceof FCFS)
        {
            switchToScene("/fxml/FCFS.fxml");
        }
        else if (ModesController.scheduler instanceof SJF)
        {
            switchToScene("/fxml/SJF.fxml");
        }
        else if (ModesController.scheduler instanceof Priority)
        {
            switchToScene("/fxml/priority.fxml");
        }
        else
        {
            switchToScene("/fxml/RR.fxml");
        }

        ModesController.scheduler.set_currentTime(0);
    }
}
