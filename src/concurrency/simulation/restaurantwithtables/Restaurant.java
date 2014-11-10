package concurrency.simulation.restaurantwithtables;

import concurrency.ornamentalgarden.Count;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 09/11/14.
 */
public class Restaurant implements Runnable {

    private ExecutorService executorService;
    private List<WaitingPerson> waitingPersonList;
    private List<Chef> chefList;
    private List<Table> tableList;
    public LinkedBlockingQueue<OrderTicket> orderTickets = new LinkedBlockingQueue<OrderTicket>();
    private Random random = new Random();
    private TablePool tablePool;

    public Restaurant(ExecutorService executorService, int numberOfWaitingPersons, int numberOfChefs, int numberOfTables) {
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

        tableList = new ArrayList<Table>();
        for (int i = 0; i <numberOfTables ; i++) {
            tableList.add(new Table());
        }

        tablePool = new TablePool(5);
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){

                Table table = tablePool.checkOut();
                int numberOfCustomers =  random.nextInt(5);
                CountDownLatch countDownLatch = new CountDownLatch(numberOfCustomers);
                for (int i=0;i<numberOfCustomers;i++){
                    Customer customer = new Customer(waitingPersonList.get(random.nextInt(waitingPersonList.size())), countDownLatch, executorService, table, tablePool);
                    executorService.execute(customer);
                    TimeUnit.MILLISECONDS.sleep(100);
                }

            }
        }catch (InterruptedException ie){
            System.out.println("Restaurant closing time");
        }
    }
}
