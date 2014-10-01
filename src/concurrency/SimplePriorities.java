package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by bogdan on 30/09/14.
 */

class NewThreadFactory implements ThreadFactory {

    private int priority;

    NewThreadFactory(int priority) {
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        return t;
    }
}

public class SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization
    //private int priority;
    //public SimplePriorities(int priority) {
    //    this.priority = priority;
    //}
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }
    public void run() {
        //Thread.currentThread().setPriority(priority);
        while(true) {
// An expensive, interruptable operation:
            for(int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double)i;
                if(i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if(--countDown == 0) return;
        }
    }
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new NewThreadFactory(Thread.MIN_PRIORITY));
        for(int i = 0; i < 5; i++)
            exec.execute(
                    new SimplePriorities());
        exec = Executors.newCachedThreadPool(new NewThreadFactory(Thread.MAX_PRIORITY));
        exec.execute(
                new SimplePriorities());
        exec.shutdown();
    }
}
