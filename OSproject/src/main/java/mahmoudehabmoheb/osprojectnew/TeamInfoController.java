package mahmoudehabmoheb.osprojectnew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TeamInfoController {

    @FXML
private void handleBackAction(ActionEvent event) {
    try {
        // Use the absolute path starting from the resources root
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Landing_Page.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}