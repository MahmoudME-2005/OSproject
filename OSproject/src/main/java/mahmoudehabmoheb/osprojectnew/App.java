package mahmoudehabmoheb.osprojectnew;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        SceneController.setStage(stage);
        scene = new Scene(loadFXML("/fxml/Landing_Page"), 640, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

 
    public static void setRootNode(Parent root) {
        scene.setRoot(root);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        
        String path = fxml.endsWith(".fxml") ? fxml : fxml + ".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}