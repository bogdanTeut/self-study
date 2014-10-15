package concurrency.producerconsumer.queues;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 15/10/2014.
 */
public class ToastOMatic {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Toast> toastBlockingQueue =  new LinkedBlockingDeque<Toast>();
        BlockingQueue<Toast> butterBlockingQueue =  new LinkedBlockingDeque<Toast>();
        BlockingQueue<Toast> jamBlockingQueue =  new LinkedBlockingDeque<Toast>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(toastBlockingQueue));
        executorService.execute(new Butterer(toastBlockingQueue, butterBlockingQueue));
        executorService.execute(new Jamer(butterBlockingQueue, jamBlockingQueue));
        executorService.execute(new Eater(jamBlockingQueue));

        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }

}

enum Status{
    DRY, BUTTERED, JAMMED
}

class Toast{
    private final int id;
    private Status status = Status.DRY;

    Toast(int id) {
        this.id = id;
    }

    public void butter(){
        status = Status.BUTTERED;
    }

    public void jam(){
        status = Status.JAMMED;
    }

    @Override
    public String toString() {
        return "Toast id: "+id+" "+status;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}

class Toaster implements Runnable{

    private BlockingQueue<Toast> toastBlockingQueue;
    private int count;
    private Random random = new Random();

    Toaster(BlockingQueue<Toast> toastBlockingQueue) {
        this.toastBlockingQueue = toastBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500 + random.nextInt(500));
                toastBlockingQueue.put(new Toast(count++));
            }
        }catch(InterruptedException ie){
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster finishes");
    }
}

class Butterer implements Runnable{

    private BlockingQueue<Toast> toastBlockingQueue;
    private BlockingQueue<Toast> butterBlockingQueue;

    Butterer(BlockingQueue<Toast> toastBlockingQueue, BlockingQueue<Toast> butterBlockingQueue) {
        this.toastBlockingQueue = toastBlockingQueue;
        this.butterBlockingQueue = butterBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = toastBlockingQueue.take();
                toast.butter();
                butterBlockingQueue.put(toast);
            }
        }catch(InterruptedException ie){
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer finishes");
    }
}

class Jamer implements Runnable{

    private BlockingQueue<Toast> butterBlockingQueue;
    private BlockingQueue<Toast> jamBlockingQueue;

    Jamer(BlockingQueue<Toast> butterBlockingQueue, BlockingQueue<Toast> jamBlockingQueue) {
        this.butterBlockingQueue = butterBlockingQueue;
        this.jamBlockingQueue = jamBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = butterBlockingQueue.take();
                toast.jam();
                jamBlockingQueue.put(toast);
            }
        }catch(InterruptedException ie){
            System.out.println("Jamer interrupted");
        }
        System.out.println("Jamer finishes");
    }
}

//TO DO
class PeanutButter implements Runnable{

//    private BlockingQueue<Toast> butterBlockingQueue;
//    private BlockingQueue<Toast> jamBlockingQueue;
//
//    Jamer(BlockingQueue<Toast> butterBlockingQueue, BlockingQueue<Toast> jamBlockingQueue) {
//        this.butterBlockingQueue = butterBlockingQueue;
//        this.jamBlockingQueue = jamBlockingQueue;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (!Thread.interrupted()) {
//                Toast toast = butterBlockingQueue.take();
//                toast.jam();
//                jamBlockingQueue.put(toast);
//            }
//        }catch(InterruptedException ie){
//            System.out.println("Jamer interrupted");
//        }
//        System.out.println("Jamer finishes");
//    }
}

class Jelly implements Runnable{

//    private BlockingQueue<Toast> butterBlockingQueue;
//    private BlockingQueue<Toast> jamBlockingQueue;
//
//    Jamer(BlockingQueue<Toast> butterBlockingQueue, BlockingQueue<Toast> jamBlockingQueue) {
//        this.butterBlockingQueue = butterBlockingQueue;
//        this.jamBlockingQueue = jamBlockingQueue;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (!Thread.interrupted()) {
//                Toast toast = butterBlockingQueue.take();
//                toast.jam();
//                jamBlockingQueue.put(toast);
//            }
//        }catch(InterruptedException ie){
//            System.out.println("Jamer interrupted");
//        }
//        System.out.println("Jamer finishes");
//    }
}

class Eater implements Runnable{

    private BlockingQueue<Toast> jamBlockingQueue;
    private int count;

    Eater(BlockingQueue<Toast> jamBlockingQueue) {
        this.jamBlockingQueue = jamBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = jamBlockingQueue.take();
                checkToast(toast);
                System.out.println("Eat "+toast);
            }
        }catch(InterruptedException ie){
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater finishes");
    }

    private void checkToast(Toast toast) {
        if (toast.getId() != count++ || toast.getStatus() != Status.JAMMED) throw new IllegalStateException();
    }
}
