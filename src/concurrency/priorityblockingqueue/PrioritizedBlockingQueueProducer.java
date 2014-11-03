package concurrency.priorityblockingqueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 24/10/14.
 */
public class PrioritizedBlockingQueueProducer implements Runnable {

    private ExecutorService executorService;
    private PriorityBlockingQueue prioritizedTasks;
    private Random random = new Random();

    public PrioritizedBlockingQueueProducer(ExecutorService executorService, PriorityBlockingQueue<PrioritizedTask> prioritizedTasks) {
        this.executorService = executorService;
        this.prioritizedTasks = prioritizedTasks;
    }

    @Override
    public void run() {
        try{
            for (int i=0;i<20;i++){
                prioritizedTasks.add(new PrioritizedTask(random.nextInt(10)));
                Thread.yield();
            }
            for (int i=0;i<10;i++){
                TimeUnit.MILLISECONDS.sleep(250);
                prioritizedTasks.add(new PrioritizedTask(10));
            }
            for (int i=0;i<10;i++){
                prioritizedTasks.add(new PrioritizedTask(i));
            }
            prioritizedTasks.add(new EndSentinel(executorService, -1));
        }catch (InterruptedException ie){
            System.out.println("PrioritizedBlockingQueueProducer interrupted");
        }
    }
}
