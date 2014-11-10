package concurrency.simulation.bankteller;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 07/11/2014.
 */
public class Teller implements Runnable, Comparable<Teller> {

    private static int counter;
    private int id = counter++;
    private CustomerLine customerLine;
    private int customersCounter;
    private boolean servingCustomersLine = true;

    public Teller(CustomerLine customerLine) {
        this.customerLine = customerLine;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Customer customer = customerLine.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());

                synchronized (this){
                    customersCounter++;
                    while (!servingCustomersLine){
                        wait();
                    }
                }
            }
        }catch (InterruptedException ie){
            System.out.println("Teller is interrupted");
        }
    }

    public synchronized void doSomethingElse(){
        customersCounter = 0;
        servingCustomersLine = false;
    }

    public synchronized void serveCustomersLine(){
        servingCustomersLine = true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller "+ id;
    }

    @Override
    public int compareTo(Teller other) {
        if (customersCounter < other.customersCounter) return -1;
        if (customersCounter > other.customersCounter) return 1;
        return 0;
    }
}
