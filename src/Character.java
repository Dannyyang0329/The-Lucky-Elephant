
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Character extends Pane {
    ImageView imageView;
    int count = 9;
    int column = 9;
    int offsetX = 0;
    int offsetY = 416;
    int width = 64;
    int height = 69;

    final int DISTANCE = 64;
    double deltaDistance = 0;
    char direction = 'S';
    int X=0;
    int Y=0;
    
    SpriteAnimation animation;

    public Character(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(600), count, column, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public void moveX(int dx) {
        boolean isGoingRight = (dx >= 0) ? true : false;

        for(int i=0 ; i<Math.abs(dx) && deltaDistance<DISTANCE; i++) {

            // going east
            if(isGoingRight) {
                direction = 'D';
                this.animation.setCount(9);
                this.animation.setOffsetY(485);
                this.setTranslateX(this.getTranslateX() + 4);
                deltaDistance += 4;
            }

            // going west
            else{
                direction = 'A';
                this.animation.setCount(9);
                this.animation.setOffsetY(346);
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
                this.animation.setCount(9);
                this.animation.setOffsetY(277);
                this.setTranslateY(this.getTranslateY() + 4);
                deltaDistance += 4;
            }

            // going north
            else
            {
                direction = 'W';
                this.animation.setCount(9);
                this.animation.setOffsetY(416); 
                this.setTranslateY(this.getTranslateY() - 4);
                deltaDistance += 4;
            }
        }
    }

    public void wink() {
        if(direction == 'W') {
            this.animation.setCount(1);
            this.animation.setOffsetY(138);
        }
        else if(direction == 'A') {
            this.animation.setCount(2);
            this.animation.setOffsetY(69);
        }
        else if(direction == 'S') {
            this.animation.setCount(2);
            this.animation.setOffsetY(0);
        }
        else if(direction == 'D') {
            this.animation.setCount(2);
            this.animation.setOffsetY(208);
        }
    }

    public void setChunk(int y, int x) {
        this.X = x;
        this.Y = y;
    }

}
