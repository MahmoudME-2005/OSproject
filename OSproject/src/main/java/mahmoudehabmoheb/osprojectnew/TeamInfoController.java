package mahmoudehabmoheb.osprojectnew;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TeamInfoController extends SceneController
{
    @FXML
    private void handleBackAction(ActionEvent event)
    {
        switchToScene("/fxml/Landing_Page.fxml");
    }
}