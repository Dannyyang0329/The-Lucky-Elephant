import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

public class Elephant extends Application
{
    
    public static Stage stage;
    private Scene scene;
    private Parent root;


    // Images in the game.
    public static Image iconImage;
    public static Image diary;
    public static Image back;
    public static Image blackImage;

    public static Image box1;
    public static Image box2;
    public static Image box3;
    public static Image box4;
    public static Image soundPlayer;
    public static Image box;
    public static Image stone;
    public static Image beer;
    public static Image bookshelf;
    public static Image glassPieces;
    public static Image elephant;
    public static Image flyingElephant;
    public static Image plant;
    public static Image redbull;

    public static Image reel;
    public static Image stacyImage;
    public static Image rice;
    
    public static Image dreamedGura;
    public static Image gura;
    public static Image dreamedTeacher;
    public static Image teacher;
    public static Image worker;
    public static Image salman;
    public static Image stacy;

    public static Image map1_1;
    public static Image map1_2;
    public static Image map1_3;
    public static Image map1_4;
    public static Image map1_5;
    public static Image map1_6;
    public static Image map1_7;
    public static Image map1_8;
    public static Image map1_9;
    public static Image map1_10;
    public static Image map1_11;
    public static Image map1_12;
    public static Image map2_1;
    public static Image map2_2;
    public static Image map2_3;
    public static Image map2_4;
    public static Image map2_5;
    public static Image map2_6;
    public static Image map2_7;
    public static Image map2_8;
    public static Image map2_9;
    public static Image map3_1;
    public static Image map3_2;
    public static Image map3_3;
    public static Image map3_4;
    public static Image map3_5;
    public static Image map3_6;
    public static Image map3_7;
    public static Image map3_8;
    public static Image map3_9;
    public static Image map3_10;
    public static Image map3_11;
    public static Image map3_12;
    public static Image map3_13;
    public static Image map3_14;
    public static Image map3_15;

    public static Image thronInImage;
    public static Image thronOutImage;

    // Images (.gif)
    public static Image thronOut;
    public static Image thronIn;
    public static Image blood;


    
    // Musics in the game.
    public static MediaPlayer beginPlayer;
    public static MediaPlayer episodePlayer1;
    public static MediaPlayer episodePlayer2;
    public static MediaPlayer episodePlayer3;
    public static MediaPlayer endPlayer;



    //  Map's Infomation 
    public static int[][][] mapInfo;
    public static int[] levelWidth;
    public static int[] levelHeight;
    public static int[] levelStrength;
    public static boolean[] levelSpecial;
    public static int[] levelCharacter;



    // Level's information (Completed or not)
    public static int episodeStatus[] = new int[]{-1, 1, 0, 0};
    public static int levelStatus[] = new int[]{ 
       -1,
        1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
    };



    // Main function
    public static void main(String[] args) throws Exception {
        launch();
    }



