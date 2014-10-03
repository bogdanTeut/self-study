package concurrency.serialnumbergenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bogdan.teut on 03/10/2014.
 */
public class SerialNumberGeneratorChecker implements Runnable {
    
    private static SerialNumberGenerator serialNumberGenerator;
    private CircularSet circularSet = new CircularSet(1000);

    public SerialNumberGeneratorChecker(SerialNumberGenerator serialNumberGenerator) {
        this.serialNumberGenerator = serialNumberGenerator;
    }

    @Override
    public void run() {
        while (true){
            int next = serialNumberGenerator.generateNext();
            if (circularSet.contains(next)){
                System.out.println("Duplicate circular set: "+next);
                System.exit(0);
            }
            circularSet.add(next);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SerialNumberGenerator generator =  new SerialNumberGenerator();
        for (int i=0;i<10;i++){
            executorService.execute(new SerialNumberGeneratorChecker(generator));
        }
        executorService.shutdown();
    }
}
