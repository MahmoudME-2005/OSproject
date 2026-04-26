package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import mahmoudehabmoheb.osproject.shedulers.FCFS;
import mahmoudehabmoheb.osproject.shedulers.SJF;
import mahmoudehabmoheb.osproject.shedulers.RoundRobin;
import mahmoudehabmoheb.osproject.shedulers.Priority;

public class Landing_PageController extends SceneController
{
    @FXML private ComboBox<String> algorithmComboBox;

    @FXML
    public void initialize()
    {
        if (this.algorithmComboBox != null && this.algorithmComboBox.getItems().isEmpty())
        {
            this.algorithmComboBox.getItems().addAll("FCFS", "SJF", "Priority", "Round Robin");
        }
    }

    // THIS IS THE METHOD CAUSING THE ERROR
    // Make sure it is exactly 'handleTeamInfoAction' and has @FXML
    @FXML
    public void handleTeamInfoAction()
    {
        this.switchToScene("/fxml/TeamInfo.fxml");
    }

   @FXML
    public void handleStartAction()
    {
        String algo = this.algorithmComboBox.getValue();
        
        if (algo.equals("FCFS"))
        {
            Landing_PageController.scheduler = new FCFS();
            switchToScene("/fxml/FCFS.fxml");
        }
        else if (algo.equals("Round Robin"))
        {
            Landing_PageController.scheduler = new RoundRobin();
            switchToScene("/fxml/RR.fxml");
        }
        else if (algo.equals("SJF"))
        {
            Landing_PageController.scheduler = new SJF();
            switchToScene("/fxml/SJF.fxml");
        }
        else
        {
            Landing_PageController.scheduler = new Priority();
            switchToScene("/fxml/priority.fxml");
        }
    }
}