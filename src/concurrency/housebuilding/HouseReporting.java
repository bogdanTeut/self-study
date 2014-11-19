package concurrency.housebuilding;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class HouseReporting implements Runnable {

    private LinkedBlockingQueue<House> reportingQueue;

    public HouseReporting(LinkedBlockingQueue<House> reportingQueue) {
        this.reportingQueue = reportingQueue;
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                House house = reportingQueue.take();
                System.out.println(house);
            }
        } catch (InterruptedException e) {
            System.out.println("HouseReporting interrupted");
        }
    }
}
