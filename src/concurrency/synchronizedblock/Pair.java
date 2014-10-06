package concurrency.synchronizedblock;

/**
 * Created by bogdan on 04/10/14.
 */
public class Pair {
    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void incrementX(){
        x++;
    }

    public void incrementY(){
        y++;
    }

    @Override
    public String toString() {
        return "x: "+x+",y: "+y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    class UnsynchronizedPairException extends RuntimeException{
        UnsynchronizedPairException(Pair pair){
            super("This pair is unsynchronized "+pair);
        };
    }

    public void checkPair(){
        if (x!=y) throw new UnsynchronizedPairException(this);
    }
}
