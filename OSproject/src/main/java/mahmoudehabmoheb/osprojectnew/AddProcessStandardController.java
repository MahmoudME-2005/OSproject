package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddProcessStandardController {

    @FXML private TextField readyQueueTxt;  // Burst Time
    @FXML private TextField readyQueueTxt1; // Priority
    @FXML private Button addProcessBtn;     // Back
    @FXML private Button addProcessBtn1;    // Add

    private CPU_Scheduling_opController mainController;

    public void setMainController(CPU_Scheduling_opController main) {
        this.mainController = main;
    }

    @FXML
    private void handleAdd() {
        if (mainController != null) {
            mainController.addNewProcessFromPopup(readyQueueTxt.getText());
        }
        closeWindow();
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) addProcessBtn.getScene().getWindow();
        stage.close();
    }
}