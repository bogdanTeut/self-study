package concurrency.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 22/10/2014.
 */
public class TaskPortion implements Runnable {

    private final int id;
    private CountDownLatch countDownLatch;

    public TaskPortion(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    private static Random random = new Random();

    @Override
    public void run() {
        try{
            doWork();
            countDownLatch.countDown();
        }catch (InterruptedException ie){
            System.out.println(this+" was interrupted");
        }
        System.out.println(this+" finished");
    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
    }

    @Override
    public String toString() {
        return "TaskPortion "+id;
    }
}
