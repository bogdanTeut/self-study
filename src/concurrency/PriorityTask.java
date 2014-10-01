package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 30/09/14.
 */
public class PriorityTask implements Runnable {

    private int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread().toString();
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(new Random().nextInt(10));
        System.out.println(this);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new Runnable(){

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.run();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<9;i++){
            executorService.execute(new PriorityTask(Thread.MIN_PRIORITY));
        }
        executorService.execute(new PriorityTask(Thread.MAX_PRIORITY));

        executorService.shutdown();
    }
}
