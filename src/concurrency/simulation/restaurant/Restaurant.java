package concurrency.simulation.restaurant;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 09/11/14.
 */
public class Restaurant implements Runnable {

    private ExecutorService executorService;
    private List<WaitingPerson> waitingPersonList;
    private List<Chef> chefList;
    public LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();

    public Restaurant(ExecutorService executorService, int numberOfWaitingPersons, int numberOfChefs) {
        this.executorService = executorService;

        waitingPersonList = new ArrayList<WaitingPerson>();
        for (int i = 0; i <numberOfWaitingPersons ; i++) {
            WaitingPerson waitingPerson = new WaitingPerson();
            waitingPersonList.add(waitingPerson);
            executorService.execute(waitingPerson);
        }

        chefList = new ArrayList<Chef>();
        for (int i = 0; i < numberOfChefs; i++) {
            Chef chef = new Chef();
            chefList.add(chef);
            executorService.execute(chef);
        }
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Customer customer = new Customer();
                executorService.execute(customer);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException ie){
            System.out.println("Restaurant closing time");
        }
    }
}
