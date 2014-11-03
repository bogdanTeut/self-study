package concurrency.priorityblockingqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 23/10/14.
 */
public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask>{

    private int priority;
    private final int id = count++;
    private static int count;
    static List<PrioritizedTask> prioritizedTasks = new ArrayList<PrioritizedTask>();
    private Random random = new Random();

    public PrioritizedTask(int priority) {
        this.priority = priority;
        prioritizedTasks.add(this);
    }

    @Override
    public int compareTo(PrioritizedTask another) {
        return (priority<another.priority?1:(priority>another.priority?-1:0));
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", priority)+" Task id "+id;
    }
}
