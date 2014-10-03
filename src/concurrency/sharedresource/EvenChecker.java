package concurrency.sharedresource;

/**
 * Created by bogdan.teut on 02/10/2014.
 */
public class EvenChecker implements Runnable {
    
    private IntGenerator generator;

    public EvenChecker(IntGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while (!generator.isCancelled()){
            int next = generator.next();
            if (next %2 != 0){
                generator.cancel();
                System.out.println("Not even value: "+next);
            };            
        }
    }
}
