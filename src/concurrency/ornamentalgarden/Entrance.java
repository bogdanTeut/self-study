package concurrency.ornamentalgarden;

import java.util.*;
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

    @Override
    public String toString() {
        return "Entrance id: "+ id + " counter: " + counter + " total count: "+count.value();
    }

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!isCancel()){
            counter++;
            count.increment();
            System.out.println(this);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch(InterruptedException e) {
            }
        }
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
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++) {
            executorService.execute(new Entrance(i));
            TimeUnit.SECONDS.sleep(5);
        } 
        
        Entrance.cancel();
        executorService.shutdown();
        
        if (executorService.awaitTermination(200, TimeUnit.MILLISECONDS)){
            System.out.println(Entrance.sumPartialCounter()); 
            System.out.println(Entrance.totalCounter());
        }
    }
}
