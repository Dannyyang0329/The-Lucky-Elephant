import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameLevel {

    private Group root = new Group();
    public Scene scene;

    private Label completeLabel, failLabel;
    private Button backButton;

    Character potato;
    Chunk[][] map;

    private int level; 
    private int height;
    private int width;
    private int strength=0;

    private int startPointX=0, startPointY=0;
    private boolean isNewGame = false;
    private boolean isKeyPressed = false;
    private static boolean north, south, west, east;

    private int[][][] numberMap; 


    private long previousTime = 0;

    private AnimationTimer gameLoop;
    private AnimationTimer labelTimer;
    private AnimationTimer pauseTimer = new AnimationTimer() {

        @Override
        public void handle(long now) {
            if(previousTime == 0) previousTime = now;
            if(now-previousTime > 250000000) {
                isKeyPressed = false;
                north = false;  
                east = false;
                south = false;
                west = false;
                previousTime = 0;
                pauseTimer.stop();
            }
        }
    };

    public GameLevel(int level, int height, int width, int streng, int[][][] m) {

        this.level = level;
        this.height = height;
        this.width = width;
        this.numberMap = m;
        this.strength = streng;

        scene = new Scene(root, 1152, 648, Color.BLACK);

        setBackButton();
        transformMap();
        setMapProperties();
        setCharacter();
        setEndGameLabel();
        
        addKeyPressListener();

        // game loop setting
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                System.out.println(strength);
                // potato walk or wink
                potatoWalk();

                // if potato is on the end point -> switch to next level
                if(map[potato.Y][potato.X].isEnd && isNewGame==false) {
                    isNewGame = true;
                    completeLabel.setVisible(true);
                    showLabel(true);
                }

                // if potato's strength is zero -> restart
                if(strength < 0 && isNewGame==false) {
                    isNewGame = true;
                    failLabel.setVisible(true);
                    showLabel(false);
                }
            }
        };

        // game loop start
        gameLoop.start();
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
            gameLoop.stop();

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


    public void transformMap() {

        map = new Chunk[height][width];

        for(int i=0 ; i<height ; i++) {
            for(int j=0 ; j<width ; j++) {
                map[i][j] = new Chunk();

                if(numberMap[level][i][j] == 9) map[i][j].setImageView(Dungeon.reel);
                else if(numberMap[level][i][j] == 8) map[i][j].setImageView(Dungeon.thronIn);
                else if(numberMap[level][i][j] == 7) map[i][j].setImageView(Dungeon.thronOut);
                else map[i][j].setImageView(Dungeon.mapImage[numberMap[level][i][j]]);

                map[i][j].imageView.setFitWidth(64);
                map[i][j].imageView.setFitHeight(64);
                map[i][j].imageView.setY(68+64*i);
                map[i][j].imageView.setX(320+64*j);

                root.getChildren().add(map[i][j].imageView);
            }
        }
    }

    public void setMapProperties() {

        for(int i=0 ; i<height ; i++) {
            for(int j=0 ; j<width ; j++) {

                if(numberMap[level][i][j]==1 || numberMap[level][i][j]==2 || numberMap[level][i][j]==3 || numberMap[level][i][j]==5 ||
                   numberMap[level][i][j]==7) 
                {
                    map[i][j].setBlocked(true);
                }

                //box
                if(numberMap[level][i][j] == 4) {
                    map[i][j].setBlocked(true);
                    map[i][j].makeBox(i, j);
                    root.getChildren().add(map[i][j].box.boxView);
                }

                if(numberMap[level][i][j] == 9) {
                    map[i][j].setEnd(true);
                }

                if(numberMap[level][i][j] == 10) {
                    map[i][j].setStart(true);
                    startPointY = i;
                    startPointX = j;
                }
            }
        }
    }

    private void setCharacter() {
        potato = new Character(new ImageView(Dungeon.zeldaSpriteImage));
        potato.animation.play();
        potato.setLayoutX(320 + 64*startPointX);
        potato.setLayoutY(60 + 64*startPointY);
        potato.setChunk(startPointY, startPointX);
        root.getChildren().add(potato);        
    }

    private void setEndGameLabel() {
        completeLabel = new Label();
        completeLabel.setPrefSize(550, 280);
        completeLabel.setLayoutX(301);
        completeLabel.setLayoutY(184);
        completeLabel.setStyle("-fx-background-image:url(\"Images/levelComplete.png\")");
        completeLabel.setVisible(false);
        root.getChildren().add(completeLabel);

        failLabel = new Label();
        failLabel.setPrefSize(550, 280);
        failLabel.setLayoutX(301);
        failLabel.setLayoutY(184);
        failLabel.setStyle("-fx-background-image:url(\"Images/levelFail.png\")");
        failLabel.setVisible(false);
        root.getChildren().add(failLabel);
    }

    private void addKeyPressListener() {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent e) {
                if(isKeyPressed == false) {
                    KeyCode in = e.getCode();

                    if(in == KeyCode.R) {
                        gameLoop.stop();
                        GameLevel game = new GameLevel(level ,Dungeon.levelHeight[level], Dungeon.levelWidth[level], Dungeon.levelStrength[level], Dungeon.mapInfo);
                        Dungeon.stage.setScene(game.scene);
                    }

                    if(in == KeyCode.W) {

                        if(map[potato.Y-1][potato.X].isBlocked && map[potato.Y-1][potato.X].box==null) {
                            potato.direction = 'W';
                            return;
                        }

                        boolean isBoxMoved = (map[potato.Y-1][potato.X].box!=null && map[potato.Y-1][potato.X].box.moveNorth(map));
                        if((!map[potato.Y-1][potato.X].isBlocked) || isBoxMoved) {

                            if(isBoxMoved) strengthDecrease(2);
                            else strengthDecrease(1);

                            isKeyPressed = true;
                            north = true;
                            potato.Y--;
                            potato.deltaDistance = 0;

                            pauseTimer.start();
                        }
                    }

                    else if(in == KeyCode.S) {

                        if(map[potato.Y+1][potato.X].isBlocked && map[potato.Y+1][potato.X].box==null) {
                            potato.direction = 'S';
                            return;
                        }

                        boolean isBoxMoved = (map[potato.Y+1][potato.X].box!=null&&map[potato.Y+1][potato.X].box.moveSouth(map));
                        if((!map[potato.Y+1][potato.X].isBlocked) || isBoxMoved) {

                            if(isBoxMoved) strengthDecrease(2);
                            else strengthDecrease(1);

                            isKeyPressed = true;
                            south = true;
                            potato.Y++;
                            potato.deltaDistance = 0;

                            pauseTimer.start();
                        }                            
                    }

                    else if(in == KeyCode.A) {

                        if(map[potato.Y][potato.X-1].isBlocked && map[potato.Y][potato.X-1].box==null) {
                            potato.direction = 'A';
                            return;
                        }

                        boolean isBoxMoved = (map[potato.Y][potato.X-1].box!=null && map[potato.Y][potato.X-1].box.moveWest(map));
                        if((!map[potato.Y][potato.X-1].isBlocked) || isBoxMoved) {

                            if(isBoxMoved) strengthDecrease(2);
                            else strengthDecrease(1);

                            isKeyPressed = true;
                            west = true;
                            potato.X--;
                            potato.deltaDistance = 0;

                            pauseTimer.start();
                        }
                    }

                    else if(in == KeyCode.D) {
                        
                        if(map[potato.Y][potato.X+1].isBlocked&&map[potato.Y][potato.X+1].box==null) {
                            potato.direction = 'D';
                            return;
                        }

                        boolean isBoxMoved = (map[potato.Y][potato.X+1].box!=null && map[potato.Y][potato.X+1].box.moveEast(map));
                        if((!map[potato.Y][potato.X+1].isBlocked) || isBoxMoved) {

                            if(isBoxMoved) strengthDecrease(2);
                            else strengthDecrease(1);

                            isKeyPressed = true;
                            east = true;
                            potato.X++;
                            potato.deltaDistance = 0;

                            pauseTimer.start();
                        }
                    }
                }
            }
        });
    }


    // private void openNewGame() throws Exception {
    //     if(isNewGame == false) {
    //         isNewGame = true;

    //         Dungeon.levelStatus[level] = 1;
    //         Dungeon.writeLevelInfo(false);
    //         Dungeon.readLevelInfo();

    //         completeLabel.setVisible(true);

    //         labelTimer.start();
    //     }
    // }

    private void showLabel(boolean newGame) {

        labelTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                isKeyPressed = true;

                if(previousTime == 0) previousTime=now;
                if(now-previousTime >= 2.0e9) {
                    if(newGame == true) {
                        completeLabel.setVisible(false);
                        GameLevel game = new GameLevel(level+1 ,Dungeon.levelHeight[level+1], Dungeon.levelWidth[level+1], Dungeon.levelStrength[level+1], Dungeon.mapInfo);
                        Dungeon.stage.setScene(game.scene);
                        labelTimer.stop();
                        gameLoop.stop();
                    }
                    else {
                        failLabel.setVisible(false);
                        GameLevel game = new GameLevel(level ,Dungeon.levelHeight[level], Dungeon.levelWidth[level], Dungeon.levelStrength[level], Dungeon.mapInfo);
                        Dungeon.stage.setScene(game.scene);
                        labelTimer.stop();
                        gameLoop.stop();
                    }
                }
            }
        };
        labelTimer.start();
    }

    public void setEnd(int y, int x) {
        map[y][x].isEnd = true;
    }

    private void strengthDecrease(int n) {
        strength -= n;
    }

    private void potatoWalk() {

        int dx = 0, dy = 0;

        // determine potato is walking or not
        if (north) dy -= 1;
        if (south) dy += 1;
        if (east)  dx += 1;
        if (west)  dx -= 1;

        // potato is stop -> wink 
        if(dx == 0 && dy == 0) potato.wink();
        else {
            potato.moveX(dx);
            potato.moveY(dy);
        }
    }
}
