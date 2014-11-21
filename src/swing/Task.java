package swing;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 21/11/2014.
 */
public class Task implements Runnable{
    private static int counter;
    private final int id = counter++;

    @Override
    public void run() {
        System.out.println("Starting task "+this);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println("Interrupting "+this);
        }
    }

    @Override
    public String toString() {
        return "Task "+id;
    }
}
