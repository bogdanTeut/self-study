package concurrency.producerconsumer.queues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 16/10/2014.
 */
public class ToastManual {
    public static void main(String[] args) {
        ToastCoordinator toastCoordinator = new ToastCoordinator();
    }
}

class ToastCoordinator{
    Toast toast;
    ToasterManual toasterWithoutBlockingQueues = new ToasterManual(this);
    ButtererManual buttererWithoutBlockingQueues = new ButtererManual(this);
    PeanutButterrerManual peanutButterWithoutBlockingQueues = new PeanutButterrerManual(this);
    JellierManual jellyWithoutBlockingQueues = new JellierManual(this);
    EaterManual eaterWithoutBlockingQueues = new EaterManual(this);
    ExecutorService executorService = Executors.newCachedThreadPool();

    ToastCoordinator() {
        executorService.execute(buttererWithoutBlockingQueues);
        executorService.execute(peanutButterWithoutBlockingQueues);
        executorService.execute(jellyWithoutBlockingQueues);
        executorService.execute(eaterWithoutBlockingQueues);
        executorService.execute(toasterWithoutBlockingQueues);

    }
}

class ToasterManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;

    ToasterManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting ToasterManual");
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                toastCoordinator.toast = new Toast(counter++);
                toastCoordinator.toast.setStatus(Status.DRY);
//                System.out.println(" ToasterManual is creating "+toastCoordinator.toast);

                synchronized (toastCoordinator.buttererWithoutBlockingQueues){
                    toastCoordinator.buttererWithoutBlockingQueues.notifyAll();
                }

                synchronized (this) {
                    while (toastCoordinator.toast.getStatus() != Status.TASTY) {
                        System.out.println(" ToasterManual sleeping");
                        wait();
                    }
                    System.out.println(" ToasterManual is waking up ");

                }
            }
        }catch (InterruptedException ie){
            System.out.println("ToasterManual interrupted");
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
        boolean which = false;
        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.DRY) {
                        System.out.println(" ButtererManual sleeping");
                        wait();
                    }
                    System.out.println(" ButtererManual is waking up for toast "+toastCoordinator.toast);
                }
                toastCoordinator.toast.setStatus(Status.BUTTERED);
//                synchronized (toastCoordinator.jamerWithoutBlockingQueues){
//                    toastCoordinator.jamerWithoutBlockingQueues.notifyAll();
//                }
                if (which){
                    synchronized (toastCoordinator.peanutButterWithoutBlockingQueues){
                        toastCoordinator.peanutButterWithoutBlockingQueues.notifyAll();
                    }
                    which = false;
                }else{
                    synchronized (toastCoordinator.jellyWithoutBlockingQueues){
                        toastCoordinator.jellyWithoutBlockingQueues.notifyAll();
                    }
                    which = true;
                }
                System.out.println(which);
            }
        }catch (InterruptedException ie){
            System.out.println("ButtererManual interrupted");
        }
    }
}

class PeanutButterrerManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;

    PeanutButterrerManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting PeanutButterrerManual");
        try {
            while (!Thread.interrupted()) {
//                synchronized (toastCoordinator){

                    synchronized (this) {
                        while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.BUTTERED) {
                            System.out.println(" PeanutButterrerManual sleeping");
                            wait();
                        }
                        System.out.println(" PeanutButterrerManual is waking up for toast" +toastCoordinator.toast);
                    }

                    toastCoordinator.toast.setStatus(Status.PEANUTBUTTERED);
                    synchronized (toastCoordinator.eaterWithoutBlockingQueues){
                        toastCoordinator.eaterWithoutBlockingQueues.notifyAll();
                    }
                }

//            }
        }catch (InterruptedException ie){
            System.out.println("PeanutButterrerManual interrupted");
        }
    }
}


class JellierManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinator;

    JellierManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting JellierManual");
        try {
            while (!Thread.interrupted()) {
//                synchronized (toastCoordinator){
                    synchronized (this) {
                        while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.BUTTERED) {
                            System.out.println(" JellierManual sleeping");
                            wait();
                        }
                        System.out.println(" JellierManual is waking up for toast" +toastCoordinator.toast);
                    }
                    toastCoordinator.toast.setStatus(Status.JELLIED);
                    synchronized (toastCoordinator.eaterWithoutBlockingQueues){
                        toastCoordinator.eaterWithoutBlockingQueues.notifyAll();
                    }
                }

//            }
        }catch (InterruptedException ie){
            System.out.println("JellierManual interrupted");
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
                    while (toastCoordinator.toast == null ||
                            (toastCoordinator.toast.getStatus() != Status.PEANUTBUTTERED && toastCoordinator.toast.getStatus() != Status.JELLIED)) {
                        System.out.println(" EaterManual sleeping");
                        wait();
                    }
                    System.out.println(" EaterManual is waking up for toast "+toastCoordinator.toast);
                }
                toastCoordinator.toast.setStatus(Status.TASTY);
                checkToast(toastCoordinator.toast);
                System.out.println("Eating "+toastCoordinator.toast);
                if(count++ == 10) {
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
//        if (toast.getId() != count++ || toast.getStatus() != Status.TASTY) throw new IllegalStateException();
    }
}


