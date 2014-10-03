package concurrency.sharedresource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bogdan.teut on 02/10/2014.
 */
public class EvenCheckerTester {
    
    public static void test(IntGenerator generator, int instances){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<instances;i++){
            executorService.execute(new EvenChecker(generator));            
        }
        executorService.shutdown();
    }

    public static void test(IntGenerator generator){
        test(generator, 10);
    }
}
