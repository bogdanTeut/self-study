package concurrency.synchronizedblock;

/**
 * Created by bogdan on 04/10/14.
 */
public class PairManagerImplTwo extends PairManager {

    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            pair.incrementX();
            pair.incrementY();
            temp = getPair();
        }
        store(temp);
    }
}
