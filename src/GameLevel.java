import java.io.IOException;
import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLevel {
    private Group root;
    public Scene scene;

    private int WIDTH;
    private int HEIGHT;
    private int level;

    private ImageView mapImageView[][];

    Timer pauseTimer;
    Character potato;
    Chunk[][] map;

    private boolean isPressed = false;
    private static boolean north, south, west, east;

    private int[][][] m; 

    private Button backButton;
    
    AnimationTimer timer;
    public GameLevel(int level, int w, int h, int[][][] m) {
        this.level = level;
        WIDTH = w;
        HEIGHT = h;
        this.m = m;

        root = new Group();

        setBackButton();
        transformMap();
        setMapProperties();
        setHero();
        root.getChildren().add(potato);        
        
        scene = new Scene(root, 1152, 648, Color.BLACK);
        addKeyPressListener();


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(map[potato.chunkY][potato.chunkX].isEnd) {
                    try {
                        newGame();
                    } catch (Exception e) {
                        System.out.println("New Game Failed.");
                    }
                }

                int dx = 0, dy = 0;
 
                if (north) dy -= 1;
                if (south) dy += 1;
                if (east)  dx += 1;
                if (west)  dx -= 1;
 
                if(dx == 0 && dy == 0) potato.wink();
                else {
                    potato.moveX(dx);
                    potato.moveY(dy);
                }
            }
        };
        timer.start();
    }

    public void transformMap() {
        mapImageView = new ImageView[HEIGHT][WIDTH];

        for(int i=0 ; i<HEIGHT ; i++) {
            for(int j=0 ; j<WIDTH ; j++) {
                if(m[level][i][j] == -1) mapImageView[i][j] = new ImageView(Dungeon.coin);
                else mapImageView[i][j] = new ImageView(Dungeon.mapImage[m[level][i][j]]);
                mapImageView[i][j].setFitWidth(64);
                mapImageView[i][j].setFitHeight(64);
                mapImageView[i][j].setX(320+64*j);
                mapImageView[i][j].setY(132+64*i);
                root.getChildren().add(mapImageView[i][j]);
            }
        }
    }

    public void setMapProperties() {
        map = new Chunk[HEIGHT][WIDTH];

        for(int i=0 ; i<HEIGHT ; i++) {
            for(int j=0 ; j<WIDTH ; j++) {
                map[i][j] = new Chunk();

                if(m[level][i][j] == -1) map[i][j].setEnd(true);

                if(m[level][i][j] != 0 && m[level][i][j] != -1) map[i][j].setBlocked(true);
                else map[i][j].setBlocked(false);
            }
        }
    }

    private void addKeyPressListener() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e) {
                if(isPressed == false) {
                    pauseTimer = new Timer();
                    KeyCode in = e.getCode();

                    if(in == KeyCode.W) {
                        if(map[potato.chunkY-1][potato.chunkX].isBlocked) {
                            potato.direction = 'W';
                            return;
                        }

                        isPressed = true;
                        north = true;
                        potato.chunkY--;
                        pauseTimer.schedule(new pause(), 500);
                    }
                    else if(in == KeyCode.A) {
                        if(map[potato.chunkY][potato.chunkX-1].isBlocked) {
                            potato.direction = 'A';
                            return;
                        }

                        isPressed = true;
                        west = true;
                        potato.chunkX--;
                        pauseTimer.schedule(new pause(), 500);
                    }
                    else if(in == KeyCode.S) {
                        if(map[potato.chunkY+1][potato.chunkX].isBlocked) {
                            potato.direction = 'S';
                            return;
                        }

                        isPressed = true;
                        south = true;
                        potato.chunkY++;
                        pauseTimer.schedule(new pause(), 500);
                    }
                    else if(in == KeyCode.D) {
                        if(map[potato.chunkY][potato.chunkX+1].isBlocked) {
                            potato.direction = 'D';
                            return;
                        }

                        isPressed = true;
                        east = true;
                        potato.chunkX++;
                        pauseTimer.schedule(new pause(), 500);
                    }
                }
            }
        });
    }

    private void setHero() {
        potato = new Character(new ImageView(Dungeon.zeldaSpriteImage));
        potato.animation.play();
        potato.setLayoutX(384);
        potato.setLayoutY(380);
        potato.setChunk(1, 4);
    }

    public void setEnd(int y, int x) {
        map[y][x].isEnd = true;
    }

    private void newGame() throws Exception {
        Dungeon.levelStatus[level] = 1;
        Dungeon.writeLevelInfo(false);
        Dungeon.readLevelInfo();

        // GameLevel game = new GameLevel(level+1,8,6, Dungeon.mapInfo);
        // game.setEnd(1,5);
        // Dungeon.stage.setScene(game.scene);
    }

    private void setBackButton() {
        // set back button
        ImageView backArrow = new ImageView(Dungeon.back);
        backArrow.setPreserveRatio(true);
        backArrow.setLayoutX(24);
        backArrow.setLayoutY(24);
        backArrow.setFitWidth(80);
        backArrow.setFitHeight(55);
        root.getChildren().add(backArrow);
        
        backButton = new Button();
        backButton.setLayoutX(24);
        backButton.setLayoutY(24);
        backButton.setPrefSize(80, 55);
        backButton.setOpacity(0);
        backButton.setOnAction( (event) -> {
            try {
                Dungeon.readLevelInfo();
            } catch (Exception e) {
                System.out.println("Loading LevelInfo failed.");
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("level.fxml"));
            Stage backStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Parent backRoot = null;
            try {
                backRoot = loader.load();
            } catch (IOException e) {
                System.out.println("level.fxml loading failed");
            }
            backStage.setScene(new Scene(backRoot));

            LevelController controller = loader.getController();
            controller.settingLock();
        });
        root.getChildren().add(backButton);
    }

    class pause extends TimerTask {
        @Override
        public void run() {
            isPressed = false;
            north = false;  
            east = false;
            south = false;
            west = false;
            pauseTimer.cancel();
        }
    }
}
