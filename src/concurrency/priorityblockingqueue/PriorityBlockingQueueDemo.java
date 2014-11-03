package concurrency.priorityblockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by bogdan on 23/10/14.
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<PrioritizedTask> prioritizedTasks = new PriorityBlockingQueue<PrioritizedTask>();
        executorService.execute(new PrioritizedBlockingQueueProducer(executorService, prioritizedTasks));
        executorService.execute(new PrioritizedBlockingQueueConsumer(prioritizedTasks));
    }

}
