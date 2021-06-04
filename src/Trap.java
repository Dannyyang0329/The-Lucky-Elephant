import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Trap {
    
    ImageView trapView ;

    boolean isTrapOn = false;

    AnimationTimer thronTimer;
    long previousTime = 0;

    public Trap(int y, int x, int height, int width, boolean isTrap) {
        isTrapOn = isTrap;
        trapView = new ImageView();

        if(isTrap) trapView.setImage(Elephant.thronOutImage);
        else trapView.setImage(Elephant.thronInImage);

        trapView.setLayoutX((1152-width*64)/2 + x*64);
        trapView.setLayoutY((648-height*64)/2 + y*64);
    }

    public void changeStatus() {
        
        Image out = new Image(getClass().getResource("resources\\Images\\thronOut.gif").toExternalForm());
        Image in = new Image(getClass().getResource("resources\\Images\\thronIn.gif").toExternalForm());

        previousTime = 0;

        if(isTrapOn == false) isTrapOn=true;
        else isTrapOn=false;
        
        if(isTrapOn) trapView.setImage(out);
        else trapView.setImage(in);

        thronTimer = new AnimationTimer(){

            @Override
            public void handle(long now) {
                if(previousTime == 0) previousTime = now;                
                if(now - previousTime >= 0.45e9){
                    if(isTrapOn) trapView.setImage(Elephant.thronOutImage);
                    else trapView.setImage(Elephant.thronInImage);

                    thronTimer.stop();
                }
            }
        };

        thronTimer.start();
    }
}