import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Level3_2Controller {

    @FXML
    Label red11, red12, red13, red14, red15;        

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
        GameLevel game11 = new GameLevel(32);
        stage.setScene(game11.scene);
    }

    public void button12(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game12 = new GameLevel(33);
        stage.setScene(game12.scene);
    }

    public void button13(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game12 = new GameLevel(34);
        stage.setScene(game12.scene);
    }

    public void button14(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game12 = new GameLevel(35);
        stage.setScene(game12.scene);
    }

    public void button15(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game12 = new GameLevel(36);
        stage.setScene(game12.scene);
    }

    public void backPage(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level3_1.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        Level3_1Controller controller = loader.getController();
        controller.settingLock();
    }

    public void settingLock() {
        if(Elephant.levelStatus[32] == 1) red11.setVisible(false);
        if(Elephant.levelStatus[33] == 1) red12.setVisible(false);
        if(Elephant.levelStatus[34] == 1) red13.setVisible(false);
        if(Elephant.levelStatus[35] == 1) red14.setVisible(false);
        if(Elephant.levelStatus[36] == 1) red15.setVisible(false);
    }
}
