import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller 
{

    public void newGame(ActionEvent e) throws Exception {
        Dungeon.writeLevelInfo(true);
        Dungeon.readLevelInfo();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("level.fxml"));

        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        LevelController controller = loader.getController();
        controller.settingLock();
    }

    public void continueGame(ActionEvent e) throws Exception {
        Dungeon.readLevelInfo();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("level.fxml"));

        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        LevelController controller = loader.getController();
        controller.settingLock();
    }

    public void exit(ActionEvent e) throws IOException {
        System.exit(0);
    }

}
