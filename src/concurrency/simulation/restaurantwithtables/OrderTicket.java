package concurrency.simulation.restaurantwithtables;

import concurrency.simulation.restaurant.Food;

import java.util.*;

/**
 * Created by bogdan on 09/11/14.
 */
public class OrderTicket {
    private WaitingPerson waitingPerson;
    private Table table;
    Map<Customer, Food> individualOrders = Collections.synchronizedMap(new HashMap<Customer, Food>());

    public OrderTicket(Table table, WaitingPerson waitingPerson) {
        this.table = table;
        this.waitingPerson = waitingPerson;
    }

    public WaitingPerson getWaitingPerson() {
        return waitingPerson;
    }

    @Override
    public String toString() {
        return "Order from "+waitingPerson+" containing "+individualOrders+" for "+table;
    }

    public Table getTable() {
        return table;
    }
}
