/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mahmoudehabmoheb.osprojectnew;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mahmoudehabmoheb.osproject.shedulers.SJF;
import mahmoudehabmoheb.osproject.Process;

/**
 * FXML Controller class
 *
 * @author Salsbil
 */
public class SJFController extends SceneController
{
    @FXML private TextField burstField;
    @FXML private CheckBox preemptionCheckBox;
    @FXML private GridPane GridBrstPri;
    @FXML private Button addBtn, startBtn;

    private int currentRow = 1; // Row 0 is the header

    @FXML
    private void handleBackToLanding()
    {
        Process.set_counter(0);
        this.switchToScene("/fxml/Landing_Page.fxml");
    }

    @FXML
    private void handleToggle()
    {
        if (this.preemptionCheckBox.isSelected())
        {
            ((SJF)SJFController.scheduler).set_preemptive(true);
        }
        else
        {
            ((SJF)SJFController.scheduler).set_preemptive(false);
        }
    }
    
    @FXML
    private void handleAdd()
    {
        String burst = this.burstField.getText();

        if (!burst.isEmpty())
        {
            // Add to internal list for the next scene
            SJFController.scheduler.add_Process(new Process(
                Integer.parseInt(burst)
            ));

            // Add UI Labels to the GridPane
            addLabelToGrid("P" + Process.get_counter(), 0, this.currentRow);
            addLabelToGrid(burst, 1, this.currentRow);

            this.currentRow++;
            clearInputs();
        }
    }

    private void addLabelToGrid(String text, int col, int row)
    {
        Label label = new Label(text);
        label.setFont(new Font("Tahoma", 14));
        VBox container = new VBox(label);
        container.setAlignment(Pos.CENTER);
        GridBrstPri.add(container, col, row);
    }

    @FXML
    private void handleStartAction()
    {
        SJFController.algo = 1;
        switchToScene("/fxml/Modes.fxml");
    }

    private void clearInputs()
    {
        this.burstField.clear();
    }
}
