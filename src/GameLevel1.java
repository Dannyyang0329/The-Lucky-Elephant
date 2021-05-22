import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameLevel1 extends Map {
    private Group root;
    public Scene scene;

    private static final int WIDTH = 8;
    private static final int HEIGHT = 6;
    private ImageView[][] pic;

    Timer pauseTimer;
    boolean isPressed = false;
    static boolean north, south, west, east;
    Character potato;

    // private static int[][] m = new int[][] {
    //     {1, 2, 3, 4, 5, 2, 3, 6},
    //     {7, 8, 9, 10, 2, 10, 11, 12},
    //     {13, 14, 15, 16, 28, 16, 17, 18},
    //     {7, 8, 2, 9, 10, 10, 11, 12},
    //     {13, 14, 2, 15, 16,  16, 17, 18},
    //     {25, 26, 27, 28, 27, 26, 29, 30}
    // };

    private static int[][] m = new int[][] {
        {1, 1, 2 ,1, 1, 3, 3, 1},
        {1, 0, 0 ,0, 1, 0, 0, 1},
        {1, 0, 0 ,0, 1, 0, 0, 2},
        {2, 0, 1 ,0, 0, 0, 0, 1},
        {1, 0, 1 ,0, 0, 0, 0, 1},
        {1, 1, 1 ,2, 1, 3, 3, 3}
    };
    
    public GameLevel1() {
        super(WIDTH, HEIGHT, m);
        root = new Group();
        pic = new ImageView[HEIGHT][WIDTH];

        potato = new Character(new ImageView(Dungeon.zeldaSpriteImage));
        potato.animation.play();
        potato.setLayoutX(384);
        potato.setLayoutY(380);

        for(int i=0 ; i<HEIGHT ; i++) {
            for(int j=0 ; j<WIDTH ; j++) {
                pic[i][j] = new ImageView(image[m[i][j]]);
                pic[i][j].setFitWidth(64);
                pic[i][j].setFitHeight(64);
                pic[i][j].setX(320+64*j);
                pic[i][j].setY(132+64*i);
                root.getChildren().add(pic[i][j]);
            }
        }
        root.getChildren().add(potato);        

        scene = new Scene(root, 1152, 648, Color.BLACK);


        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e) {
                if(isPressed == false) {
                    pauseTimer = new Timer();
                    KeyCode in = e.getCode();

                    if(in == KeyCode.W) {
                        isPressed = true;
                        north = true;
                        pauseTimer.schedule(new pause(), 500);
                    }
                    else if(in == KeyCode.A) {
                        isPressed = true;
                        west = true;
                        pauseTimer.schedule(new pause(), 500);
                    }
                    else if(in == KeyCode.S) {
                        isPressed = true;
                        south = true;
                        pauseTimer.schedule(new pause(), 500);
                    }
                    else if(in == KeyCode.D) {
                        isPressed = true;
                        east = true;
                        pauseTimer.schedule(new pause(), 500);
                    }
                }
            }
        });
            
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
