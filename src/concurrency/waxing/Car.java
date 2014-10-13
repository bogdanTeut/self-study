package concurrency.waxing;

/**
 * Created by bogdan.teut on 09/10/2014.
 */
public class Car {
    boolean waxOn;
    String name;

    public Car(String name) {
        this.name = name;
    }

    public synchronized void waxOff() {
        System.out.println("Waxing Off "+this);
        waxOn = false;
        notify();
    }

    public synchronized void waxOn() {
        System.out.println("Waxing On "+this);
        waxOn = true;
        notify();
    }
    
    public synchronized void waitForWaxingOff() throws InterruptedException {
        while (waxOn == true){
            wait();
        }
    }

    public synchronized void waitForWaxingOn() throws InterruptedException {
        while (waxOn == false){
            wait();
        }
    }

    @Override
    public String toString() {
        return "Car: "+name;
    }
}
