package concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bogdan on 28/09/14.
 */
public class FibonacciTask implements Runnable {

    private int number;
    private Set<Integer> fibonacciNumbers = new TreeSet<Integer>();

    public FibonacciTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        fibonacci(number);
        System.out.println(fibonacciNumbers);
    }

    private int fibonacci(int number) {
        int result = 0;
        if (number < 2) result = 1;
        else result = fibonacci(number-2) + fibonacci(number-1);

        fibonacciNumbers.add(result);
        return result;
    }

    public static void main(String[] args) {
        for(int i=0;i<1;i++){
            Thread t = new Thread(new FibonacciTask(10));
            t.start();
        }
    }
}
