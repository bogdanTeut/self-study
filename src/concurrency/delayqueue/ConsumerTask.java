package concurrency.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * Created by bogdan on 23/10/14.
 */
public class ConsumerTask implements Runnable{

    private DelayQueue<DelayQueueTask> queueTasks = new DelayQueue<DelayQueueTask>();

    public ConsumerTask(DelayQueue<DelayQueueTask> queueTasks) {
        this.queueTasks = queueTasks;
    }

    @Override
    public void run() {
//        for (DelayQueueTask queueTask : queueTasks) {
//            queueTask.run();
//        }
        try {
            while(!Thread.interrupted()){
                queueTasks.take().run();
            }
        } catch (InterruptedException e) {
            System.out.println("ConsumerTask interrupted");
        }
    }
}








