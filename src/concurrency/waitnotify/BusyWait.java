package concurrency.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BusyWait {

    volatile static boolean flag;

    /**
     * Created by bogdan on 09/10/14.
     */
    class Sleeper implements Runnable{
        private Waiter waiter;

        Sleeper(Waiter waiter) {
            this.waiter = waiter;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
                flag = true;
                synchronized (waiter){
                    waiter.notify();
                    System.out.println("Notify " + flag);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Waiter implements Runnable{

        @Override
        public void run() {
            try {
                while (!flag){
                    System.out.println(flag);
                    synchronized (this){
                        System.out.println("Going to wait");
                        wait();
                    }
                }

                flag = false;
                System.out.println("Changing the flag");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Waiter waiter = new BusyWait().new Waiter();
        executorService.execute(waiter);
        executorService.execute(new BusyWait().new Sleeper(waiter));
        executorService.shutdown();
    }
}
