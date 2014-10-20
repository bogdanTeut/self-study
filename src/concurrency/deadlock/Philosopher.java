package concurrency.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 20/10/2014.
 */
public class Philosopher implements Runnable{
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private int ponderFactor;
    private Random random = new Random();
    private Bin bin;

    public Philosopher(Bin bin, int id, int ponderFactor) {
        this.id = id;
        this.bin = bin;
        this.ponderFactor = ponderFactor;
    }

    private void pause() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(ponderFactor * random.nextInt(250));
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this+" is thinking");
                pause();
//                System.out.println(this+" grabs left");
//                left.take();
//                System.out.println(this+" grabs right");
//                right.take();

                System.out.println(this + " grabing chopsticks");
                bin.take(this);
                System.out.println(this + " eating");
                bin.checkIfTwoPhilosophersAreUsingTheSameChopstick(this);
                pause();
                System.out.println(this + " dropping chopsticks");
                bin.drop(this);
            }
        }catch (InterruptedException ie){
            System.out.println(this+" interrupted");
        }
    }

    @Override
    public String toString() {
        return "Philosopher "+id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        Philosopher otherPhilosopher = (Philosopher)obj;
        return id==otherPhilosopher.id;
    }
}
