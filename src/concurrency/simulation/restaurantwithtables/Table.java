package concurrency.simulation.restaurantwithtables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by bogdan.teut on 10/11/2014.
 */
public class Table {

    private static int counter;
    private int id = counter++;
    private int numberOfCustomers;
    List<Customer> customerList = Collections.synchronizedList(new ArrayList<Customer>());
    private OrderTicket orderTicket;

    @Override
    public String toString() {
        return "Table "+id;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public OrderTicket getOrderTicket() {
        return orderTicket;
    }

    public void setOrderTicket(OrderTicket orderTicket) {
        this.orderTicket = orderTicket;
    }

}
