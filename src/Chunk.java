import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Chunk extends Pane {
    
    ImageView imageView = new ImageView();

    MyBox box = null;
    Trap trap = null;

    boolean isBlocked = false;
    boolean isDangered = false;
    boolean isEnd = false;
    boolean isStart = false;
    boolean isSpecial = false;


    public void makeBox(int y, int x, int height, int width) {
        box = new MyBox(y, x, height, width);
    }

    public void makeTrap(int y, int x, int height, int width, boolean isTrap) {
        trap = new Trap(y, x, height, width, isTrap);
    }

}
