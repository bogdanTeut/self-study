package concurrency.ornamentalgarden;

import java.util.Random;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class Count {
    private int counter;
    
    public synchronized void increment(){
        int temp = counter;
        if(new Random().nextBoolean()) Thread.yield();
        counter = ++temp;
    }
    
    public synchronized int value (){
        return counter;        
    }
}
