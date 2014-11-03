package concurrency.delayqueue;

import java.util.concurrent.ExecutorService;

/**
 * Created by bogdan on 23/10/14.
 */
public class EndSentinel extends DelayQueueTask {

    private ExecutorService executorService;

    public EndSentinel(long milliseconds, ExecutorService executorService) {
        super(milliseconds);
        this.executorService = executorService;
    }

    @Override
    public void run() {
        System.out.println("End sentinel");
        for (DelayQueueTask delayQueueTask:queueTasks){
            System.out.println(delayQueueTask);
        }
        System.out.println("Shutting down");
        executorService.shutdownNow();
    }
}
