/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mahmoudehabmoheb.osproject.Process;

/**
 * FXML Controller class
 *
 * @author Salsbil
 */
public class FCFSController extends SceneController
{
    @FXML private TextField burstField;
    @FXML private GridPane GridBrstPri;
    @FXML private Button addBtn, startBtn;

    private int currentRow = 1; // Row 0 is the header

    // 1. Back to Landing Page
    @FXML
    private void handleBackToLanding(javafx.scene.input.MouseEvent event)
    {
        Process.set_counter(0);
        this.switchToScene("/fxml/Landing_Page.fxml");
    }

    // 2. Add Data to Table (GridPane)
    @FXML
    private void handleAdd()
    {
        if (Process.get_counter() <= 10)
        {
            String burst = this.burstField.getText();

            if (!burst.isEmpty())
            {
                // Add to internal list for the next scene
                FCFSController.scheduler.add_Process(new Process(
                    Integer.parseInt(burst)
                ));

                // Add UI Labels to the GridPane
                addLabelToGrid("P" + Process.get_counter(), 0, this.currentRow);
                addLabelToGrid(burst, 1, this.currentRow);

                this.currentRow++;
                clearInputs();
            }
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

    // 3. Start Simulation and Pass Data
    @FXML
    private void handleStartAction()
    {
        FCFSController.algo = 0;
        switchToScene("/fxml/Modes.fxml");
    }

    private void clearInputs()
    {
        this.burstField.clear();
    }
}
