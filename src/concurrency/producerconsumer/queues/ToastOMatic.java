package concurrency.producerconsumer.queues;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 15/10/2014.
 */
public class ToastOMatic {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Toast> toastBlockingQueue1 =  new LinkedBlockingDeque<Toast>();
        BlockingQueue<Toast> butterBlockingQueue1 =  new LinkedBlockingDeque<Toast>();
        BlockingQueue<Toast> toastBlockingQueue2 =  new LinkedBlockingDeque<Toast>();
        BlockingQueue<Toast> butterBlockingQueue2 =  new LinkedBlockingDeque<Toast>();
        BlockingQueue<Toast> eaterBlockingQueue =  new LinkedBlockingDeque<Toast>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(toastBlockingQueue1, "PeanutButter line"));
        executorService.execute(new Butterer(toastBlockingQueue1, butterBlockingQueue1));
        executorService.execute(new PeanutButterer(butterBlockingQueue1, eaterBlockingQueue));

        executorService.execute(new Toaster(toastBlockingQueue2, "Jelly line"));
        executorService.execute(new Butterer(toastBlockingQueue2, butterBlockingQueue2));
        executorService.execute(new Jellier(butterBlockingQueue2, eaterBlockingQueue));

        executorService.execute(new Eater(eaterBlockingQueue));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }


}

enum Status{
    DRY, BUTTERED, JAMMED, PEANUTBUTTERED, JELLIED, TASTY
}

class Toast{
    private final int id;

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public void peanutButter(){
        status = Status.PEANUTBUTTERED;
    }

    public void jelly(){
        status = Status.JELLIED;
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
    String description;

    Toaster(BlockingQueue<Toast> toastBlockingQueue, String description) {
        this.toastBlockingQueue = toastBlockingQueue;
        this.description = description;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500 + random.nextInt(500));
                Toast toast = new Toast(count++);
                System.out.println("Created "+toast+" by "+description);
                toastBlockingQueue.put(toast);
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

//class PeanutButterer implements Runnable{
//
//    private BlockingQueue<Toast> butterBlockingQueue;
//    private BlockingQueue<Toast> jamBlockingQueue;
//
//    PeanutButterer(BlockingQueue<Toast> butterBlockingQueue, BlockingQueue<Toast> jamBlockingQueue) {
//        this.butterBlockingQueue = butterBlockingQueue;
//        this.jamBlockingQueue = jamBlockingQueue;
//    }
//
//    @Override
//    public void run() {
//        try {
//            while (!Thread.interrupted()) {
//                Toast toast = butterBlockingQueue.take();
//                toast.peanutButter();
//                jamBlockingQueue.put(toast);
//            }
//        }catch(InterruptedException ie){
//            System.out.println("PeanutButterer interrupted");
//        }
//        System.out.println("PeanutButterer finishes");
//    }
//}

class PeanutButterer implements Runnable{

    private BlockingQueue<Toast> butterBlockingQueue;
    private BlockingQueue<Toast> jamBlockingQueue;

    PeanutButterer(BlockingQueue<Toast> butterBlockingQueue, BlockingQueue<Toast> jamBlockingQueue) {
        this.butterBlockingQueue = butterBlockingQueue;
        this.jamBlockingQueue = jamBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = butterBlockingQueue.take();
                toast.peanutButter();
                jamBlockingQueue.put(toast);
            }
        }catch(InterruptedException ie){
            System.out.println("PeanutButterer interrupted");
        }
        System.out.println("PeanutButterer finishes");
    }
}

class Jellier implements Runnable{

    private BlockingQueue<Toast> butterBlockingQueue;
    private BlockingQueue<Toast> jamBlockingQueue;

    Jellier(BlockingQueue<Toast> butterBlockingQueue, BlockingQueue<Toast> jamBlockingQueue) {
        this.butterBlockingQueue = butterBlockingQueue;
        this.jamBlockingQueue = jamBlockingQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = butterBlockingQueue.take();
                toast.jelly();
                jamBlockingQueue.put(toast);
            }
        }catch(InterruptedException ie){
            System.out.println("PeanutButterer interrupted");
        }
        System.out.println("PeanutButterer finishes");
    }
}

class Eater implements Runnable{

    private BlockingQueue<Toast> jamBlockingQueue;
    private int peanutButterCounter;
    private int jellyCounter;

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
        if ((toast.getId() != peanutButterCounter && toast.getId() != jellyCounter) ||
                (toast.getStatus() != Status.PEANUTBUTTERED & toast.getStatus() != Status.JELLIED) )
            throw new IllegalStateException();
        if (toast.getStatus() == Status.PEANUTBUTTERED) peanutButterCounter++;
        if (toast.getStatus() == Status.JELLIED) jellyCounter++;
    }
}
