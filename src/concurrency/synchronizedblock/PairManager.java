package concurrency.synchronizedblock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bogdan on 04/10/14.
 */
public abstract class PairManager {
    AtomicInteger atomicInteger = new AtomicInteger();
    protected Pair pair = new Pair(0,0);
    private List<Pair> pairs = Collections.synchronizedList(new ArrayList<Pair>());

    public synchronized Pair getPair(){
        return new Pair(pair.getX(), pair.getY());
    }

    public void store(Pair pair){
        pairs.add(pair);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public abstract void increment();

    @Override
    public String toString() {
        return "Pair " + pair + " counter " + atomicInteger.get();
    }
}
