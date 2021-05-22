import javafx.scene.image.Image;

public class Map {
    private int width;
    private int heigth;
    Chunk[][] map;

    public static Image image[] = new Image[4];

    public Map(int w, int h, int[][] m) {
        loadImages();
        width = w;
        heigth = h;
        transformMap();
    }

    public void loadImages() {
        for(int i=0 ; i<=3 ; i++) {
            String num = Integer.toString(i);
            image[i] = new Image("Images\\"+num+".png");
        }
    }

    public void transformMap() {
        map = new Chunk[heigth][width];

        for(int i=0 ; i<heigth ; i++) {
            for(int j=0 ; j<width ; j++) {
                map[i][j] = new Chunk(false, false);
            }
        }
    }
}
