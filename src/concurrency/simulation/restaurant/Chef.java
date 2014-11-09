package concurrency.simulation.restaurant;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 09/11/14.
 */
public class Chef implements Runnable{

    private static int count;
    private int id = count++;
    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                Order order = restaurant.orders.take();
                TimeUnit.MILLISECONDS.sleep(200);
                //here prepare food is represented by a tinny sleep
                order.getWaitingPerson().filledOrders.put(new Plate());
            }
        }catch (InterruptedException ie){
            System.out.println(this+" is dismissed");
        }
    }

    @Override
    public String toString() {
        return "Chef "+id;
    }
}
