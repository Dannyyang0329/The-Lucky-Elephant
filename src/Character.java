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
    int physicalStrength = 100;
    char direction = 'S';
    
    SpriteAnimation animation;

    public Character(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(800), count, column, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public void moveX(int dx) {
        // if(dx == 0) return;

        boolean isGoingRight = (dx >= 0) ? true : false;

        for(int i=0 ; i<Math.abs(dx) ; i++) {
            if(isGoingRight) {
                direction = 'D';
                this.animation.setCount(9);
                this.animation.setOffsetY(485);
                this.setTranslateX(this.getTranslateX() + 2.1334);
            }
            else{
                direction = 'A';
                this.animation.setCount(9);
                this.animation.setOffsetY(346);
                this.setTranslateX(this.getTranslateX() - 2.1334);
            }
        }
    }

    public void moveY(int dy) {
        // if(dy == 0) return;

        boolean isGoingDown = (dy >= 0) ? true : false;

        for(int i=0 ; i<Math.abs(dy) ; i++) {
            if(isGoingDown) 
            {
                direction = 'S';
                this.animation.setCount(9);
                this.animation.setOffsetY(277);
                this.setTranslateY(this.getTranslateY() + 2.1334);
            }
            else
            {
                direction = 'W';
                this.animation.setCount(9);
                this.animation.setOffsetY(416); 
                this.setTranslateY(this.getTranslateY() - 2.1334);
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

    public static boolean isDigit(int i) {
        return false;
    }

    public static int getNumericValue(char c) {
        return 0;
    }
}
