package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 04/11/2014.
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final int size = 25;
        final Pool<Fat> pool = new Pool<Fat>(Fat.class, size);
        for (int i = 0; i < size; i++) {
            executorService.execute(new CheckoutTask<Fat>(pool));
        }
        System.out.println("All fat tasks are checked out");

        List<Fat> fatList = new ArrayList<Fat>();
        for (int i = 0; i <size ; i++) {
            Fat fat = pool.checkOut();
            fat.operation();
            fatList.add(fat);
        }

        System.out.println("No more fat tasks are available");

        final Future<?> blocked = executorService.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("Blocked interrupted");
                }
            }
        });

        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);

        System.out.println("Checking in the tasks");

        for (Fat fat : fatList) {
            pool.checkIn(fat);
        }

        System.out.println("Checking in the tasks");

        for (Fat fat : fatList) {
            pool.checkIn(fat);
        }

        executorService.shutdownNow();
    }
}
