package concurrency.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/10/2014.
 */
class Meal{
    private int mealId;
    public boolean isDelivered;

    Meal(int mealId) {
        this.mealId = mealId;
    }

    @Override
    public String toString() {
        return "Meal "+mealId;
    }
}

class BusBoy implements Runnable{

    private Restaurant restaurant;

    BusBoy(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while ( (restaurant.meal == null) || !restaurant.meal.isDelivered){
                        wait();
                    }
                }

                System.out.println("Bus boy cleaning "+restaurant.meal);

                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }

                TimeUnit.SECONDS.sleep(1);

            }
        }catch (InterruptedException e){
            System.out.println("BusBoy interrupted");
        }
    }
}

class WaitPerson implements Runnable{
    
    private Restaurant restaurant;

    WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal == null || restaurant.meal.isDelivered){
                        wait();
                    }
                }

                System.out.println("Waiting person got "+restaurant.meal);

                synchronized (restaurant.boy){
                    restaurant.meal.isDelivered = true;
                    restaurant.boy.notifyAll();
                }
                
            }
        }catch (InterruptedException e){
            System.out.println("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable{

    private Restaurant restaurant;

    Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            int counter = 0;
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal != null){
                        wait();
                    }
                }
                if(++counter == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.executorService.shutdownNow();
                }
                System.out.println("Order up ");

                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Meal(counter);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e){
            System.out.println("Chef interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;
    Chef chef = new Chef(this);
    BusBoy boy =  new BusBoy(this);
    WaitPerson waitPerson = new WaitPerson(this);
    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant() throws InterruptedException {
        executorService.execute(boy);
        executorService.execute(waitPerson);
        executorService.execute(chef);
        TimeUnit.SECONDS.sleep(15);
        executorService.shutdownNow();
    }

    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant = new Restaurant();
        
    }

    
    
}
