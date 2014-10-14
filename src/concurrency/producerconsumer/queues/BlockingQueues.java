package concurrency.producerconsumer.queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 14/10/2014.
 */
class LiftOff implements Runnable{

    private int countDown;

    LiftOff(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while(--countDown > 0){
            System.out.println(Thread.currentThread()+ ":" + countDown);
            Thread.yield();
        }
    }
}


class LiftOffRunner implements Runnable{

    private BlockingQueue<LiftOff> blockingQueue;

    LiftOffRunner(BlockingQueue<LiftOff> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void add(LiftOff liftOff){
        try {
            blockingQueue.put(liftOff);
        } catch(InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                LiftOff liftOff = blockingQueue.take();
                liftOff.run();
            }
        }catch (InterruptedException e){
            System.out.println("LiftOffRunner interrupted");
        }
    }
}


public class BlockingQueues {
    static void test(BlockingQueue<LiftOff>... blockingQueues) throws IOException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(BlockingQueue<LiftOff> blockingQueue:blockingQueues) {
            executorService.execute(new PopulateQueue(blockingQueue.getClass().getSimpleName(), blockingQueue));
        }
        executorService.shutdown();
    }

    public static void main(String[] args) throws IOException {
        test(new ArrayBlockingQueue<LiftOff>(3), new LinkedBlockingQueue<LiftOff>(), new SynchronousQueue<LiftOff>());
    }
}

class PopulateQueue implements Runnable{

    private LiftOffRunner liftOffRunner;
    private String message;
    Thread liftOffRunnerThread;

    PopulateQueue(String message, BlockingQueue<LiftOff> blockingQueue) {
        liftOffRunner = new LiftOffRunner(blockingQueue);
        this.message = message;
        liftOffRunnerThread = new Thread(liftOffRunner);
        liftOffRunnerThread.start();
    }

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            try{
                liftOffRunner.add(new LiftOff(5));
            }catch (IllegalStateException ise){
                throw ise;
            }
        }
        try {
            readKey(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        liftOffRunnerThread.interrupt();
    }

    private static void readKey(String message) throws IOException {
        System.out.println("Press Enter " + "(" +message+")");
        new BufferedReader(new InputStreamReader(System.in)).readLine();
    }
}
