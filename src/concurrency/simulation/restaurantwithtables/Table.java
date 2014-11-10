package concurrency.simulation.restaurantwithtables;

/**
 * Created by bogdan.teut on 10/11/2014.
 */
public class Table {

    private static int counter;
    private int id = counter++;
    private boolean available = true;

    @Override
    public String toString() {
        return "Table "+id;
    }
}
