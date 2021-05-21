import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map {
    int width;
    int heigth;
    int[][] mapSheet;
    Chunk[][] map;

    public static ImageView imageView[] = new ImageView[26];

    public Map(int w, int h, int[][] m) {
        loadImages();
        width = w;
        heigth = h;
        mapSheet = m;
    }

    public void loadImages() {
        for(int i=1 ; i<=25 ; i++) {
            String num = Integer.toString(i);
            imageView[i] = new ImageView(new Image("Images\\"+num+".png"));
        }
    }

    public void transformMap() {
        map = new Chunk[heigth][width];

        for(int i=0 ; i<heigth ; i++) {
            for(int j=0 ; j<width ; j++) {
                map[i][j] = new Chunk(imageView[mapSheet[i][j]], false, false);
            }
        }
    }


    
}
