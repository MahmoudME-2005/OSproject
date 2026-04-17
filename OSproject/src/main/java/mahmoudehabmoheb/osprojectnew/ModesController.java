/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.scene.control.Button;



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
    
//    @FXML
//    private void handleBack()
//    {
//        switch (ModesController.algo)
//        {
//            case 0:
//                switchToScene("/fxml/FCFS.fxml");
//                break;
//            case 1:
//                switchToScene("/fxml/SJF.fxml");
//                break;
//            case 2:
//                switchToScene("/fxml/priority.fxml");
//                break;
//            default:
//                switchToScene("/fxml/RR.fxml");
//                break;
//        }
//    }
}
