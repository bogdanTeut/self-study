package concurrency.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 23/10/14.
 */
public class DelayQueueTask implements Delayed,Runnable {

    private static int count;
    private final int id = count++;
    private long delta;
    private long trigger;
    static DelayQueue<DelayQueueTask> queueTasks = new DelayQueue<DelayQueueTask>();

    public DelayQueueTask(long milliseconds) {
        delta = milliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(milliseconds, TimeUnit.MILLISECONDS);
        queueTasks.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long sourceDuration = trigger - System.nanoTime();
//        System.out.println(unit.convert(sourceDuration, TimeUnit.NANOSECONDS));
//        System.out.println(sourceDuration);
        return unit.convert(sourceDuration, TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayQueueTask other = (DelayQueueTask)o;
        if(trigger<other.trigger) return -1;
        if(trigger>other.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta)+" Task "+id;
    }
}
