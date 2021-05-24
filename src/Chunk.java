public class Chunk {

    boolean isBlocked = false;
    boolean isDangered = false;
    boolean isEnd = false;

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
}
