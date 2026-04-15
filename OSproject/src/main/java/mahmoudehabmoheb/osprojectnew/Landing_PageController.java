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

public class Landing_PageController {

    @FXML private TextField processCountField;
    @FXML private ComboBox<String> algorithmComboBox;

    @FXML
    public void initialize() {
        if (algorithmComboBox != null && algorithmComboBox.getItems().isEmpty()) {
            algorithmComboBox.getItems().addAll("FCFS", "SJF", "Round Robin", "Priority");
        }
    }

    // THIS IS THE METHOD CAUSING THE ERROR
    // Make sure it is exactly 'handleTeamInfoAction' and has @FXML
    @FXML
public void handleTeamInfoAction(ActionEvent event) {
//    try {
//        // Use the absolute path starting from the resources root
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/TeamInfo.fxml"));
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(new Scene(root));
//        stage.show();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
    try {
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamInfo.fxml"));
        Parent root = loader.load();

        
        Scene scene = new Scene(root);
        
        
        Stage stage = new Stage();
        stage.setTitle("Team Information");
        
       
        stage.initModality(Modality.APPLICATION_MODAL); 
        
        stage.setScene(scene);
        stage.setResizable(false); 
        stage.show(); 

    } catch (IOException e) {
        System.err.println("Error: Could not load TeamInfo.fxml. Check the file path.");
        e.printStackTrace();
    }
}

    @FXML
    public void handleStartAction() {
        try {
            String algo = algorithmComboBox.getValue();
            String countStr = processCountField.getText();
            
            if (algo == null || countStr.isEmpty()) return;
            
            int count = Integer.parseInt(countStr);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CPU_Scheduling_op.fxml"));
            Parent root = loader.load();

            CPU_Scheduling_opController controller = loader.getController();
            controller.setupSimulation(algo, count);

            App.setRootNode(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}