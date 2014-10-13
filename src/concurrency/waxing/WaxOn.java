package concurrency.waxing;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 09/10/2014.
 */
public class WaxOn implements Runnable {
    
    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        System.out.println("Starting the Waxing On for "+car);
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(1000);
                car.waxOn();
                car.waitForWaxingOff();
            }            
        } catch (InterruptedException e){
            System.out.println("WaxOn Interrupted");
        }
        System.out.println("Exiting WaxOn");
    }
}
