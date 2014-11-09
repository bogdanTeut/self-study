package concurrency.simulation.restaurant;

import java.util.concurrent.SynchronousQueue;

/**
 * Created by bogdan on 09/11/14.
 */
public class Customer implements Runnable{
    private SynchronousQueue<Plate> meals = new SynchronousQueue<Plate>();

    private WaitingPerson waitingPerson;

    public Customer(WaitingPerson waitingPerson) {
        this.waitingPerson = waitingPerson;
    }

    public void deliverPlate(Plate plate) throws InterruptedException {
        meals.put(plate);
    }

    @Override
    public void run() {
        try{
            for (Course course:Course.values()){
                Food food = course.randomValues();
                waitingPerson.placeOrder(new Order(food));
                //eating is represented here by take
                meals.take();
            }
        }catch (InterruptedException ie){
            System.out.println("Customer is done");
        }
        System.out.println("Customer is done");
    }
}
