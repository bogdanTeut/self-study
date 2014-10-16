package concurrency.producerconsumer.queues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 16/10/2014.
 */
public class ToastManul {
    public static void main(String[] args) {
        ToastCoordinator toastCoordinator = new ToastCoordinator();
    }
}

class ToastCoordinator{
    Toast toast;
    ToasterManul toasterWithoutBlockingQueues = new ToasterManul(this);
    ButtererManual buttererWithoutBlockingQueues = new ButtererManual(this);
    JamerManual jamerWithoutBlockingQueues = new JamerManual(this);
    EaterManual eaterWithoutBlockingQueues = new EaterManual(this);
    ExecutorService executorService = Executors.newCachedThreadPool();

    ToastCoordinator() {
        executorService.execute(buttererWithoutBlockingQueues);
        executorService.execute(jamerWithoutBlockingQueues);
        executorService.execute(eaterWithoutBlockingQueues);
        executorService.execute(toasterWithoutBlockingQueues);

    }
}

class ToasterManul implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;

    ToasterManul(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting ToasterManul");
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                toastCoordinator.toast = new Toast(counter++);
                toastCoordinator.toast.setStatus(Status.DRY);

                synchronized (toastCoordinator.buttererWithoutBlockingQueues){
                    toastCoordinator.buttererWithoutBlockingQueues.notifyAll();
                }

                synchronized (this) {
                    while (toastCoordinator.toast.getStatus() != Status.TASTY) {
                        System.out.println(" ToasterManul sleeping");
                        wait();
                    }
                    System.out.println(" ToasterManul is waking up");
                }
            }
        }catch (InterruptedException ie){
            System.out.println("ToasterManul interrupted");
        }
    }
}

class ButtererManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;

    ButtererManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting ButtererManual");
        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.DRY) {
                        System.out.println(" ButtererManual sleeping");
                        wait();
                    }
                    System.out.println(" ButtererManual is waking up");
                }
                toastCoordinator.toast.setStatus(Status.BUTTERED);
                synchronized (toastCoordinator.jamerWithoutBlockingQueues){
                    toastCoordinator.jamerWithoutBlockingQueues.notifyAll();
                }

            }
        }catch (InterruptedException ie){
            System.out.println("ButtererManual interrupted");
        }
    }
}

class JamerManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;

    JamerManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting JamerManual");
        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.BUTTERED) {
                        System.out.println(" JamerManual sleeping");
                        wait();
                    }
                    System.out.println(" JamerManual is waking up");
                }
                toastCoordinator.toast.setStatus(Status.JAMMED);
                synchronized (toastCoordinator.eaterWithoutBlockingQueues){
                    toastCoordinator.eaterWithoutBlockingQueues.notifyAll();
                }

            }
        }catch (InterruptedException ie){
            System.out.println("JamerManual interrupted");
        }
    }
}

class EaterManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;
    private int count;

    EaterManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting EaterManual");
        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.JAMMED) {
                        System.out.println(" EaterManual sleeping");
                        wait();
                    }
                    System.out.println(" EaterManual is waking up");
                }
                toastCoordinator.toast.setStatus(Status.TASTY);
                checkToast(toastCoordinator.toast);
                System.out.println("Eating "+toastCoordinator.toast);
                if(count == 10) {
                    System.out.println("Out of food, closing");
                    toastCoordinator.executorService.shutdownNow();
                }
                synchronized (toastCoordinator.toasterWithoutBlockingQueues){
                    toastCoordinator.toasterWithoutBlockingQueues.notifyAll();
                }

            }
        }catch (InterruptedException ie){
            System.out.println("EaterManual interrupted");
        }
    }

    private void checkToast(Toast toast) {
        if (toast.getId() != count++ || toast.getStatus() != Status.TASTY) throw new IllegalStateException();
    }
}


