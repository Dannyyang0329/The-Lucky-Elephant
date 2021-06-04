import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BeginController 
{

    @FXML
    ImageView elephantView;

    long previousTime = 0;
    AnimationTimer timer;

    public void newGame(ActionEvent e) throws Exception {

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

    public void credit(ActionEvent e) throws Exception {
        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if(previousTime == 0) previousTime = now; 
                if(now-previousTime < 3.0e9) elephantView.setVisible(true);
                else {
                    elephantView.setVisible(false);
                    previousTime = 0;
                    timer.stop();
                }
            }
        };

        timer.start();
    }

    public void exit(ActionEvent e) throws IOException {
        System.exit(0);
    }

}
