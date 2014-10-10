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
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxOn();
                car.waitForWaxingOff();
            }            
        } catch (InterruptedException e){
            System.out.println("WaxOn Interrupted");
        }
    }
}
