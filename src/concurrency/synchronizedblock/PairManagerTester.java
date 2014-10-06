package concurrency.synchronizedblock;

import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 04/10/14.
 */
public class PairManagerTester {
    static void test(PairManager pairManagerOne, PairManager pairManagerTwo) throws InterruptedException {
        PairManagerRunner pairManagerRunnerOne =  new PairManagerRunner(pairManagerOne);
        PairManagerChecker pairManagerCheckerOne = new PairManagerChecker(pairManagerOne);

        PairManagerRunner pairManagerRunnerTwo =  new PairManagerRunner(pairManagerTwo);
        PairManagerChecker pairManagerCheckerTwo = new PairManagerChecker(pairManagerTwo);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(pairManagerRunnerOne);
        executorService.execute(pairManagerRunnerTwo);

        executorService.execute(pairManagerCheckerOne);
        executorService.execute(pairManagerCheckerTwo);

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println(pairManagerOne);
        System.out.println(pairManagerTwo);
        System.exit(0);
    }

    public static void main(String[] args) throws InterruptedException {
        test (new PairManagerImplOne(), new PairManagerImplTwo());
    }
}
