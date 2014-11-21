package swing;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 21/11/2014.
 */
public class CallableTask extends Task implements Callable<String>{
    private static int counter;
    private final int id = counter++;

    @Override
    public String toString() {
        return "Task "+id;
    }

    @Override
    public String call() throws Exception {
        run();
        System.out.println("Returning "+this);
        return this.toString();
    }
}
