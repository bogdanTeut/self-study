package concurrency.synchronizedblock;

/**
 * Created by bogdan on 04/10/14.
 */
public class PairManagerImplOne extends PairManager {

    @Override
    public synchronized void increment() {
        pair.incrementX();
        pair.incrementY();
        store(getPair());
    }
}
