package concurrency.carassembler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class ChassisBuilder implements Runnable {

    private LinkedBlockingQueue<Car> chassisQueue;

    public ChassisBuilder(LinkedBlockingQueue<Car> chassisQueue) {
        this.chassisQueue = chassisQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(1000);
                Car car = new Car();
                chassisQueue.put(car);
            }
        }catch (InterruptedException ie){
            System.out.println("ChassisBuilder interrupted");
        }
    }
}
