import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MyBox extends Pane {

    ImageView boxView;

    int Y = 0;
    int X = 0;
    final int DISTANCE = 64;
    int deltaDistance = 0;
   
    TranslateTransition translate;;

    public MyBox(int y, int x) {
        Y = y;
        X = x;

        boxView = new ImageView();
        translate = new TranslateTransition();

        int num = (int)(Math.random()*4)+1;
        if(num == 1) boxView.setImage(Dungeon.box1);
        if(num == 2) boxView.setImage(Dungeon.box2);
        if(num == 3) boxView.setImage(Dungeon.box3);
        if(num == 4) boxView.setImage(Dungeon.box4);
        boxView.setLayoutX(384+(x-1)*64);
        boxView.setLayoutY(132+(y-1)*64);

        translate.setNode(boxView);
        translate.setDuration(Duration.millis(250));
    }


    public boolean moveNorth(Chunk[][] map) {
        if(!map[Y-1][X].isBlocked && !map[Y-1][X].isEnd) {
            translate.setByX(0);
            translate.setByY(-64);
            translate.play();
            
            map[Y][X].setBlocked(false);
            map[Y-1][X].setBlocked(true);

            map[Y-1][X].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y-1][X].box.setChunk(Y-1,X);

            return true;
        }
        else return false;
    }

    public boolean moveSouth(Chunk[][] map) {
        if(!map[Y+1][X].isBlocked && !map[Y+1][X].isEnd) {
            translate.setByX(0);
            translate.setByY(64);
            translate.play();
            
            map[Y][X].setBlocked(false);
            map[Y+1][X].setBlocked(true);

            map[Y+1][X].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y+1][X].box.setChunk(Y+1,X);

            return true;
        }
        else return false;
    }

    public boolean moveWest(Chunk[][] map) {
        if(!map[Y][X-1].isBlocked && !map[Y][X-1].isEnd) {
            translate.setByX(-64);
            translate.setByY(0);
            translate.play();
            
            map[Y][X].setBlocked(false);
            map[Y][X-1].setBlocked(true);

            map[Y][X-1].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y][X-1].box.setChunk(Y,X-1);

            return true;
        }
        else return false;
    }

    public boolean moveEast(Chunk[][] map) {
        if(!map[Y][X+1].isBlocked && !map[Y][X+1].isEnd) {
            translate.setByX(64);
            translate.setByY(0);
            translate.play();
            
            map[Y][X].setBlocked(false);
            map[Y][X+1].setBlocked(true);

            map[Y][X+1].box = map[Y][X].box;
            map[Y][X].box = null;
            map[Y][X+1].box.setChunk(Y,X+1);

            return true;
        }
        else return false;
    }
    // public boolean moveNorth(Chunk[][] map, int d) {

    //     // if( (!map[Y-1][X].isBlocked) || (map[Y-1][X].box!=null && map[Y-1][X].box.moveNorth(map, d)) ) {
    //     if( !map[Y-1][X].isBlocked && !map[Y-1][X].isEnd) {
    //         for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
    //             boxView.setTranslateY(boxView.getTranslateY() - 2);
    //             deltaDistance += 2;
    //         }
    //         return true;
    //     }

    //     return false;
    // }

    // public boolean moveSouth(Chunk[][] map, int d) {
        
    //     // if( (!map[Y+1][X].isBlocked)) || (map[Y+1][X].box!=null && map[Y+1][X].box.moveSouth(map, d)) ) {
    //     if( !map[Y+1][X].isBlocked && !map[Y+1][X].isEnd) {
    //         for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
    //             boxView.setTranslateY(boxView.getTranslateY() + 2);
    //             deltaDistance += 2;
    //         }
    //         return true;
    //     }

    //     return false;
    // }

    // public boolean moveWest(Chunk[][] map, int d) {

    //     // if( (!map[Y][X-1].isBlocked) || (map[Y][X-1].box!=null && map[Y][X-1].box.moveWest(map, d)) ) {
    //     if( !map[Y][X-1].isBlocked && !map[Y][X-1].isEnd) {
    //         for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
    //             boxView.setTranslateX(boxView.getTranslateX() - 2);
    //             deltaDistance += 2;
    //         }
    //         return true;
    //     }

    //     return false;
    // }

    // public boolean moveEast(Chunk[][] map, int d) {

    //     // if( (!map[Y][X+1].isBlocked) || (map[Y][X+1].box!=null && map[Y][X+1].box.moveEast(map, d)) ) {
    //     if( !map[Y][X+1].isBlocked && !map[Y][X+1].isEnd) {
    //         for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
    //             boxView.setTranslateX(boxView.getTranslateX() + 2);
    //             deltaDistance += 2;
    //         }
    //         return true;
    //     }

    //     return false;
    // }

    // TranslateTransition translate = new TranslateTransition();
    // translate.
    // public boolean moveNorth() {

    // }
    // public boolean moveSouth() {

    // }
    // public boolean moveWest() {

    // }
    // public boolean moveEast() {

    // }
        


    public void setChunk(int y, int x) {
        Y = y;
        X = x;
    }
}
