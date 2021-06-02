import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BeginController 
{

    public void newGame(ActionEvent e) throws Exception {
        Elephant.episdoeStatus[2] = 0;
        Elephant.episdoeStatus[3] = 0;

        Elephant.writeLevelInfo(true);
        Elephant.readLevelInfo();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("episode.fxml"));

        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        EpisodeController controller = loader.getController();
        controller.settingLock();
    }

    public void continueGame(ActionEvent e) throws Exception {
        Elephant.readLevelInfo();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("episode.fxml"));

        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        EpisodeController controller = loader.getController();
        controller.settingLock();
    }

    public void exit(ActionEvent e) throws IOException {
        System.exit(0);
    }

}
