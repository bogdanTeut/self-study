package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by bogdan.teut on 01/10/2014.
 */

class ExceptionThread extends Thread{
    @Override
    public void run() {
        throw new RuntimeException();
    }
}

public class DealingWithExceptions {
    public static void main(String[] args) {
        try{
            ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactory() {
                
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r);
                    t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                        @Override
                        public void uncaughtException(Thread t, Throwable e) {
                            System.out.println(e);
                        }
                    });
                    return t;
                }
            });
            executorService.execute(new ExceptionThread());
            executorService.shutdown();
        }catch (RuntimeException e){
            System.out.println("Exception caught");            
        }
    }
}
