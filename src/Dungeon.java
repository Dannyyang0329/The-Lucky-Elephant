import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.lang.Character;

public class Dungeon extends Application
{
    public Stage stage;
    public Scene scene;
    public Parent root;

    public static Image iconImage;
    public static Image zeldaImage;
    public static Image backgroundImage;
    public static Image zeldaSpriteImage;

    public static MediaPlayer beginPlayer;

    public static int levelStatus[] = new int[]{
        1, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0 
    };

    public static void main(String[] args) throws Exception {
        loadImages();
        loadMusics();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        readLevelInfo();
        
        root = FXMLLoader.load(getClass().getResource("begin.fxml"));

        initializeStage();
        initializeScene();

        // Character potato = new Character(new ImageView(zeldaSpriteImage));
        // potato.animation.play();
        // root.getChildren().add(potato);        

        stage.setScene(scene);
        stage.show();

        // AnimationTimer timer = new AnimationTimer() {
        //     @Override
        //     public void handle(long now) {
        //         int dx = 0, dy = 0;
 
        //         if (north) dy -= 1;
        //         if (south) dy += 1;
        //         if (east)  dx += 1;
        //         if (west)  dx -= 1;
 
        //         if(dx == 0 && dy == 0) potato.wink();
        //         else {
        //             potato.moveX(dx);
        //             potato.moveY(dy);
        //         }
        //     }
        // };
        // timer.start();
    }

    public static void loadImages() {
        iconImage = new Image("Images\\iconImage.png");
        zeldaImage= new Image("Images\\zeldaImage.jpg");
        backgroundImage = new Image("Images\\game.jpg");
        zeldaSpriteImage = new Image("Images\\zeldaSprite.png");
    }

    public static void loadMusics() {
        Media beginMusic = new Media(new File("src\\Songs\\beginMusic.mp3").toURI().toString());
        beginPlayer = new MediaPlayer(beginMusic);
        beginPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        beginPlayer.play();
    }


    private void initializeStage() {

        // initialize the stage
        stage = new Stage();
        stage.setTitle("Escape The Dungeon");
        stage.getIcons().add(iconImage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1152);
        stage.setHeight(648);
    }

    private void initializeScene() {

        scene = new Scene(root, 1152, 648);

        // scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
        //     @Override
        //     public void handle(KeyEvent e) {
        //         KeyCode in = e.getCode();

        //         if(in == KeyCode.W) north = true;
        //         else if(in == KeyCode.A) west = true;
        //         else if(in == KeyCode.S) south = true;
        //         else if(in == KeyCode.D) east = true;
        //     }
        // });
            
        // scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
        //     @Override
        //     public void handle(KeyEvent e) {
        //         KeyCode in = e.getCode();

        //         if(in == KeyCode.W) north = false;
        //         else if(in == KeyCode.A) west = false;
        //         else if(in == KeyCode.S) south = false;
        //         else if(in == KeyCode.D) east = false;
        //     }
        // });
    }

    public static void readLevelInfo() throws Exception {
        FileReader fr = new FileReader("src\\LevelInfo.txt");

        char[] info = new char[150];
        int tmp = fr.read(info);
        
        // System.out.println(info);
        for(int i=0, level=0; i<tmp ; i++) {

            if(Character.isDigit(info[i])) {
                levelStatus[level++] = Character.getNumericValue(info[i]);
            }
            else if(info[i] == '<') break;
        }

        fr.close();
    }

    public static void writeLevelInfo(boolean isNewGame) throws Exception
    {
        FileWriter fw = new FileWriter("src\\LevelInfo.txt", false);

        if(isNewGame == true) {
            for(int i=0 ; i<24 ; i++) {
                if(i == 0) fw.write(">>> 1, ");
                else if(i == 23) fw.write("0<<<");
                else fw.write("0, ");
            }
        }
        else {
            for(int i=0 ; i<24 ; i++) {
                if(i == 0) fw.write((levelStatus[i] == 1) ? ">>> 1, " : ">>> 0, ");
                else if(i == 23) fw.write((levelStatus[i] == 1) ? "1<<<" : "0<<<");
                else fw.write((levelStatus[i] == 1) ? "1, " : "0, ");
            }
        }

        fw.close();
    } 
}
