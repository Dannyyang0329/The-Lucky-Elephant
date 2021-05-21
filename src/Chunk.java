import javafx.scene.image.ImageView;

public class Chunk {
    ImageView imageView;
    
    boolean isBlocked = false;
    boolean isDangered = false;


    public Chunk(ImageView imageView, boolean isBlocked, boolean isDangered) {
        this.imageView = imageView;
        this.isBlocked = isBlocked;
        this.isDangered = isDangered;
    }
}
