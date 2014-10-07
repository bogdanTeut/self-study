package concurrency.criticalsections;

/**
 * Created by bogdan.teut on 07/10/2014.
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
        return "x= "+ x + ", y= " + y;
    }

    public void checkState(){
        if (x != y) throw new PairUnsynchronisedException();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    class PairUnsynchronisedException extends RuntimeException{
        public PairUnsynchronisedException(){
            super("x and y are not synchronized: " + Pair.this);
        }
    }
}


