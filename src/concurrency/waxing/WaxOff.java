package concurrency.waxing;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 09/10/2014.
 */
public class WaxOff implements Runnable {
    
    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        System.out.println("Starting the Waxing Off for "+car);
        try {
            while (!Thread.interrupted()){
                car.waitForWaxingOn();
                TimeUnit.MILLISECONDS.sleep(1000);
                car.waxOff();
            }
        } catch (InterruptedException e) {
            System.out.println("WaxOff Interrupted");
        }
        System.out.println("Exiting WaxOff");
    }
}
