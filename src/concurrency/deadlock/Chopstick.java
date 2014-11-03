package concurrency.deadlock;

import java.util.*;

/**
 * Created by bogdan.teut on 20/10/2014.
 */
public class Chopstick {
    private final int id;
    private boolean taken;

    public Chopstick(int id) {
        this.id = id;
    }

    public void take() throws InterruptedException {
        taken = true;
    }

    public void drop(){
        taken = false;
    }

    @Override
    public String toString() {
        return "Chopstick "+id;
    }

    public boolean isTaken() {
        return taken;
    }
}

class Bin{

    private Chopstick[] chopsticks;
    private Map<Philosopher,Chopstick[]> philosophersToChopsticks = new HashMap<Philosopher, Chopstick[]>();

    Bin(Chopstick[] chopsticks) {
        this.chopsticks = chopsticks;
    }

    public synchronized void take(Philosopher philosopher) throws InterruptedException {
        while (!twoChopsticksAvailable(philosopher)){
            wait();
        }
        System.out.println("There we go "+philosopher + Arrays.toString(philosophersToChopsticks.get(philosopher)));
    }

    private synchronized boolean twoChopsticksAvailable(Philosopher philosopher) throws InterruptedException {
        Chopstick chopstickOne = null;
        Chopstick chopstickTwo = null;
        for (Chopstick chopstick:chopsticks){
            if (!chopstick.isTaken()) {
                if (chopstickOne == null){
                    chopstick.take();
                    chopstickOne = chopstick;
                }else{
                    chopstick.take();
                    chopstickTwo = chopstick;
                }
            }
            if (chopstickOne!= null && chopstickTwo!= null) {
                    philosophersToChopsticks.put(philosopher, new Chopstick[]{chopstickOne, chopstickTwo});
                return true;
            }
        }
        return false;
    }

    public synchronized void drop(Philosopher philosopher) {
        Chopstick[] chopsticks = philosophersToChopsticks.get(philosopher);
        chopsticks[0].drop();
        chopsticks[1].drop();
        philosophersToChopsticks.remove(philosopher);
        notifyAll();
    }

    public synchronized void checkIfTwoPhilosophersAreUsingTheSameChopstick(Philosopher philosopher) {
        Collection<Chopstick[]> values = philosophersToChopsticks.values();
        Iterator<Chopstick[]> iterator = values.iterator();
        while (iterator.hasNext()){
            Chopstick[] chopsticks = iterator.next();
            if ( contains(philosophersToChopsticks.get(philosopher), chopsticks[0])) throw new RuntimeException(Arrays.toString(philosophersToChopsticks.get(philosopher))+" "+chopsticks[0]);
            if ( contains(philosophersToChopsticks.get(philosopher), chopsticks[1])) throw new RuntimeException(Arrays.toString(philosophersToChopsticks.get(philosopher))+" "+chopsticks[1]);
        }
    }

    private synchronized boolean contains(Chopstick[] chopsticks, Chopstick chopstick) {
        int counter = 0;
        for (Chopstick currentChopstick:chopsticks){
            if (currentChopstick.equals(chopstick)){
                return counter++>1;
            }
        }
        return false;
    }
}