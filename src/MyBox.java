import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MyBox extends Pane {

    ImageView boxView = new ImageView(Dungeon.box);

    int Y = 0;
    int X = 0;
    final int DISTANCE = 64;
    int deltaDistance = 0;
   
    public MyBox(int y, int x) {
        Y = y;
        X = x;

        boxView.setLayoutX(384+(x-1)*64);
        boxView.setLayoutY(196+(y-1)*64);
    }


    public boolean moveNorth(Chunk[][] map, int d) {

        if( (!map[Y-1][X].isBlocked) || (map[Y-1][X].box!=null && map[Y-1][X].box.moveNorth(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateY(boxView.getTranslateY() - 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveSouth(Chunk[][] map, int d) {

        if( (!map[Y+1][X].isBlocked) || (map[Y+1][X].box!=null && map[Y+1][X].box.moveSouth(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateY(boxView.getTranslateY() + 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveWest(Chunk[][] map, int d) {

        if( (!map[Y][X-1].isBlocked) || (map[Y][X-1].box!=null && map[Y][X-1].box.moveWest(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateX(boxView.getTranslateX() - 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveEast(Chunk[][] map, int d) {

        if( (!map[Y][X+1].isBlocked) || (map[Y][X+1].box!=null && map[Y][X+1].box.moveEast(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateX(boxView.getTranslateX() + 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }



    public void setChunk(int y, int x) {
        Y = y;
        X = x;
    }
}
