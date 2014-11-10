package concurrency.simulation.restaurantwithtables;

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
                OrderTicket orderTicket = restaurant.orderTickets.take();
                System.out.println(orderTicket +" is starting getting prepared by "+this);
                TimeUnit.MILLISECONDS.sleep(200);
                //here prepare food is represented by a tinny sleep
                orderTicket.getWaitingPerson().filledOrders.put(new Plate(orderTicket.getCustomer(), orderTicket.getFood()));
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
