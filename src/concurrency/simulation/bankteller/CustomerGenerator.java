package concurrency.simulation.bankteller;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 07/11/2014.
 */
public class CustomerGenerator implements Runnable {

    private CustomerLine customerLine;
    private Random random = new Random();

    public CustomerGenerator(CustomerLine customerLine) {
        this.customerLine = customerLine;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customerLine.add(new Customer(random.nextInt(1000)));
            }
        }catch (InterruptedException ie){
            System.out.println("CustomerGenerator interrupted");
        }

    }
}
