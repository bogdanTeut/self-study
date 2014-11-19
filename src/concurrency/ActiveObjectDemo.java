package concurrency;

import concurrency.delayqueue.DelayQueueDemo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 18/11/2014.
 */
public class ActiveObjectDemo {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Random random = new Random();

    public Future<Integer> calculateInt(final int x, final int y){
        return executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Starting x:"+x+" y:"+y);
                pause(200);
                return x+y;
            };
        });
    }

    public Future<Float> calculateFloat(final float x, final float y){
        return executorService.submit(new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                System.out.println("Starting x:"+x+" y:"+y);
                pause(2000);
                return x+y;
            };
        });
    }

    private void pause(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(500+random.nextInt(i));
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted");
        }
    }

    private void calculateDouble(final double x, final double y){
        executorService.submit(new Callable<Object>() {
            @Override
            public Double call() throws Exception {
                System.out.println("Starting x:"+x+" y:"+y);
                pause(2000);
                return x+y;
            }
        });
    }

    private void shutDown() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo delayQueueDemo = new ActiveObjectDemo();
        List<Future<?>> list = new CopyOnWriteArrayList<Future<?>>();
        for (float i=0.0f;i<1.0f;i+=0.2f){
            list.add(delayQueueDemo.calculateFloat(i, i));
        }

        for (double i=0.0d;i<1.0d;i+=0.2d){
            delayQueueDemo.calculateDouble(i, i);
        }

        for (int i=0;i<5;i++){
            list.add(delayQueueDemo.calculateInt(i, i));
        }

        while (list.size()>0) {

            for (int i = 0; i < list.size(); i++) {
                Future<?> future = list.get(i);
                if (future.isDone()) {
                    try {
                        System.out.println(future.get());
                    } catch (InterruptedException e) {
                        System.out.println("Get interrupted");
                    } catch (ExecutionException e) {
                        System.out.println("Something bad happened");
                    }
                    list.remove(future);
                }
            }
        }

        delayQueueDemo.shutDown();

    }

}
