package concurrency.ornamentalgarden;

import java.util.Random;

/**
 * Created by bogdan on 05/10/14.
 */
public class Counter {
    private int counter;

    public synchronized void increment(){
        if (new Random().nextBoolean()) Thread.yield();
        counter++;
    }

    public synchronized int value(){
        return counter;
    }
}
