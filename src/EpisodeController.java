import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EpisodeController {

    @FXML
    Label label_1, label_2, label_3;
    
    // Back to initial screen
    public void back(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("begin.fxml"));
        stage.setScene(new Scene(root));
    }

    public void episodeOne(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level1_1.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        Level1_1Controller controller = loader.getController();
        controller.settingLock();
    }

    public void episodeTwo(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level2_1.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        Level2_1Controller controller = loader.getController();
        controller.settingLock();
    }

    public void episodeThree(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level3_1.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        Level3_1Controller controller = loader.getController();
        controller.settingLock();
    }

    public void settingLock() {
        if(Elephant.episodeStatus[1] == 1) label_1.setVisible(false);
        if(Elephant.episodeStatus[2] == 1) label_2.setVisible(false);
        if(Elephant.episodeStatus[3] == 1) label_3.setVisible(false);
    }
}