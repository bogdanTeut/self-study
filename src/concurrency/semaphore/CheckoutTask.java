package concurrency.semaphore;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 04/11/2014.
 */
public class CheckoutTask<T> implements Runnable {

    private Pool<T> pool;
    private static int counter;
    private final int id = counter++;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T t = pool.checkOut();
            System.out.println(this + " checking out "+t);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checking in "+t);
            pool.checkIn(t);
        } catch (InterruptedException e) {
            System.out.println(this + " was interrupted");
        }
    }

    @Override
    public String toString() {
        return "Checkout task "+id;
    }
}
