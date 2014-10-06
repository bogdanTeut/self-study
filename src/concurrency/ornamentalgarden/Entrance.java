package concurrency.ornamentalgarden;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 05/10/14.
 */
public class Entrance implements Runnable{
    private int number;
    private static Counter counter = new Counter();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int id;
    private static volatile boolean cancel;

    @Override
    public void run() {
        while(!cancel){
            synchronized (this) {
                ++number;
                counter.increment();
                System.out.println(this+" total count: "+counter.value());
            }
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    public static void cancel (){
        cancel = true;
    }

    @Override
    public String toString() {
        return "Entrance id: "+id +" number: "+number;
    }

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    public static int getTotalCount(){
        return counter.value();
    }

    public static int sumEntrances(){
        int sum = 0;
        for (Entrance entrance:entrances){
            sum += entrance.number;
        };
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++){
            executorService.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        executorService.shutdown();
        try {
            executorService.awaitTermination(250, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (executorService.isShutdown()){
            System.out.println("Total: "+Entrance.getTotalCount());
            System.out.println("Sum of entrances: "+Entrance.sumEntrances());
        }
    }

}
