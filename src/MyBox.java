import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MyBox extends Pane {

    ImageView boxView;

    int Y = 0;
    int X = 0;
    final int DISTANCE = 64;
    int deltaDistance = 0;
   
    public MyBox(int y, int x) {
        Y = y;
        X = x;

        boxView = new ImageView();

        int num = (int)(Math.random()*4)+1;
        if(num == 1) boxView.setImage(Dungeon.box1);
        if(num == 2) boxView.setImage(Dungeon.box2);
        if(num == 3) boxView.setImage(Dungeon.box3);
        if(num == 4) boxView.setImage(Dungeon.box4);
        boxView.setLayoutX(384+(x-1)*64);
        boxView.setLayoutY(132+(y-1)*64);
    }


    public boolean moveNorth(Chunk[][] map, int d) {

        // if( (!map[Y-1][X].isBlocked) || (map[Y-1][X].box!=null && map[Y-1][X].box.moveNorth(map, d)) ) {
        if( !map[Y-1][X].isBlocked && !map[Y-1][X].isEnd) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateY(boxView.getTranslateY() - 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveSouth(Chunk[][] map, int d) {
        
        // if( (!map[Y+1][X].isBlocked)) || (map[Y+1][X].box!=null && map[Y+1][X].box.moveSouth(map, d)) ) {
        if( !map[Y+1][X].isBlocked && !map[Y+1][X].isEnd) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateY(boxView.getTranslateY() + 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveWest(Chunk[][] map, int d) {

        // if( (!map[Y][X-1].isBlocked) || (map[Y][X-1].box!=null && map[Y][X-1].box.moveWest(map, d)) ) {
        if( !map[Y][X-1].isBlocked && !map[Y][X-1].isEnd) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateX(boxView.getTranslateX() - 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveEast(Chunk[][] map, int d) {

        // if( (!map[Y][X+1].isBlocked) || (map[Y][X+1].box!=null && map[Y][X+1].box.moveEast(map, d)) ) {
        if( !map[Y][X+1].isBlocked && !map[Y][X+1].isEnd) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateX(boxView.getTranslateX() + 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

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
