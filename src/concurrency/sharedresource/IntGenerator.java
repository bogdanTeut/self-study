package concurrency.sharedresource;

/**
 * Created by bogdan.teut on 02/10/2014.
 */
public abstract class IntGenerator {
    
    volatile boolean cancelled;
    abstract int next();
    
    public boolean isCancelled(){
        return cancelled;
    }
    
    public void cancel(){
        cancelled = true;
    }
}
