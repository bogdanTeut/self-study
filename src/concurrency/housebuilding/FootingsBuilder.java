package concurrency.housebuilding;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class FootingsBuilder implements Runnable {

    private LinkedBlockingQueue<House> footingsQueue;

    public FootingsBuilder(LinkedBlockingQueue<House> footingsQueue) {
        this.footingsQueue = footingsQueue;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(500);
                House house = new House();
                footingsQueue.put(house);
            }
        }catch (InterruptedException ie){
            System.out.println("FootingsBuilder interrupted");
        }
    }
}
