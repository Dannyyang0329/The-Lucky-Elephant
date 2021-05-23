import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameLevel1 {
    protected Group root;
    public Scene scene;

    protected static int WIDTH;
    protected static int HEIGHT;

    protected ImageView mapImageView[][];

    Timer pauseTimer;
    Character potato;
    Chunk[][] map;

    protected boolean isPressed = false;
    protected static boolean north, south, west, east;

    private static int[][] m = new int[][] {
        {1, 1, 2 ,1, 1, 3, 3, 1},
        {1, 0, 0 ,0, 1, 0, 0, 1},
        {1, 0, 0 ,0, 1, 0, 0, 2},
        {2, 0, 1 ,0, 0, 0, 0, 1},
        {1, 0, 1 ,0, 0, 0, 0, 1},
        {1, 1, 1 ,2, 1, 3, 3, 3}
    };
    
    public GameLevel1(int w, int h) {
        WIDTH = w;
        HEIGHT = h;

        root = new Group();

        transformMap();
        setMapProperties();
        setHero();
        root.getChildren().add(potato);        
        
        scene = new Scene(root, 1152, 648, Color.BLACK);
        addKeyPressListener();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
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
                mapImageView[i][j] = new ImageView(Dungeon.mapImage[m[i][j]]);
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

                if(m[i][j] != 0) map[i][j].setBlocked(true);
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
