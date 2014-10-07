package concurrency.criticalsections;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class PairManager2 extends PairManager{
    
    @Override
    public void increment() {
        synchronized (this) {
            pair.incrementX();
            pair.incrementY();
        }
        store(getPair());
    }
}
