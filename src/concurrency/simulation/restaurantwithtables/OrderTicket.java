package concurrency.simulation.restaurantwithtables;

import concurrency.simulation.restaurant.Food;

/**
 * Created by bogdan on 09/11/14.
 */
public class OrderTicket {
    private WaitingPerson waitingPerson;
    private Food food;
    private Customer customer;

    public OrderTicket(Food food, Customer customer, WaitingPerson waitingPerson) {
        this.food = food;
        this.customer = customer;
        this.waitingPerson = waitingPerson;
    }

    public WaitingPerson getWaitingPerson() {
        return waitingPerson;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return "Order from "+waitingPerson+" containing "+food+" for "+customer;
    }
}
