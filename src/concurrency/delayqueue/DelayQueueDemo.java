package concurrency.delayqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bogdan on 23/10/14.
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        Random random = new Random();
        DelayQueue<DelayQueueTask> queueTasks = new DelayQueue<DelayQueueTask>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i=0;i<20;i++){
            queueTasks.add(new DelayQueueTask(random.nextInt(5000)));
        }
        queueTasks.add(new EndSentinel(5000, executorService));

        executorService.execute(new ConsumerTask(queueTasks));
    }
}
