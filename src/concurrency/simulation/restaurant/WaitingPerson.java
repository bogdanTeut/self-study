package concurrency.simulation.restaurant;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan on 09/11/14.
 */
public class WaitingPerson implements Runnable{
    private static int counter;
    private int id = counter++;
    public LinkedBlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<Plate>();
    private Restaurant restaurant;

    public WaitingPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Plate plate = filledOrders.take();
                System.out.println(plate+" is ready "+this);
                Customer customer = plate.getCustomer();
                customer.deliverPlate(plate);
            }
        }catch (InterruptedException ie){
            System.out.println(this +" is dismissed");
        }
    }

    public void placeOrder(Order order) throws InterruptedException {
        System.out.println(this+" placing "+order);
        restaurant.orders.put(order);
    }

    @Override
    public String toString() {
        return "WaitingPerson "+id;
    }
}
