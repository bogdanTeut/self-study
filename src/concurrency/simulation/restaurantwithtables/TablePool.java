package concurrency.simulation.restaurantwithtables;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by bogdan.teut on 10/11/2014.
 */
public class TablePool {

    private List<Table> tables;
    private boolean[] checkedOut;
    private Semaphore semaphore;

    public TablePool(int numberOfTables) {
        semaphore = new Semaphore(numberOfTables, true);
        tables = new ArrayList<Table>();
        checkedOut = new boolean[numberOfTables];
    }

    public Table checkOut() throws InterruptedException {
        semaphore.acquire();
        Table table =  getTable();
        System.out.println(table+" checked out");
        return table;
    }

    public Table getTable() {
        for (int i = 0; i <checkedOut.length ; i++) {
            if (!checkedOut[i]){
                return tables.get(i);
            }
        }
        return null;
    }

    public synchronized void checkIn(Table table){
        if (releaseTable(table)){
            semaphore.release();
        }
    }

    private synchronized boolean releaseTable(Table table) {
        int index = tables.indexOf(table);
        if (index == -1) return false;
        if (checkedOut[index]){
            checkedOut[index] = false;
            return true;
        }
        return false;
    }
}