    @Override
    public void start(Stage primaryStage) throws Exception  {

        loadImages();
        loadMusics();
        loadMap();
        readLevelInfo();
        
        root = FXMLLoader.load(getClass().getResource("begin.fxml"));
        stage = new Stage();
        stage.setTitle("The Lucky Elephant");
        stage.getIcons().add(iconImage);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setWidth(1152);
        stage.setHeight(648);
        scene = new Scene(root, 1152, 648);
        stage.setScene(scene);
        stage.show();
    }


    
    private void loadImages() {

        iconImage = new Image("resources\\Images\\iconImage.png");
        back = new Image("resources\\Images\\back.png");
        blackImage = new Image("resources\\Images\\blackImage.jpg");
        diary = new Image("resources\\Images\\diary.jpg");

        box1 = new Image("resources\\Images\\box1.png");
        box2 = new Image("resources\\Images\\box2.png");
        box3 = new Image("resources\\Images\\box3.png");
        box4 = new Image("resources\\Images\\box4.png");
        soundPlayer = new Image("resources\\Images\\soundPlayer.png");
        box = new Image("resources\\Images\\box.png");
        stone = new Image("resources\\Images\\stone.png");
        beer = new Image("resources\\Images\\beer.png");
        bookshelf = new Image("resources\\Images\\bookshelf.png");
        glassPieces = new Image("resources\\Images\\glassPieces.png");
        elephant = new Image("resources\\Images\\elephant.png");                    
        flyingElephant = new Image("resources\\Images\\flyingElephant.png");
        redbull = new Image("resources\\Images\\redbull.png");
        plant = new Image("resources\\Images\\plant.png");

        reel = new Image("resources\\Images\\reel.png");
        stacyImage = new Image("resources\\Images\\stacyImage.png");
        rice = new Image("resources\\Images\\rice.png");

        dreamedGura = new Image("resources\\Images\\dreamedGura.png");
        gura = new Image("resources\\Images\\gura.png");
        dreamedTeacher = new Image("resources\\Images\\dreamedTeacher.png");
        teacher = new Image("resources\\Images\\teacher.png");
        worker = new Image("resources\\Images\\worker.png");
        salman = new Image("resources\\Images\\salman.png");
        stacy = new Image("resources\\Images\\stacy.png");

        map1_1 = new Image("resources\\Images\\map1_1.png");
        map1_2 = new Image("resources\\Images\\map1_2.png");
        map1_3 = new Image("resources\\Images\\map1_3.png");
        map1_4 = new Image("resources\\Images\\map1_4.png");
        map1_5 = new Image("resources\\Images\\map1_5.png");
        map1_6 = new Image("resources\\Images\\map1_6.png");
        map1_7 = new Image("resources\\Images\\map1_7.png");
        map1_8 = new Image("resources\\Images\\map1_8.png");
        map1_9 = new Image("resources\\Images\\map1_9.png");
        map1_10 = new Image("resources\\Images\\map1_10.png");
        map1_11 = new Image("resources\\Images\\map1_11.png");
        map1_12 = new Image("resources\\Images\\map1_12.png");
        map2_1 = new Image("resources\\Images\\map2_1.png");
        map2_2 = new Image("resources\\Images\\map2_2.png");
        map2_3 = new Image("resources\\Images\\map2_3.png");
        map2_4 = new Image("resources\\Images\\map2_4.png");
        map2_5 = new Image("resources\\Images\\map2_5.png");
        map2_6 = new Image("resources\\Images\\map2_6.png");
        map2_7 = new Image("resources\\Images\\map2_7.png");
        map2_8 = new Image("resources\\Images\\map2_8.png");
        map2_9 = new Image("resources\\Images\\map2_9.png");
        map3_1 = new Image("resources\\Images\\map3_1.png");
        map3_2 = new Image("resources\\Images\\map3_2.png");
        map3_3 = new Image("resources\\Images\\map3_3.png");
        map3_4 = new Image("resources\\Images\\map3_4.png");
        map3_5 = new Image("resources\\Images\\map3_5.png");
        map3_6 = new Image("resources\\Images\\map3_6.png");
        map3_7 = new Image("resources\\Images\\map3_7.png");
        map3_8 = new Image("resources\\Images\\map3_8.png");
        map3_9 = new Image("resources\\Images\\map3_9.png");
        map3_10 = new Image("resources\\Images\\map3_10.png");
        map3_11 = new Image("resources\\Images\\map3_11.png");
        map3_12 = new Image("resources\\Images\\map3_12.png");
        map3_13 = new Image("resources\\Images\\map3_13.png");
        map3_14 = new Image("resources\\Images\\map3_14.png");
        map3_15 = new Image("resources\\Images\\map3_15.png");

        thronInImage = new Image("resources\\Images\\thronInImage.png");
        thronOutImage = new Image("resources\\Images\\thronOutImage.png");
        thronIn = new Image(getClass().getResource("resources\\Images\\thronIn.gif").toExternalForm());
        thronOut = new Image(getClass().getResource("resources\\Images\\thronOut.gif").toExternalForm());
        blood = new Image(getClass().getResource("resources\\Images\\blood.gif").toExternalForm());
    }


    
    private void loadMusics() {
        Media beginMusic = new Media(new File("src\\resources\\Songs\\beginMusic.mp3").toURI().toString());
        beginPlayer = new MediaPlayer(beginMusic);
        beginPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        beginPlayer.play();

        Media episodeMusic1 = new Media(new File("src\\resources\\Songs\\episode1.mp3").toURI().toString());
        episodePlayer1 = new MediaPlayer(episodeMusic1);
        episodePlayer1.setCycleCount(MediaPlayer.INDEFINITE);

        Media episodeMusic2 = new Media(new File("src\\resources\\Songs\\episode2.mp3").toURI().toString());
        episodePlayer2 = new MediaPlayer(episodeMusic2);
        episodePlayer2.setCycleCount(MediaPlayer.INDEFINITE);

        Media episodeMusic3 = new Media(new File("src\\resources\\Songs\\episode3.mp3").toURI().toString());
        episodePlayer3 = new MediaPlayer(episodeMusic3);
        episodePlayer3.setCycleCount(MediaPlayer.INDEFINITE);

        Media endMusic = new Media(new File("src\\resources\\Songs\\endMusic.mp3").toURI().toString());
        endPlayer = new MediaPlayer(endMusic);
        endPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }


    
    private void loadMap() throws IOException {

        // mapInfo[level][height][width]
        FileReader fr = new FileReader("src\\resources\\Txt\\mapInfomation.txt");
        BufferedReader br = new BufferedReader(fr);

        mapInfo = new int[37][20][20];
        levelWidth = new int[37];
        levelHeight = new int[37];
        levelStrength = new int[37];
        levelSpecial = new boolean[37];
        levelCharacter = new int[37];

        int level = 0;
        int width = 0;
        int height = 0;
        int strength = 0;
        int special = 0;
        int character = 0;

        boolean start = false;
        boolean canReadInfo = false;

        String str = null;
        while( (str = br.readLine()) != null) {

            // When read the text "Level", it means that I can start reading the infomation.
            if(str.equals("Level")) {
                start = true;
                continue;
            }
            
            // Read the level, height and width, strength, specialItem and character
            if(start && !canReadInfo) {
                String[] tmp = str.split(" ");
                level = Integer.parseInt(tmp[0]);
                height =  Integer.parseInt(tmp[1]);
                width = Integer.parseInt(tmp[2]);
                strength = Integer.parseInt(tmp[3]);
                special = Integer.parseInt(tmp[4]);
                character = Integer.parseInt(tmp[5]);

                levelHeight[level] = height;
                levelWidth[level] = width;               
                levelStrength[level] = strength;
                levelCharacter[level] = character;

                if(special == 1) levelSpecial[level] = false;
                else levelSpecial[level] = true;

                canReadInfo = true;
                continue;
            }

            // Read the map
            if(start && canReadInfo) {
                for(int i=0 ; i<height ; i++) {
                    str = br.readLine().trim();
                    String[] tmp = str.split(",");
                    
                    for(int j=0 ; j<width ; j++) {
                        mapInfo[level][i][j] = Integer.parseInt(tmp[j]);
                    }
                }

                start = false;
                canReadInfo = false;
            }
        }

        br.close();
        fr.close();
    }


