package io.greenhouse;

/**
 * Created by bogdan.teut on 27/08/2014.
 */

//SERVER (LIBRARY)
abstract class Event{
    
    private long delay;
    private long startTime;

    protected Event(long delay) {
        this.delay = delay;
        start();
    }

    void start() {
        startTime = System.nanoTime()+delay;    
    }

    public abstract void action();
    
    public boolean ready(){
        return System.nanoTime()>=startTime;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
