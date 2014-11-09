package concurrency.simulation.restaurant;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan on 09/11/14.
 */
public class WaitingPerson implements Runnable{
    private static int counter;
    private int id = counter++;
    public LinkedBlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Plate plate = filledOrders.take();
                Customer customer = plate.getCustomer();
                customer.deliverPlate(plate);
            }
        }catch (InterruptedException ie){
            System.out.println(this +" is dismissed");
        }
    }
}
