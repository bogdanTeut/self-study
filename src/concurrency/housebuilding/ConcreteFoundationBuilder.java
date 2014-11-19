package concurrency.housebuilding;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class ConcreteFoundationBuilder implements Runnable {

    private LinkedBlockingQueue<House> steelConcreteQueue;
    private LinkedBlockingQueue<House> framingQueue;

    public ConcreteFoundationBuilder(LinkedBlockingQueue<House> steelConcreteQueue, LinkedBlockingQueue<House> framingQueue) {
        this.steelConcreteQueue = steelConcreteQueue;
        this.framingQueue = framingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                House house = steelConcreteQueue.take();
                house.addFoundation();
                framingQueue.put(house);
            }
        } catch (InterruptedException e) {
            System.out.println("ConcreteFoundationBuilder interrupted");
        }
    }
}
