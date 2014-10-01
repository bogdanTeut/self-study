package concurrency;

/**
 * Created by bogdan.teut on 01/10/2014.
 */

class Sleeper extends Thread{

    private int sleepTime;

    Sleeper(int sleepTime, String name) {
        super(name);
        this.sleepTime = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + "was interrupted");
        }
        System.out.println(Thread.currentThread() + "woke up");
    }
}

class Joiner extends Thread{
    private Sleeper sleeper;

    Joiner(Sleeper sleeper) {
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread() + "was interrupted");
        }
        System.out.println(sleeper + "joined" + Thread.currentThread());

    }
}

public class JoiningThreads {
    

    public static void main(String[] args) {
        Sleeper sleepy =  new Sleeper(1500, "Sleepy"),
                grumpy = new Sleeper(1500, "Grumpy");
        
        Joiner joinerOne = new Joiner(sleepy);
        Joiner joinerTwo = new Joiner(grumpy);
        joinerTwo.interrupt();
    }
}
