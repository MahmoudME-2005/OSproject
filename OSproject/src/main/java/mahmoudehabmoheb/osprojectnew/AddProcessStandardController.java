package mahmoudehabmoheb.osprojectnew;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mahmoudehabmoheb.osproject.Process;

public class AddProcessStandardController extends SceneController
{
    @FXML private TextField burstTimeField;
    @FXML private Button addProcessBtn, backBtn;

    @FXML
    private void handleAdd()
    {
        if (Process.get_counter() < 10)
        {
            AddProcessStandardController.scheduler.add_Process(new Process(
                Integer.parseInt(this.burstTimeField.getText())
            ));

            this.burstTimeField.clear();
        }
    }

    @FXML
    private void handleBack()
    {
        ((Stage) this.addProcessBtn.getScene().getWindow()).close();
    }
}