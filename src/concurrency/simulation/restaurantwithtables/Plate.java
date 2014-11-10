package concurrency.simulation.restaurantwithtables;

import concurrency.simulation.restaurant.Food;

/**
 * Created by bogdan on 09/11/14.
 */
public class Plate {
    private Customer customer;
    private Food food;

    public Plate(Customer customer, Food food) {
        this.customer = customer;
        this.food = food;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}
