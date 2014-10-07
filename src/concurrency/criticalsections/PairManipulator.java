package concurrency.criticalsections;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class PairManipulator implements Runnable {
    
    private PairManager pairManager;

    public PairManipulator(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true){
            pairManager.increment();
        }        
    }

    public String toString() {
        return "Pair: " + pairManager.getPair() +
                " checkCounter = " + pairManager.atomicInteger.get();
    }
}
