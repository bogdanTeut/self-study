package concurrency.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 09/10/14.
 */
class RunableOne implements Runnable{

    @Override
    public void run() {
        try {
            synchronized (this){
                wait();
            }
            System.out.println("There we go");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class RunableTwo implements Runnable{

    private RunableOne runableOne;

    RunableTwo(RunableOne runableOne) {
        this.runableOne = runableOne;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("After sleeping 5 secs we call notify");
            synchronized (runableOne){
                runableOne.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class TwoRunnables {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        RunableOne runableOne = new RunableOne();
        executorService.execute(runableOne);
        executorService.execute(new RunableTwo(runableOne));
        executorService.shutdown();
    }
}
