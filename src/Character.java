import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Character extends Pane {

    ImageView imageView;
    SpriteAnimation animation;

    int X=0;
    int Y=0;

    final int DISTANCE = 64;
    double deltaDistance = 0;
    char direction = 'S';
    

    public Character(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(0, 0, 64, 64));
        animation = new SpriteAnimation(imageView, Duration.millis(500), 3, 3, 0, 0, 64, 64);
        getChildren().addAll(imageView);
    }

    public void moveX(int dx) {
        boolean isGoingRight = (dx >= 0) ? true : false;

        for(int i=0 ; i<Math.abs(dx) && deltaDistance<DISTANCE; i++) {

            // going east
            if(isGoingRight) {
                direction = 'D';
                this.animation.setCount(3);
                this.animation.setOffsetX(0);
                this.animation.setOffsetY(128);
                this.setTranslateX(this.getTranslateX() + 4);
                deltaDistance += 4;
            }

            // going west
            else{
                direction = 'A';
                this.animation.setCount(3);
                this.animation.setOffsetX(0);
                this.animation.setOffsetY(64);
                this.setTranslateX(this.getTranslateX() - 4);
                deltaDistance += 4;
            }
        }
    }

    public void moveY(int dy) {
        boolean isGoingDown = (dy >= 0) ? true : false;

        for(int i=0 ; i<Math.abs(dy) && deltaDistance<DISTANCE; i++) {

            // going south
            if(isGoingDown) 
            {
                direction = 'S';
                this.animation.setCount(3);
                this.animation.setOffsetX(0);
                this.animation.setOffsetY(0);
                this.setTranslateY(this.getTranslateY() + 4);
                deltaDistance += 4;
            }

            // going north
            else
            {
                direction = 'W';
                this.animation.setCount(3);
                this.animation.setOffsetX(0);
                this.animation.setOffsetY(192); 
                this.setTranslateY(this.getTranslateY() - 4);
                deltaDistance += 4;
            }
        }
    }

    public void wink() {
        if(direction == 'W') {
            this.animation.setCount(1);
            this.animation.setOffsetX(64);
            this.animation.setOffsetY(192);
        }
        else if(direction == 'A') {
            this.animation.setCount(1);
            this.animation.setOffsetX(64);
            this.animation.setOffsetY(64);
        }
        else if(direction == 'S') {
            this.animation.setCount(1);
            this.animation.setOffsetX(64);
            this.animation.setOffsetY(0);
        }
        else if(direction == 'D') {
            this.animation.setCount(1);
            this.animation.setOffsetX(64);
            this.animation.setOffsetY(128);
        }
    }

    public void setChunk(int y, int x) {
        this.X = x;
        this.Y = y;
    }
}
