package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PriorityController {

    @FXML private TextField arrivalField, burstField, priorityField;
    @FXML private GridPane GridBrstPri;
    @FXML private Button addBtn, startBtn;

    private int currentRow = 1; // Row 0 is the header
    private List<ProcessData> processList = new ArrayList<>();

    // 1. Back to Landing Page
    @FXML
    private void handleBackToLanding(javafx.scene.input.MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/mahmoudehabmoheb/osprojectnew/landing_page.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. Add Data to Table (GridPane)
    @FXML
    private void handleAdd() {
        String arrival = arrivalField.getText();
        String burst = burstField.getText();
        String priority = priorityField.getText();

        if (!arrival.isEmpty() && !burst.isEmpty() && !priority.isEmpty()) {
            // Add to internal list for the next scene
            processList.add(new ProcessData(
                Integer.parseInt(arrival), 
                Integer.parseInt(burst), 
                Integer.parseInt(priority)
            ));

            // Add UI Labels to the GridPane
            addLabelToGrid(arrival, 0, currentRow);
            addLabelToGrid(burst, 1, currentRow);
            addLabelToGrid(priority, 2, currentRow);

            currentRow++;
            clearInputs();
        }
    }

    private void addLabelToGrid(String text, int col, int row) {
        Label label = new Label(text);
        label.setFont(new Font("Tahoma", 14));
        VBox container = new VBox(label);
        container.setAlignment(Pos.CENTER);
        GridBrstPri.add(container, col, row);
    }

    // 3. Start Simulation and Pass Data
    @FXML
    private void handleStartAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mahmoudehabmoheb/osprojectnew/CPU_Scheduling_op.fxml"));
            Parent root = loader.load();

//            // Get the simulation controller and pass the data
//            CPU_Scheduling_opController controller = loader.getController();
//            controller.initSimulationWithData("PRIORITY", processList);
//
//            Stage stage = (Stage) startBtn.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearInputs() {
        arrivalField.clear();
        burstField.clear();
        priorityField.clear();
    }

    // Helper class to hold process data
    public static class ProcessData {
        public int arrival, burst, priority;
        public ProcessData(int a, int b, int p) { this.arrival = a; this.burst = b; this.priority = p; }
    }
}