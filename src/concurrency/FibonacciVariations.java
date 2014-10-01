package concurrency;

import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 01/10/2014.
 */
public class FibonacciVariations {
    
    
    class FibonacciOne{
        
        private Inner inner;
        
        class Inner extends Thread{
            
            private int number;

            Inner(int number) {
                this.number = number;
                start();
            }

            @Override
            public void run() {
                System.out.println(fibonacci(number));
            }
        }

        FibonacciOne(int number) {
            if (inner == null){
                inner = new Inner(number);
            }
        }
    }
    
    class FibonacciTwo{
        
        private int number;

        FibonacciTwo(final int number) {
            this.number = number;
            Thread t = new Thread(){
                
                @Override
                public void run() {
                    System.out.println(fibonacci(number));
                };
            };
            t.start();
        }
    }
    
    class FibonacciThree{
        
        private int number;

        FibonacciThree(int number) {
            this.number = number;
            inner =  new Inner();
        }
        
        Inner inner;
        
        class Inner implements Runnable{

            Thread t;
            Inner() {
               if (t == null){
                   t = new Thread(this);
                   t.start();
               } 
            }

            @Override
            public void run() {
                System.out.println(fibonacci(number)); 
            }
        }
    }
    
    class FibonacciFour{
        private int number;

        Thread t;
        FibonacciFour(final int number) {
            this.number = number;
            t = new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println(fibonacci(number));
                }
            });
            t.start();
        }
    }
    
    class FibonacciMethod{
        private int number;

        Thread t;
        FibonacciMethod(final int number) {
            this.number = number;
        }
        
        public void runTask(){
            t = new Thread(){

                @Override
                public void run() {
                    System.out.println(fibonacci(number));
                };
            };
            t.start();                            
        }
    }
    
    class FibonacciMethodCallable{
        private int number;

        FibonacciMethodCallable(int number) {
            this.number = number;
        }

        public int runTask() throws ExecutionException, InterruptedException {
            return new FutureTask<Integer>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int fibonacci = fibonacci(number);
                    System.out.println(fibonacci);
                    return fibonacci;
                }
            }).get();
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            Future<Integer> fibonnaciResult = executorService.submit(callable);
//            return fibonnaciResult.get();
        };
    }
    
    static int fibonacci(int number){
        if (number<2) return 1;
        return fibonacci(number-1)+fibonacci(number-2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new FibonacciVariations().new FibonacciOne(8);
        new FibonacciVariations().new FibonacciTwo(9);
        new FibonacciVariations().new FibonacciThree(10);
        new FibonacciVariations().new FibonacciFour(11);
        new FibonacciVariations().new FibonacciMethod(12).runTask();
        new FibonacciVariations().new FibonacciMethodCallable(13).runTask();
    }
}
