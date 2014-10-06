package concurrency.synchronizedblock;

/**
 * Created by bogdan on 04/10/14.
 */
public class PairManagerChecker implements Runnable {

    private PairManager pairManager;

    public PairManagerChecker(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while(true){
            pairManager.atomicInteger.incrementAndGet();
            pairManager.getPair().checkPair();
        }
    }
}
