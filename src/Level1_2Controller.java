import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Level1_2Controller {

    @FXML
    Label red11, red12;        

    // Back to episode screen
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("episode.fxml"));
        Stage backStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        backStage.setScene(new Scene(root));

        EpisodeController controller = loader.getController();
        controller.settingLock();
    }

    public void button11(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game11 = new GameLevel(11);
        stage.setScene(game11.scene);
    }

    public void button12(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game12 = new GameLevel(12);
        stage.setScene(game12.scene);
    }

    public void backPage(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level1_1.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        Level1_1Controller controller = loader.getController();
        controller.settingLock();
    }

    public void settingLock() {
        if(Elephant.levelStatus[11] == 1) red11.setVisible(false);
        if(Elephant.levelStatus[12] == 1) red12.setVisible(false);
    }
}
