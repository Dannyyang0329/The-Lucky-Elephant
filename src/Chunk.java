import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Chunk extends Pane {
    
    ImageView imageView = new ImageView();
    ImageView subView = new ImageView();
    MyBox box = null;
    Trap trap = null;

    boolean isBlocked = false;
    boolean isDangered = false;
    boolean isEnd = false;
    boolean isStart = false;
    boolean isSpecial = false;


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

    public void setSpecial(boolean b) {
        if(b == true) isSpecial = true;
        else isSpecial = false;
    }

    public void makeBox(int y, int x, int height, int width) {
        box = new MyBox(y, x, height, width);
    }

    public void makeTrap(int y, int x, int height, int width, boolean isTrap) {
        trap = new Trap(y, x, height, width, isTrap);
    }

}