    public static void readLevelInfo() throws Exception {
        FileReader fr = new FileReader("src\\resources\\Txt\\LevelInfo.txt");

        char[] info = new char[150];
        int tmp = fr.read(info);
        
        for(int i=0, level=1, episode=1; i<tmp ; i++) {

            if(Character.isDigit(info[i])) {
                levelStatus[level++] = Character.getNumericValue(info[i]);
            }
            else if(info[i] == '<') {
                for(int j=i+1 ; j<tmp ; j++) {
                    if(Character.isDigit(info[j])) {
                        episodeStatus[episode++] = Character.getNumericValue(info[j]);
                    }
                    else if(info[j] == '<') {
                        fr.close();
                        return;
                    }
                }
            }
        }

        fr.close();
    }

    public static void writeLevelInfo(boolean isNewGame) throws Exception
    {
        FileWriter fw = new FileWriter("src\\resources\\Txt\\LevelInfo.txt", false);

        if(isNewGame == true) {
            for(int i=1 ; i<=36 ; i++) {
                if(i == 1) fw.write(">>> 1, ");
                else if(i == 36) fw.write("0<\n");
                else fw.write("0, ");
            }
            fw.write(">>>1, 0, 0<");
        }
        else {
            for(int i=1 ; i<=36 ; i++) {
                if(i == 1) fw.write((levelStatus[i] == 1) ? ">>> 1, " : ">>> 0, ");
                else if(i == 36) fw.write((levelStatus[i] == 1) ? "1<\n" : "0<\n");
                else fw.write((levelStatus[i] == 1) ? "1, " : "0, ");
            }
            for(int i=1 ; i<=3 ; i++) {
                if(i == 1) fw.write((episodeStatus[i] == 1) ? ">>> 1, " : ">>> 0, ");
                else if(i == 3) fw.write((episodeStatus[3] == 1) ? "1<" : "0<");
                else fw.write((episodeStatus[i] == 1) ? "1, " : "0, ");
            }
        }

        fw.close();
    } 
}