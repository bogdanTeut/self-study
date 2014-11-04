package concurrency.semaphore;

/**
 * Created by bogdan.teut on 04/11/2014.
 */
public class Fat {
    private static int counter;
    private final int id = counter++;
    private volatile double d;

    public Fat() {
        for(int i = 1; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double)i;
        }
    }
    public void operation() { System.out.println(this); }
    public String toString() { return "Fat id: " + id; }

}
