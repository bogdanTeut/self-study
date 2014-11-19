package concurrency.housebuilding;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class FramingBuilder implements Runnable {

    private LinkedBlockingQueue<House> framingQueue;
    private LinkedBlockingQueue<House> reportingQueue;

    public FramingBuilder(LinkedBlockingQueue<House> framingQueue, LinkedBlockingQueue<House> reportingQueue) {
        this.framingQueue =  framingQueue;
        this.reportingQueue = reportingQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                House house = framingQueue.take();
                house.addFraming();
                reportingQueue.put(house);
            }
        }catch (InterruptedException ie){
            System.out.println("FramingBuilder interrupted");
        }
    }
}
