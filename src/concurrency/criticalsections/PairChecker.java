package concurrency.criticalsections;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class PairChecker implements Runnable{
    
    private PairManager pairManager;

    public PairChecker(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true){
            pairManager.atomicInteger.incrementAndGet();
            pairManager.getPair().checkState();
        }        
    }
}
