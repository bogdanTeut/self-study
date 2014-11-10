package concurrency.exchanger;

import concurrency.semaphore.Fat;
import enums.vendingmachine.Generator;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 05/11/2014.
 */
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Generator generator = new Generator() {

            @Override
            public Object next() {
                return new Fat();
            };
        };

        List<Fat> producesList = new CopyOnWriteArrayList<Fat>();
        List<Fat> consumersList = new CopyOnWriteArrayList<Fat>();
        Exchanger<List<Fat>> exchanger = new Exchanger<List<Fat>>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExchangerProducer<Fat>(producesList, generator, exchanger));
        executorService.execute(new ExchangerConsumer<Fat>(consumersList, exchanger));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
