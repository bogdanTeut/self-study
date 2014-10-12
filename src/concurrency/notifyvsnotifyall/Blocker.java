package concurrency.notifyvsnotifyall;

/**
 * Created by bogdan on 12/10/14.
 */
public class Blocker {

    public synchronized void waitingCall(){
        try{
            while (!Thread.interrupted()){
                wait();
                System.out.println(Thread.currentThread());
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted exception");
        }
    }

    public synchronized void notifyOneThread(){
        notify();
    }

    public synchronized void notifyAllThreads(){
        notifyAll();
    }

}

class Task implements Runnable{

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable{

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}
