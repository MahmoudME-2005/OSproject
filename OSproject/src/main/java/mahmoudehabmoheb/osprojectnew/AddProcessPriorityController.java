/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mahmoudehabmoheb.osproject.Process;

/**
 * FXML Controller class
 *
 * @author Salsbil
 */
public class AddProcessPriorityController extends SceneController
{
    @FXML private TextField burstTimeField, priorityField;
    @FXML private Button addProcessBtn, backBtn;
    
    @FXML
    private void handleAdd()
    {
        AddProcessPriorityController.scheduler.add_Process(new Process(
            Integer.parseInt(this.burstTimeField.getText()),
            Integer.parseInt(this.priorityField.getText())
        ));
        
        clearInputs();
    }
    
    @FXML
    private void handleBack()
    {
        ((Stage) this.addProcessBtn.getScene().getWindow()).close();
    }
    
    private void clearInputs()
    {
        this.burstTimeField.clear();
        this.priorityField.clear();
    }
}
