package concurrency.ornamentalgarden;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class Entrance implements Runnable {
    
    private static Count count = new Count();
    private int counter;
    private static boolean cancel;
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int id;
    private CountDownLatch countDownLatch;

    @Override
    public String toString() {
        return "Entrance id: "+ id + " counter: " + counter + " total count: "+count.value();
    }

    public Entrance(int id, CountDownLatch countDownLatch) {
        this.id = id;
        entrances.add(this);
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        while (!isCancel()){
            synchronized (this) {
                counter++;
                count.increment();
                System.out.println(this);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch(InterruptedException e) {
                System.out.println("Task interrupted");
                return;
            }
        }
        countDownLatch.countDown();
        System.out.println("Stopping "+this);
    }
    
    public static synchronized int sumPartialCounter(){
        int sum = 0;
        for(Entrance entrance:entrances){
            sum += entrance.counter;            
        }
        return sum;
    }

    public static synchronized int totalCounter(){
        return count.value();
    }
    
    public static void cancel(){
        cancel = true;
    }

    public static boolean isCancel() {
        return cancel;
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Entrance.sumPartialCounter());
                System.out.println(Entrance.totalCounter());
            }
        }){
        }.start();

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++) {
            executorService.execute(new Entrance(i, countDownLatch));
        }

        TimeUnit.SECONDS.sleep(5);

        Entrance.cancel();
        executorService.shutdown();
        System.out.println(countDownLatch.getCount());

    }
}
