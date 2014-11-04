package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by bogdan.teut on 04/11/2014.
 */
public class Pool<T> {

    private Semaphore semaphore;
    private boolean[] checkedOut;
    private List<T> items;

    public Pool(Class<T> type, int size) {
        semaphore = new Semaphore(size,true);
        checkedOut = new boolean[size];
        items = new ArrayList<T>();

        for (int i = 0; i <size ; i++) {
            try {
                items.add(type.newInstance());
            } catch (InstantiationException e) {
                System.out.println("Class not found");
            } catch (IllegalAccessException e) {
                System.out.println("Class inaccessible");
            }
        }
    }

    public T checkOut() throws InterruptedException {
        semaphore.acquire();
        return getItem();
    }

    public synchronized T getItem() {
        for (int i = 0; i < checkedOut.length; i++) {
            if(!checkedOut[i]){
                checkedOut[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    public synchronized void checkIn(T t) {
        if (releaseItem(t)) semaphore.release();
    }

    private boolean releaseItem(T t) {
        int index = items.indexOf(t);
        if (index == -1) return false;
        if(checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
