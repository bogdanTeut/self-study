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
    public LinkedBlockingQueue<OrderTicket> orderTickets = new LinkedBlockingQueue<OrderTicket>();
    private Random random = new Random();
    private TablePool tablePool;

    public Restaurant(ExecutorService executorService, int numberOfWaitingPersons, int numberOfChefs, int numberOfTables) {
        this.executorService = executorService;
        tablePool = new TablePool(numberOfTables);

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
        int counter =0;
        try{
            while (counter++<100){
                final Table table = tablePool.checkOut();
                int numberOfCustomers =  random.nextInt(3)+2;
                System.out.println("We'd like a table of "+numberOfCustomers+" customers");
                table.setNumberOfCustomers(numberOfCustomers);

                final CountDownLatch countDownLatch = new CountDownLatch(numberOfCustomers);
                WaitingPerson waitingPerson = waitingPersonList.get(random.nextInt(waitingPersonList.size()));
                System.out.println(waitingPerson+" will take care of "+table);
                for (int i=0;i<numberOfCustomers;i++){
                    Customer customer = new Customer(waitingPerson, countDownLatch, executorService, table);
                    table.customerList.add(customer);
                    System.out.println("Adding "+customer+" to "+table);
                    executorService.execute(customer);
                    TimeUnit.MILLISECONDS.sleep(100);
                }

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countDownLatch.await();
                            System.out.println("Checking in " + table);
                            tablePool.checkIn(table);
                        } catch (InterruptedException e) {
                            System.out.println("Table checker interrupted");
                        }
                    }
                });
            }
        }catch (InterruptedException ie){
            System.out.println("Restaurant closing time");
        }
    }
}
