package mahmoudehabmoheb.osprojectnew;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Landing_PageController extends SceneController
{

    @FXML private TextField processCountField;
    @FXML private ComboBox<String> algorithmComboBox;

    @FXML
    public void initialize()
    {
        if (algorithmComboBox != null && algorithmComboBox.getItems().isEmpty()) {
            algorithmComboBox.getItems().addAll("FCFS", "SJF", "Round Robin", "Priority");
        }
    }

    // THIS IS THE METHOD CAUSING THE ERROR
    // Make sure it is exactly 'handleTeamInfoAction' and has @FXML
    @FXML
    public void handleTeamInfoAction(ActionEvent event)
    {
        switchToScene("/fxml/TeamInfo.fxml");
    }

   @FXML
    public void handleStartAction()
    {
        String algo = algorithmComboBox.getValue();
        String countStr = processCountField.getText();
        
        if (algo == null || countStr.isEmpty()) return;
        
        int count = Integer.parseInt(countStr);
        
        switchToScene("/fxml/CPU_Scheduling_op.fxml");
    }
}