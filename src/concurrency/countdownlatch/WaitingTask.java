package concurrency.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 22/10/2014.
 */
public class WaitingTask implements Runnable {

    private final int id;
    private CountDownLatch countDownLatch;

    public WaitingTask(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    public WaitingTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try{
//            while(!Thread.interrupted()){
                System.out.println(this+" await for barrier");
                countDownLatch.await();
                System.out.println(this+" barrier passed");
//            }
        }catch (InterruptedException ie){
            System.out.println(this+" is interrupted");
        }

    }

    @Override
    public String toString() {
        return "WaitingTask "+id;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i=0;i<10;i++){
            executorService.execute(new WaitingTask(i, countDownLatch));
        }

        for (int i=0;i<100;i++){
            executorService.execute(new TaskPortion(i, countDownLatch));
        }
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Task are shut down");
        executorService.shutdown();
    }
}
