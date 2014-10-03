package concurrency.sharedresource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bogdan.teut on 02/10/2014.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    ReentrantLock lock = new ReentrantLock();
    
    @Override
    int next() {
        boolean locked = false;
        try {
            locked = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try{
            ++currentEvenValue; // Danger point here!
            ++currentEvenValue;
            return currentEvenValue;
        }finally {
            if (locked) lock.unlock();
        }
//        ++currentEvenValue; // Danger point here!
//        ++currentEvenValue;
//        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenCheckerTester.test(new EvenGenerator());
    }
}
