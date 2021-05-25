import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Chunk extends Pane {
    
    ImageView imageView = new ImageView(Dungeon.coin);

    boolean isBlocked = false;
    boolean isDangered = false;
    boolean isEnd = false;
    boolean isMoveable = false;

    MyBox box;

    public void setImageView(Image image) {
        imageView.setImage(image);
    }

    public void setBlocked(boolean b) {
        if(b == true) isBlocked = true;
        else isBlocked = false;
    }

    public void setDangered(boolean b) {
        if(b == true) isDangered = true;
        else isDangered = false;
    }

    public void setEnd(boolean b) {
        if(b == true) isEnd = true;
        else isEnd = false;
    }

    public void setMoveable(boolean b) {
        if(b == true) isMoveable = true;
        else isMoveable = false;
    }

    public void makeBox(int x, int y) {
        box = new MyBox(x, y);
    }

}
