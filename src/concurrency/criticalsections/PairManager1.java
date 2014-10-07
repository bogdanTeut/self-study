package concurrency.criticalsections;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class PairManager1 extends PairManager{
    
    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(getPair());
    }
}
