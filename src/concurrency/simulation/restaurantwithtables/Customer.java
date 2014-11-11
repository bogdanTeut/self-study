package concurrency.simulation.restaurantwithtables;

import concurrency.simulation.restaurant.Course;
import concurrency.simulation.restaurant.Food;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by bogdan on 09/11/14.
 */
public class Customer implements Runnable{
    private static int counter;
    private final ExecutorService executorService;
    private int id = counter++;
    private SynchronousQueue<Plate> meals = new SynchronousQueue<Plate>();
    private CountDownLatch countDownLatch;
    private Table table;

    private WaitingPerson waitingPerson;

    public Customer(WaitingPerson waitingPerson, CountDownLatch countDownLatch, ExecutorService executorService, Table table) {
        this.waitingPerson = waitingPerson;
        this.countDownLatch = countDownLatch;
        this.executorService = executorService;
        this.table = table;
    }

    public void deliverPlate(Plate plate) throws InterruptedException {
        meals.put(plate);
    }

    @Override
    public void run() {
        try{
            for (Course course:Course.values()){
                Food food = course.randomValues();
                System.out.println(this+" ordering "+ food);
                waitingPerson.placeOrder(food, table, waitingPerson, this);
                //eating is represented here by take
                Plate plate = meals.take();
                System.out.println(this+" eating "+plate.getFood());
            }

            System.out.println(this + " is done");
            table.customerList.remove(this);
            countDownLatch.countDown();

        }catch (InterruptedException ie){
            System.out.println(this+" is interrupted");
        }
    }

    @Override
    public String toString() {
        return "Customer "+id;
    }

    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer)obj;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
