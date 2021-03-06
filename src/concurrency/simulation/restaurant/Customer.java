package concurrency.simulation.restaurant;

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

    private WaitingPerson waitingPerson;

    public Customer(WaitingPerson waitingPerson, CountDownLatch countDownLatch, ExecutorService executorService) {
        this.waitingPerson = waitingPerson;
        this.countDownLatch = countDownLatch;
        this.executorService = executorService;
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
                waitingPerson.placeOrder(new Order(food, this, waitingPerson));
                //eating is represented here by take
                Plate plate = meals.take();
                System.out.println(this+" eating "+plate.getFood());
            }
            countDownLatch.countDown();
            countDownLatch.await();
            executorService.shutdownNow();
        }catch (InterruptedException ie){
            System.out.println(this+" is interrupted");
        }
        System.out.println(this+" is done");
    }

    @Override
    public String toString() {
        return "Customer "+id;
    }
}
