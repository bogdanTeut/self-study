package concurrency.simulation.bankteller;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 07/11/2014.
 */
public class TellerManager implements Runnable{

    private int adjustmentPeriod;
    private CustomerLine customerLine;
    private PriorityQueue<Teller> workingTellers =
            new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings =
            new LinkedList<Teller>();
    private ExecutorService executorService;

    public TellerManager(int adjustmentPeriod, CustomerLine customerLine, ExecutorService executorService) {
        this.adjustmentPeriod = adjustmentPeriod;
        this.customerLine = customerLine;
        this.executorService = executorService;

        Teller teller = new Teller(customerLine);
        executorService.execute(teller);
        workingTellers.add(teller);
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjust();
                showCustomers();
            }
        }catch (InterruptedException ie){
            System.out.println("TellerManager interrupted");
        }
    }

    private void adjust() {
        System.out.println("Before adjustment: "+customerLine.size() + " customers");
        System.out.println("Before adjustment: " + workingTellers.size() + " working tellers");
        showCustomers();
        if (customerLine.size()/workingTellers.size() > 2){
            if (tellersDoingOtherThings.size() > 0){
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomersLine();
                workingTellers.offer(teller);
            }
            Teller teller = new Teller(customerLine);
            executorService.execute(teller);
            workingTellers.offer(teller);
        }

        if (workingTellers.size() > 1 && customerLine.size()/workingTellers.size() < 2){
            reassignTeller();
        }

        if (customerLine.size() == 0){
            while (workingTellers.size() > 1){
                reassignTeller();
            }
        }
        System.out.println("After adjustment: "+customerLine.size() + " customers");
        System.out.println("After adjustment: "+workingTellers.size() + " working tellers");
        showCustomers();
    }

    private void showCustomers() {
        System.out.print(customerLine + " { ");
        for(Teller teller : workingTellers)
            System.out.print(teller + " ");
        System.out.println("}");
    }

    private void reassignTeller() {
        Teller teller = workingTellers.remove();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }
}
