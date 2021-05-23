public class Chunk {

    boolean isBlocked = false;
    boolean isDangered = false;

    public void setBlocked(boolean b) {
        if(b == true) isBlocked = true;
        else isBlocked = false;
    }

    public void setDangered(boolean b) {
        if(b == true) isDangered = true;
        else isDangered = false;
    }
}
