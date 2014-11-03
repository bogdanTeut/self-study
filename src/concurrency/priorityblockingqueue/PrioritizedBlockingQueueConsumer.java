package concurrency.priorityblockingqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by bogdan on 24/10/14.
 */
public class PrioritizedBlockingQueueConsumer implements Runnable {

    private PriorityBlockingQueue<PrioritizedTask> prioritizedTasks;

    public PrioritizedBlockingQueueConsumer(PriorityBlockingQueue<PrioritizedTask> prioritizedTasks) {
        this.prioritizedTasks = prioritizedTasks;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                prioritizedTasks.take().run();
            }
        }catch (InterruptedException ie){
            System.out.println("PrioritizedBlockingQueueConsumer interrupted");
        }
    }
}
