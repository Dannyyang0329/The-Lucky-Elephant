import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Level1_1Controller {

    @FXML
    Label red1, red2, red3, red4, red5, red6, red7, red8, red9, red10; 
        
    // Back to episode screen
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("episode.fxml"));
        Stage backStage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = loader.load();
        backStage.setScene(new Scene(root));

        EpisodeController controller = loader.getController();
        controller.settingLock();
    }

    public void button1(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game1 = new GameLevel(1);
        stage.setScene(game1.scene);
    }
    public void button2(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game2 = new GameLevel(2);
        stage.setScene(game2.scene);
    }
    public void button3(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game3 = new GameLevel(3);
        stage.setScene(game3.scene);
    }
    public void button4(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game4 = new GameLevel(4);
        stage.setScene(game4.scene);
    }
    public void button5(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game5 = new GameLevel(5);
        stage.setScene(game5.scene);
    }
    public void button6(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game6 = new GameLevel(6);
        stage.setScene(game6.scene);
    }
    public void button7(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game7 = new GameLevel(7);
        stage.setScene(game7.scene);
    }
    public void button8(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game8 = new GameLevel(8);
        stage.setScene(game8.scene);
    }
    public void button9(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game9 = new GameLevel(9);
        stage.setScene(game9.scene);
    }
    public void button10(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        GameLevel game10 = new GameLevel(10);
        stage.setScene(game10.scene);
    }
    
    public void nextPage(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("level1_2.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));

        Level1_2Controller controller = loader.getController();
        controller.settingLock();
    }

    public void settingLock() {
        if(Elephant.levelStatus[1] == 1) red1.setVisible(false);
        if(Elephant.levelStatus[2] == 1) red2.setVisible(false);
        if(Elephant.levelStatus[3] == 1) red3.setVisible(false);
        if(Elephant.levelStatus[4] == 1) red4.setVisible(false);
        if(Elephant.levelStatus[5] == 1) red5.setVisible(false);
        if(Elephant.levelStatus[6] == 1) red6.setVisible(false);
        if(Elephant.levelStatus[7] == 1) red7.setVisible(false);
        if(Elephant.levelStatus[8] == 1) red8.setVisible(false);
        if(Elephant.levelStatus[9] == 1) red9.setVisible(false);
        if(Elephant.levelStatus[10] == 1) red10.setVisible(false);
    }
}