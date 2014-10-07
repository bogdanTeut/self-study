package concurrency.criticalsections;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public abstract class PairManager {
    protected Pair pair = new Pair(0, 0);
    AtomicInteger atomicInteger = new AtomicInteger();
    private List<Pair> pairs = Collections.synchronizedList(new ArrayList<Pair>());
    
    public synchronized Pair getPair(){
        return new Pair(pair.getX(), pair.getY());
    }
    
    public void store(Pair pair){
        pairs.add (pair);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public abstract void increment();
}
