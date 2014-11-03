package concurrency.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 20/10/2014.
 */
public class PhilosopherDiningDeadlock {
    public static void main(String[] args) throws InterruptedException {
        int ponderFactor = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        ExecutorService executorService = Executors.newCachedThreadPool();

        Chopstick[] chopsticks = new Chopstick[size];
        for (int i=0;i<size;i++){
            chopsticks[i] = new Chopstick(i);
        }

        Bin bin = new Bin(chopsticks);
        for (int i=0;i<size-1;i++){
            executorService.execute(new Philosopher(bin, i, ponderFactor));
        }

        TimeUnit.SECONDS.sleep(15);
        executorService.shutdownNow();
    }
}
