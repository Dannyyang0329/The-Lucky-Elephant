import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MyBox extends Pane {

    ImageView boxView;
    TranslateTransition translate;

    int Y = 0;
    int X = 0;

    final int DISTANCE = 64;
    int deltaDistance = 0;
   

    public MyBox(int y, int x, int height, int width) {
        Y = y;
        X = x;

        boxView = new ImageView();
        translate = new TranslateTransition();

        int num = (int)(Math.random()*4)+1;
        if(num == 1) boxView.setImage(Elephant.box1);
        if(num == 2) boxView.setImage(Elephant.box2);
        if(num == 3) boxView.setImage(Elephant.box3);
        if(num == 4) boxView.setImage(Elephant.box4);
        boxView.setLayoutX((1152-width*64)/2 + X*64);
        boxView.setLayoutY((648-height*64)/2 + Y*64);

        translate.setNode(boxView);
        translate.setDuration(Duration.millis(250));
    }


    public boolean moveNorth(Chunk[][] map) {
        if(!map[Y-1][X].isBlocked && !map[Y-1][X].isEnd && !map[Y-1][X].isSpecial && map[Y-1][X].trap==null) {
            translate.setByX(0);
            translate.setByY(-64);
            translate.play();
            
            map[Y][X].isBlocked = false;
            map[Y-1][X].isBlocked = true;

            map[Y-1][X].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y-1][X].box.setChunk(Y-1,X);

            return true;
        }
        else return false;
    }

    public boolean moveSouth(Chunk[][] map) {
        if(!map[Y+1][X].isBlocked && !map[Y+1][X].isEnd && !map[Y+1][X].isSpecial && map[Y+1][X].trap==null) {
            translate.setByX(0);
            translate.setByY(64);
            translate.play();
            
            map[Y][X].isBlocked = false;
            map[Y+1][X].isBlocked = true;

            map[Y+1][X].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y+1][X].box.setChunk(Y+1,X);

            return true;
        }
        else return false;
    }

    public boolean moveWest(Chunk[][] map) {
        if(!map[Y][X-1].isBlocked && !map[Y][X-1].isEnd && !map[Y][X-1].isSpecial && map[Y][X-1].trap==null) {
            translate.setByX(-64);
            translate.setByY(0);
            translate.play();
            
            map[Y][X].isBlocked = false;
            map[Y][X-1].isBlocked = true;

            map[Y][X-1].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y][X-1].box.setChunk(Y,X-1);

            return true;
        }
        else return false;
    }

    public boolean moveEast(Chunk[][] map) {
        if(!map[Y][X+1].isBlocked && !map[Y][X+1].isEnd && !map[Y][X+1].isSpecial && map[Y][X+1].trap==null) {
            translate.setByX(64);
            translate.setByY(0);
            translate.play();
            
            map[Y][X].isBlocked = false;
            map[Y][X+1].isBlocked = true;

            map[Y][X+1].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y][X+1].box.setChunk(Y,X+1);

            return true;
        }
        else return false;
    }
    
    public void setChunk(int y, int x) {
        Y = y;
        X = x;
    }
}
