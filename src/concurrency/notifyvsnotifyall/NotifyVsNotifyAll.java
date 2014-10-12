package concurrency.notifyvsnotifyall;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 12/10/14.
 */
public class NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<5;i++){
            executorService.execute(new Task());
        }
        executorService.execute(new Task2());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            boolean prod = true;

            @Override
            public void run() {
                if(prod){
                    System.out.println("Running notify");
                    Task.blocker.notifyOneThread();
                    prod = false;
                }else{
                    System.out.println("Running notify all");
                    Task.blocker.notifyAllThreads();
                    prod = true;
                }
            }
        },400,400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println("Running notify all on task2 ");
        Task2.blocker.notifyAllThreads();
        executorService.shutdownNow();
    }
}
