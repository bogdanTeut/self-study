package concurrency.housebuilding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class HouseBuilder {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        LinkedBlockingQueue<House> footingsQueue = new LinkedBlockingQueue<House>();
        LinkedBlockingQueue<House> steelConcreteQueue = new LinkedBlockingQueue<House>();
        LinkedBlockingQueue<House> framingQueue = new LinkedBlockingQueue<House>();
        LinkedBlockingQueue<House> reportingQueue = new LinkedBlockingQueue<House>();

        executorService.execute(new FootingsBuilder(footingsQueue));

        BuilderPool builderPool = new BuilderPool();

        executorService.execute(new SteelBuilder(builderPool));
        executorService.execute(new ConcreteFormsBuilder(builderPool));

        executorService.execute(new HouseAssembler(footingsQueue, steelConcreteQueue, builderPool));

        executorService.execute(new ConcreteFoundationBuilder(steelConcreteQueue, framingQueue));

        executorService.execute(new FramingBuilder(framingQueue, reportingQueue));
        executorService.execute(new HouseReporting(reportingQueue));

        TimeUnit.SECONDS.sleep(7);
        executorService.shutdownNow();

    }
}
