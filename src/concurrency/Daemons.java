package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 30/09/14.
 */
class DaemonSpawn implements Runnable{

    @Override
    public void run() {
        while (true){
            Thread.yield();
        }
    }
}

class Daemon implements Runnable{

    private Thread[] threads = new Thread[10];

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            threads[i]  = new Thread(new DaemonSpawn());
            threads[i].start();
            System.out.println(threads[i]+ " started");
        }

        for (int i=0;i<10;i++){
            System.out.println(threads[i].isDaemon());
        }
        while (true){
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println(d.isDaemon());
        TimeUnit.SECONDS.sleep(3);
    }
}
