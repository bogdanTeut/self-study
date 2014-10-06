package concurrency.synchronizedblock;

/**
 * Created by bogdan on 04/10/14.
 */
public class PairManagerRunner implements Runnable{

    private PairManager pairManager;

    public PairManagerRunner(PairManager pairManager) {
        this.pairManager = pairManager;
    }

    @Override
    public void run() {
        while (true){
            pairManager.increment();
        }
    }
}
