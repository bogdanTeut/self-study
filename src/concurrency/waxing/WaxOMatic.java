package concurrency.waxing;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 09/10/2014.
 */
public class WaxOMatic {

    public static void main(String[] args) throws InterruptedException {
        final Car carOne = new Car("BMW");
        Car carTwo = new Car("Mercedes");
        Car carThree = new Car("Dacia");
        
        final Car[] cars = {carOne, carTwo, carThree};
        
        final ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(carOne));
        executorService.execute(new WaxOff(carTwo));
        executorService.execute(new WaxOff(carThree));
        //TimeUnit.SECONDS.sleep(5);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int counter = 3;            
            @Override
            public void run() {
                if (--counter >= 0) {
                    executorService.execute(new WaxOn(cars[counter]));
                }else{
                    timer.cancel();
                }
            }
        }, 5000, 400);

        TimeUnit.SECONDS.sleep(15);
        executorService.shutdownNow();
    }
    
}
