package concurrency.simulation.bankteller;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bogdan.teut on 07/11/2014.
 */
public class CustomerLine extends ArrayBlockingQueue<Customer>{

    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (this.size() == 0) return "Empty";
        StringBuilder sb = new StringBuilder();
        for (Customer customer : this) {
            sb.append(customer);
        }
        return sb.toString();
    }
}
