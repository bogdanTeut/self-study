package swing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 21/11/2014.
 */
public class TaskManager extends ArrayList<TaskItem>{
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void add(Callable callableTask) {
        add(new TaskItem(executorService.submit((Callable<String>)callableTask), callableTask));
    }

    public List<String> getResults(){
        List<String> results = new ArrayList<String>();
        for (Iterator<TaskItem> iterator = iterator(); iterator.hasNext(); ) {
            TaskItem next =  iterator.next();
            if (next.future.isDone()){
                try {
                    results.add(next.future.get());
                } catch (InterruptedException e) {
                    System.out.println("Interrupted get");
                } catch (ExecutionException e) {
                    System.out.println("An exception occurred");
                }
            }
        }
        return results;
    }

    public List<String> purge(){
        List<String> results = new ArrayList<String>();
        Iterator<TaskItem> items = iterator();
        while (items.hasNext()){
            TaskItem taskItem =  items.next();
            if (!taskItem.future.isDone()){
                results.add("Cancelling "+taskItem.callableTask);
                taskItem.future.cancel(true);
                items.remove();
            }
        }
        return results;
    }
}
