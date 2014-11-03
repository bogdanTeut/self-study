package concurrency.priorityblockingqueue;

import concurrency.delayqueue.DelayQueueTask;

import java.util.concurrent.ExecutorService;

/**
 * Created by bogdan on 23/10/14.
 */
public class EndSentinel extends PrioritizedTask {

    private ExecutorService executorService;

    public EndSentinel(ExecutorService executorService, int priority) {
        super(priority);
        this.executorService = executorService;
    }

    @Override
    public void run() {
        System.out.println("End sentinel");
        for (PrioritizedTask prioritizedTask:prioritizedTasks){
            System.out.println(prioritizedTask);
        }
        System.out.println("Shutting down");
        executorService.shutdownNow();
    }
}
