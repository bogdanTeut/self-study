package concurrency.simulation.restaurantwithtables;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bogdan on 09/11/14.
 */
public class RestaurantWithQueues {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Restaurant(executorService, 5, 2, 5));
        //System.in.read();
        //System.out.println("Restaurant with queues done");
    }
}
