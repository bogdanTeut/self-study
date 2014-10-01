package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by bogdan on 29/09/14.
 */
public class FibonacciCallableTask implements Callable<Integer>{

    private int number;

    public FibonacciCallableTask(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        return fibonacci(number);
    }

    private Integer fibonacci(int number) {
        if (number < 2) return 1;
        return fibonacci(number-1) + fibonacci(number-2);
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> results = new ArrayList<Future<Integer>>();
        for (int i=0;i<5;i++){
            results.add(executorService.submit(new FibonacciCallableTask(i)));
        }
        for (Future<Integer> result:results){
            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
    }
}
