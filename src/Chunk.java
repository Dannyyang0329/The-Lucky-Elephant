import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Chunk extends Pane {
    
    ImageView imageView = new ImageView();
    MyBox box = null;

    boolean isBlocked = false;
    boolean isDangered = false;
    boolean isEnd = false;
    boolean isStart = false;


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

    public void setStart(boolean b) {
        if(b == true) isStart = true;
        else isStart = false;
    }

    public void makeBox(int y, int x) {
        box = new MyBox(y, x);
    }

}
