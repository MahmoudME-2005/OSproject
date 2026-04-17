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
import mahmoudehabmoheb.osproject.shedulers.Scheduler;

/**
 *
 * @author Mahmoud Ehab
 */
/*
 All controllers that involves scene switching functionality
 should inherit from this controller and use the switchToScene
 method
*/
public class SceneController
{
    private static Stage stage;
    private Scene scene;
    private Parent root;
    protected static Scheduler<?> scheduler;
    protected static Scheduler<?> tempScheduler;
    protected static int algo;

    public void switchToScene(String fxmlFilePath)
    {
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

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setStage(Stage primaryStage)
    {
        stage = primaryStage;
    }
}
