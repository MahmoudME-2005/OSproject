/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 *
 * @author Mahmoud Ehab
 */
public class SceneController {
    private static Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene(String fxmlFilePath) {
        // 1. Load the FXML file
        // Note: The path must be relative to the classpath. 
        // Based on your folder structure, it likely needs to start with "/view/"
        try
        {
            root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        }
        catch (IOException ex)
        {
            System.out.println("The page doesn't exist");
        }
        catch (Exception ex)
        {
            System.out.println("The page doesn't exist");
        }

        // 3. Create the new scene and set it on the stage
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setStage(Stage primaryStage)
    {
        stage = primaryStage;
    }
}
