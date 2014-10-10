package concurrency.waxing;

/**
 * Created by bogdan.teut on 09/10/2014.
 */
public class Car {
    boolean waxOn;

    public synchronized void waxOff() {
        System.out.println("Waxing Off");
        waxOn = false;
        notifyAll();
    }

    public synchronized void waxOn() {
        System.out.println("Waxing On");
        waxOn = true;
        notifyAll();
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
}
