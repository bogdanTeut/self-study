package concurrency.producerconsumer;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/10/2014.
 */
class StringProducer implements Runnable{

    private StringsManager stringsManager;

    StringProducer(StringsManager stringsManager) {
        this.stringsManager = stringsManager;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            stringsManager.waitIfLimitReached();
            stringsManager.addNewString("a");            
        }               
    }
}

class StringConsumer implements Runnable{

    private StringsManager stringsManager;

    StringConsumer(StringsManager stringsManager) {
        this.stringsManager = stringsManager;
    }
    
    @Override
    public void run() {
        while (!Thread.interrupted()){
            stringsManager.waitIfNoString();
            stringsManager.consumeString();
        }                
    }
}

class StringsManager{
    String[] stringsToConsume = new String[10];
    private int counter;
    
    public synchronized void waitIfLimitReached(){
        try{
            while (counter > 9){
                System.out.printf("Producer waiting");
                wait();
            }
        }catch (InterruptedException e){
            System.out.println("StringProducer interrupted!");
        }
    }

    public synchronized void waitIfNoString(){
        try{
            while (counter == 0){
                System.out.println("Consumer waiting");
                wait();
            }
        }catch (InterruptedException e){
            System.out.println("StringProducer interrupted!");
        }
    }

    public synchronized void addNewString(String string) {
        if (counter == 10){
            System.out.println("Limit Reached");
        }
        stringsToConsume[counter++] = string;
        
    }

    public synchronized void consumeString() {
        System.out.println(stringsToConsume[--counter]);
    }
}

public class StringProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        StringsManager stringsManager =  new StringsManager();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new StringProducer(stringsManager));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Arrays.toString(stringsManager.stringsToConsume));
        
        executorService.execute(new StringConsumer(stringsManager));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Arrays.toString(stringsManager.stringsToConsume));
    }
}
