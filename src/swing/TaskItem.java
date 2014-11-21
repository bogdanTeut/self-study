package swing;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by bogdan.teut on 21/11/2014.
 */
public class TaskItem {
    public final Future<String> future;
    public final Callable callableTask;

    public TaskItem(Future<String> future, Callable callableTask) {
        this.future = future;
        this.callableTask = callableTask;
    }
}
