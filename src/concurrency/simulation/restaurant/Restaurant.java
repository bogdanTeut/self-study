package concurrency.simulation.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by bogdan on 09/11/14.
 */
public class Restaurant implements Runnable {

    private ExecutorService executorService;
    private List<WaitingPerson> waitingPersonList;
    private List<Chef> chefList;
    public LinkedBlockingQueue<Order> orders = new LinkedBlockingQueue<Order>();
    private Random random = new Random();
    private CountDownLatch countDownLatch = new CountDownLatch(5);

    public Restaurant(ExecutorService executorService, int numberOfWaitingPersons, int numberOfChefs) {
        this.executorService = executorService;

        waitingPersonList = new ArrayList<WaitingPerson>();
        for (int i = 0; i <numberOfWaitingPersons ; i++) {
            WaitingPerson waitingPerson = new WaitingPerson(this);
            waitingPersonList.add(waitingPerson);
            executorService.execute(waitingPerson);
        }

        chefList = new ArrayList<Chef>();
        for (int i = 0; i < numberOfChefs; i++) {
            Chef chef = new Chef(this);
            chefList.add(chef);
            executorService.execute(chef);
        }
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Customer customer = new Customer(waitingPersonList.get(random.nextInt(waitingPersonList.size())), countDownLatch, executorService);
                executorService.execute(customer);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch (InterruptedException ie){
            System.out.println("Restaurant closing time");
        }
    }
}
