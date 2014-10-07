package concurrency.criticalsections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 07/10/2014.
 */
public class CriticalSections {
    
    static ExecutorService executorService = Executors.newCachedThreadPool();
    
    static void test(PairManager pairManager1, PairManager pairManager2) throws InterruptedException {
        PairManipulator pairManipulator1 = new PairManipulator(pairManager1);
        PairManipulator pairManipulator2 = new PairManipulator(pairManager2);
        
        PairChecker pairChecker1 = new PairChecker(pairManager1);
        PairChecker pairChecker2 = new PairChecker(pairManager2);
        
        executorService.execute(pairManipulator1);
        executorService.execute(pairManipulator2);

        executorService.execute(pairChecker1);
        executorService.execute(pairChecker2);

        TimeUnit.MILLISECONDS.sleep(500);
        
        System.out.println(pairManipulator1);
        System.out.println(pairManipulator2);
        System.exit(0);
                
    }

    public static void main(String[] args) throws InterruptedException {
        test(new PairManager1(), new PairManager2());
    } 
}
