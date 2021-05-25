import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MyBox extends Pane {

    ImageView boxView = new ImageView(Dungeon.box);

    int chunkX = 0;
    int chunkY = 0;
    final int DISTANCE = 64;
    int deltaDistance = 0;
   
    public MyBox(int x, int y) {
        chunkX = x;
        chunkY = y;

        boxView.setLayoutX(384+(x-1)*64);
        boxView.setLayoutY(196+(y-1)*64);
    }

    public boolean moveEast(Chunk[][] map, int d) {

        if( (!map[chunkY][chunkX+1].isBlocked) || (map[chunkY][chunkX+1].box!=null && map[chunkY][chunkX+1].box.moveEast(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateX(boxView.getTranslateX() + 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveWest(Chunk[][] map, int d) {

        if( (!map[chunkY][chunkX-1].isBlocked) || (map[chunkY][chunkX-1].box!=null && map[chunkY][chunkX-1].box.moveWest(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateX(boxView.getTranslateX() - 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveNorth(Chunk[][] map, int d) {

        if( (!map[chunkY-1][chunkX].isBlocked) || (map[chunkY-1][chunkX].box!=null && map[chunkY-1][chunkX].box.moveNorth(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setLayoutY(boxView.getLayoutY() - 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public boolean moveSouth(Chunk[][] map, int d) {

        if( (!map[chunkY+1][chunkX].isBlocked) || (map[chunkY+1][chunkX].box!=null && map[chunkY+1][chunkX].box.moveSouth(map, d)) ) {
            for(int i=0 ; i<Math.abs(d) && deltaDistance<DISTANCE; i++) {
                boxView.setTranslateY(this.getTranslateY() + 2);
                deltaDistance += 2;
            }
            return true;
        }

        return false;
    }

    public void setChunk(int chunkX, int chunkY) {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
    }
}
