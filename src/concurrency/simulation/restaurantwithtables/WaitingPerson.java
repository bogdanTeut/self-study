package concurrency.simulation.restaurantwithtables;

import concurrency.simulation.restaurant.Food;

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

    public void placeOrder(Food food, Table table, WaitingPerson waitingPerson, Customer customer) throws InterruptedException {
        if (table.getOrderTicket() == null) table.setOrderTicket(new OrderTicket(table, waitingPerson));
        OrderTicket orderTicket = table.getOrderTicket();
        orderTicket.individualOrders.put(customer, food);

        //not all of the customers are ready yet
        if (table.getNumberOfCustomers() != table.customerList.size()) {
            System.out.println("Not all of the customers are ready yet");
            return;
        }

        if (orderTicket.individualOrders.size() != table.getNumberOfCustomers()) {
            System.out.println("Not all of the customers are ready to order yet");
            return;
        }

        System.out.println(this+" placing "+ orderTicket);
        restaurant.orderTickets.put(orderTicket);
        table.setOrderTicket(null);

    }

    @Override
    public String toString() {
        return "WaitingPerson "+id;
    }

}
