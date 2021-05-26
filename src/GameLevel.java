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

    private int startPointX=0, startPointY=0;
    private boolean isNewGame = false;
    private boolean isKeyPressed = false;
    private boolean boxIsPushed = false;
    private static boolean north, south, west, east;

    private int[][][] numberMap; 


    private long previousTime = 0;

    private AnimationTimer timer;
    private AnimationTimer pauseTimer = new AnimationTimer() {

        public void handle(long now) {
            if(previousTime == 0) previousTime = now;
            if(now-previousTime > 300000000) {
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
    private AnimationTimer labelTimer = new AnimationTimer() {

        @Override
        public void handle(long now) {
            isKeyPressed = true;

            if(previousTime == 0) previousTime=now;
            if(now-previousTime >= 2.0e9) {
                completeLabel.setVisible(false);
                GameLevel game = new GameLevel(level+1 ,Dungeon.levelHeight[level+1], Dungeon.levelWidth[level+1], Dungeon.mapInfo);
                Dungeon.stage.setScene(game.scene);
                labelTimer.stop();
            }
        }
    };

    public GameLevel(int level, int h, int w, int[][][] m) {

        this.level = level;
        height = h;
        width = w;
        numberMap = m;

        scene = new Scene(root, 1152, 648, Color.BLACK);

        setBackButton();
        transformMap();
        setMapProperties();
        setCharacter();
        setEndGameLabel();
        
        addKeyPressListener();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(map[potato.Y][potato.X].isEnd && isNewGame==false) {
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
                    if(!boxIsPushed) {
                        potato.moveX(dx);
                        potato.moveY(dy);
                        System.out.println(potato.Y+" "+potato.X);
                    }
                    else {
                        // push the box to north
                        if(dy<0 && map[potato.Y][potato.X].box!=null && map[potato.Y][potato.X].box.moveNorth(map, dy)) {

                            potato.moveX(dx);
                            potato.moveY(dy);
                        System.out.println(potato.Y+" "+potato.X);

                            if(map[potato.Y][potato.X].box.deltaDistance == 64) {
                                map[potato.Y-1][potato.X].box = map[potato.Y][potato.X].box;
                                map[potato.Y][potato.X].box = null;
                                map[potato.Y-1][potato.X].box.setChunk(potato.Y-1, potato.X);

                                map[potato.Y][potato.X].setBlocked(false);
                                map[potato.Y-1][potato.X].setBlocked(true);

                                return;
                            }
                        }
                        if(dy<0 && map[potato.Y][potato.X].box!=null && !map[potato.Y][potato.X].box.moveNorth(map, dy)) {
                            potato.Y++;
                        }

                        // push the box to south
                        if(dy>0 && map[potato.Y][potato.X].box!=null && map[potato.Y][potato.X].box.moveSouth(map, dy)) {

                            potato.moveX(dx);
                            potato.moveY(dy);
                        System.out.println(potato.Y+" "+potato.X);

                            if(map[potato.Y][potato.X].box.deltaDistance == 64) {
                                map[potato.Y+1][potato.X].box = map[potato.Y][potato.X].box;
                                map[potato.Y][potato.X].box = null;
                                map[potato.Y+1][potato.X].box.setChunk(potato.Y+1, potato.X);

                                map[potato.Y][potato.X].setBlocked(false);
                                map[potato.Y+1][potato.X].setBlocked(true);

                                return;
                            }
                        }
                        if(dy>0 && map[potato.Y][potato.X].box!=null && !map[potato.Y][potato.X].box.moveSouth(map, dy)) {
                            potato.Y--;
                        }

                        // push the box to west
                        if(dx<0 && map[potato.Y][potato.X].box!=null && map[potato.Y][potato.X].box.moveWest(map, dx)) {

                            potato.moveX(dx);
                            potato.moveY(dy);
                        System.out.println(potato.Y+" "+potato.X);

                            if(map[potato.Y][potato.X].box.deltaDistance == 64) {
                                map[potato.Y][potato.X-1].box = map[potato.Y][potato.X].box;
                                map[potato.Y][potato.X].box = null;
                                map[potato.Y][potato.X-1].box.setChunk(potato.Y, potato.X-1);

                                map[potato.Y][potato.X].setBlocked(false);
                                map[potato.Y][potato.X-1].setBlocked(true);

                                return;
                            }
                        }
                        if(dx<0 && map[potato.Y][potato.X].box!=null && !map[potato.Y][potato.X].box.moveWest(map, dx)) {
                            potato.X++;
                        }

                        // push the box to east
                        if(dx>0 && map[potato.Y][potato.X].box!=null && map[potato.Y][potato.X].box.moveEast(map, dx)) {

                            potato.moveX(dx);
                            potato.moveY(dy);
                        System.out.println(potato.Y+" "+potato.X);

                            if(map[potato.Y][potato.X].box.deltaDistance == 64) {
                                map[potato.Y][potato.X+1].box = map[potato.Y][potato.X].box;
                                map[potato.Y][potato.X].box = null;
                                map[potato.Y][potato.X+1].box.setChunk(potato.Y, potato.X+1);

                                map[potato.Y][potato.X].setBlocked(false);
                                map[potato.Y][potato.X+1].setBlocked(true);

                                return;
                            }
                        }
                        if(dx>0 && map[potato.Y][potato.X].box!=null && !map[potato.Y][potato.X].box.moveEast(map, dx)) {
                            potato.X--;
                        }
                    }
                }
            }
        };
        timer.start();
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
            timer.stop();

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

                if(numberMap[level][i][j] == 9) map[i][j].setImageView(Dungeon.coin);
                else if(numberMap[level][i][j] == 8) map[i][j].setImageView(Dungeon.thronIn);
                else if(numberMap[level][i][j] == 7) map[i][j].setImageView(Dungeon.thronOut);
                else map[i][j].setImageView(Dungeon.mapImage[numberMap[level][i][j]]);

                map[i][j].imageView.setFitWidth(64);
                map[i][j].imageView.setFitHeight(64);
                map[i][j].imageView.setY(132+64*i);
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
        potato.setLayoutY(124 + 64*startPointY);
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

                    if(in == KeyCode.W) {

                        boxIsPushed = false;

                        if(map[potato.Y-1][potato.X].isBlocked && map[potato.Y-1][potato.X].box==null) {
                            potato.direction = 'W';
                            return;
                        }

                        if(map[potato.Y-1][potato.X].box != null) {
                            boxIsPushed = true;
                            map[potato.Y-1][potato.X].box.deltaDistance = 0;
                        }

                        isKeyPressed = true;
                        north = true;
                        potato.Y--;
                        potato.deltaDistance = 0;

                        pauseTimer.start();
                    }

                    else if(in == KeyCode.S) {

                        boxIsPushed = false;

                        if(map[potato.Y+1][potato.X].isBlocked && map[potato.Y+1][potato.X].box==null) {
                            potato.direction = 'S';
                            return;
                        }

                        if(map[potato.Y+1][potato.X].box != null) {
                            boxIsPushed = true;
                            map[potato.Y+1][potato.X].box.deltaDistance = 0;
                        }

                        isKeyPressed = true;
                        south = true;
                        potato.Y++;
                        potato.deltaDistance = 0;

                        pauseTimer.start();
                    }

                    else if(in == KeyCode.A) {

                        boxIsPushed = false;

                        if(map[potato.Y][potato.X-1].isBlocked && map[potato.Y][potato.X-1].box==null) {
                            potato.direction = 'A';
                            return;
                        }


                        if(map[potato.Y][potato.X-1].box != null) {
                            boxIsPushed = true;
                            map[potato.Y][potato.X-1].box.deltaDistance = 0;
                        }

                        isKeyPressed = true;
                        west = true;
                        potato.X--;
                        potato.deltaDistance = 0;

                        pauseTimer.start();
                    }

                    else if(in == KeyCode.D) {

                        boxIsPushed = false;
                        
                        if(map[potato.Y][potato.X+1].isBlocked && map[potato.Y][potato.X+1].box==null) {
                            potato.direction = 'D';
                            return;
                        }

                        if(map[potato.Y][potato.X+1].box != null) {
                            boxIsPushed = true;
                            map[potato.Y][potato.X+1].box.deltaDistance = 0;
                        }

                        isKeyPressed = true;
                        east = true;
                        potato.X++;
                        potato.deltaDistance = 0;

                        pauseTimer.start();
                    }
                }
            }
        });
    }


    private void newGame() throws Exception {
        if(isNewGame == false) {
            isNewGame = true;

            Dungeon.levelStatus[level] = 1;
            Dungeon.writeLevelInfo(false);
            Dungeon.readLevelInfo();

            completeLabel.setVisible(true);

            labelTimer.start();
        }
    }

    public void setEnd(int y, int x) {
        map[y][x].isEnd = true;
    }
}
