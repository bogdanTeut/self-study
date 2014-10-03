package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bogdan.teut on 03/10/2014.
 */
public class AtomicityTest implements Runnable {
    
    private int nextValue;
    
    public synchronized void increment(){
        nextValue++;
        nextValue++;
    }
    
    public synchronized int getNext(){
        return nextValue;
    };
    
    @Override
    public void run() {
        while(true)
            increment();            
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicityTest command = new AtomicityTest();
        executorService.execute(command);
        while (true){
            int next = command.getNext();
            if (next %2!=0){
                System.out.println("concurrency failed:"+next);
                System.exit(0);
            }
        }
    }
}
