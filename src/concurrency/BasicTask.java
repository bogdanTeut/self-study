package concurrency;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 28/09/14.
 */
public class BasicTask implements Runnable{

    private static int count;
    private final int id = count++;
    private int countDown = 10;

    @Override
    public String toString() {
        return "Task "+id+" countDown "+countDown +"thread "+Thread.currentThread();
    }

    @Override
    public void run() {
        while (countDown-->0){
            System.out.println(this);
//            Thread.yield();
            try {
                long startTime = System.currentTimeMillis();
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                long endTime = System.currentTimeMillis();
                System.out.println(Thread.currentThread()+" slept for "+ (endTime - startTime));
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        BasicTask basicTask = new BasicTask();
        //basicTask.run();
        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            //Thread t = new Thread(new BasicTask());
            //t.start();
            executorService.execute(new BasicTask());
        }
        TimeUnit.SECONDS.sleep(15);
        executorService.shutdownNow();

        System.out.println("Waiting for base task...");
    }
}
