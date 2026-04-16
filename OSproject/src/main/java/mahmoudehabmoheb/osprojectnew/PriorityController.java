package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import mahmoudehabmoheb.osproject.Process;
import mahmoudehabmoheb.osproject.shedulers.Priority;

public class PriorityController extends SceneController
{
    @FXML private TextField burstField, priorityField;
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
            ((Priority)PriorityController.scheduler).set_preemptive(true);
        }
        else
        {
            ((Priority)PriorityController.scheduler).set_preemptive(false);
        }
    }
    
    @FXML
    private void handleAdd()
    {
        String burst = this.burstField.getText();
        String priority = this.priorityField.getText();

        if (!burst.isEmpty() && !priority.isEmpty())
        {
            // Add to internal list for the next scene
            PriorityController.scheduler.add_Process(new Process(
                Integer.parseInt(burst),
                Integer.parseInt(priority)
            ));

            // Add UI Labels to the GridPane
            addLabelToGrid("P" + Process.get_counter(), 0, this.currentRow);
            addLabelToGrid(burst, 1, this.currentRow);
            addLabelToGrid(priority, 2, this.currentRow);

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
        PriorityController.algo = 2;
        switchToScene("/fxml/Modes.fxml");
    }

    private void clearInputs()
    {
        this.burstField.clear();
        this.priorityField.clear();
    }
}