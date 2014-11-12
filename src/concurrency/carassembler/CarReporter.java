package concurrency.carassembler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class CarReporter implements Runnable {

    private LinkedBlockingQueue<Car> reporterQueue;

    public CarReporter(LinkedBlockingQueue<Car> reporterQueue) {
        this.reporterQueue = reporterQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Car car = reporterQueue.take();
                System.out.println(car);
            }
        }catch (InterruptedException ie){
            System.out.println("ChassisBuilder interrupted");
        }
    }
}
