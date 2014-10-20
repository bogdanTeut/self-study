package concurrency.producerconsumer.queues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bogdan.teut on 16/10/2014.
 */
public class ToastManual {
    public static void main(String[] args) {

        ToastCoordinator coordinatorLineOne = new ToastCoordinator("One");
        ToasterManual toasterManualOne = new ToasterManual(coordinatorLineOne);
        ButtererManual buttererManualOne = new ButtererManual(coordinatorLineOne);
        PeanutButterrerManual peanutButterrerManual = new PeanutButterrerManual(coordinatorLineOne);

        ToastCoordinator coordinatorLineTwo = new ToastCoordinator("Two");
        ToasterManual toasterManualTwo = new ToasterManual(coordinatorLineTwo);
        ButtererManual buttererManualTwo = new ButtererManual(coordinatorLineTwo);
        JellierManual jellierManual = new JellierManual(coordinatorLineTwo);

        EaterManual eaterManual = new EaterManual(coordinatorLineOne, coordinatorLineTwo);


        coordinatorLineOne.setToasterManual(toasterManualOne);
        coordinatorLineOne.setButtererManual(buttererManualOne);
        coordinatorLineOne.setPeanutButtererOJellierManual(peanutButterrerManual);
        coordinatorLineOne.setEaterManual(eaterManual);

        coordinatorLineTwo.setToasterManual(toasterManualTwo);
        coordinatorLineTwo.setButtererManual(buttererManualTwo);
        coordinatorLineTwo.setPeanutButtererOJellierManual(jellierManual);
        coordinatorLineTwo.setEaterManual(eaterManual);

        coordinatorLineOne.startTestCoordinator();
        coordinatorLineTwo.startTestCoordinator();
    }
}

class ToastCoordinator{
    Toast toast;
    ToasterManual toasterManual;
    ButtererManual buttererManual;
    Runnable peanutButtererOJellierManual;
    EaterManual eaterManual;
    ExecutorService executorService = Executors.newCachedThreadPool();
    String description;


    ToastCoordinator(String description) {
        this.description = description;
    }

    public void setToasterManual(ToasterManual toasterManual) {
        this.toasterManual = toasterManual;
    }

    public void setButtererManual(ButtererManual buttererManual) {
        this.buttererManual = buttererManual;
    }

    public void setPeanutButtererOJellierManual(Runnable peanutButtererOJellierManual) {
        this.peanutButtererOJellierManual = peanutButtererOJellierManual;
    }

    public void setEaterManual(EaterManual eaterManual) {
        this.eaterManual = eaterManual;
    }

    public void startTestCoordinator(){
        executorService.execute(buttererManual);
        executorService.execute(peanutButtererOJellierManual);
        if (description.equals("Two")) executorService.execute(eaterManual);
        executorService.execute(toasterManual);
    }
}

class ToasterManual implements Runnable{

    static AtomicInteger atomicInteger = new AtomicInteger();
    private static int counter;
    private ToastCoordinator toastCoordinator;

    ToasterManual(ToastCoordinator toastCoordinator) {
        this.toastCoordinator = toastCoordinator;
    }

    @Override
    public void run() {
        System.out.println(" Starting ToasterManual from Toaster Coordinator "+toastCoordinator.description);
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                toastCoordinator.toast = new Toast(atomicInteger.incrementAndGet());
                toastCoordinator.toast.setStatus(Status.DRY);
                System.out.println(" ToasterManual is creating "+toastCoordinator.toast+ " from toast coordinator "+toastCoordinator.description);

                synchronized (toastCoordinator.buttererManual){
                    toastCoordinator.buttererManual.notifyAll();
                }

                synchronized (this) {
                    while (toastCoordinator.toast.getStatus() != Status.TASTY) {
                        System.out.println(" ToasterManual sleeping from Toaster Coordinator "+toastCoordinator.description);
                        wait();
                    }
                    System.out.println(" ToasterManual is waking up from Toaster Coordinator "+toastCoordinator.description);

                }
            }
        }catch (InterruptedException ie){
            System.out.println("ToasterManual interrupted from Toaster Coordinator "+toastCoordinator.description);
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
        System.out.println(" Starting ButtererManual from Toaster Coordinator "+toastCoordinator.description);
        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.DRY) {
                        System.out.println(" ButtererManual sleeping from Toaster Coordinator "+toastCoordinator.description);
                        wait();
                    }
                    System.out.println(" ButtererManual is waking up for toast "+toastCoordinator.toast+" from Toaster Coordinator "+toastCoordinator.description);
                }
                toastCoordinator.toast.setStatus(Status.BUTTERED);

                synchronized (toastCoordinator.peanutButtererOJellierManual){
                    toastCoordinator.peanutButtererOJellierManual.notifyAll();
                }
            }
        }catch (InterruptedException ie){
            System.out.println("ButtererManual interrupted from Toaster Coordinator "+toastCoordinator.description);
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
        System.out.println(" Starting PeanutButterrerManual from Toaster Coordinator "+toastCoordinator.description);
        try {
            while (!Thread.interrupted()) {

                    synchronized (this) {
                        while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.BUTTERED) {
                            System.out.println(" PeanutButterrerManual sleeping from Toaster Coordinator "+toastCoordinator.description);
                            wait();
                        }
                        System.out.println(" PeanutButterrerManual is waking up for toast" +toastCoordinator.toast+" from Toaster Coordinator "+toastCoordinator.description);
                    }

                    toastCoordinator.toast.setStatus(Status.PEANUTBUTTERED);
                    synchronized (toastCoordinator.eaterManual){
                        toastCoordinator.eaterManual.notifyAll();
                    }
                }

        }catch (InterruptedException ie){
            System.out.println("PeanutButterrerManual interrupted from Toaster Coordinator "+toastCoordinator.description);
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
        System.out.println(" Starting JellierManual from Toaster Coordinator "+toastCoordinator.description);
        try {
            while (!Thread.interrupted()) {
//                synchronized (toastCoordinator){
                    synchronized (this) {
                        while (toastCoordinator.toast == null || toastCoordinator.toast.getStatus() != Status.BUTTERED) {
                            System.out.println(" JellierManual sleeping from Toaster Coordinator "+toastCoordinator.description);
                            wait();
                        }
                        System.out.println(" JellierManual is waking up for toast" +toastCoordinator.toast);
                    }
                    toastCoordinator.toast.setStatus(Status.JELLIED);
                    synchronized (toastCoordinator.eaterManual){
                        toastCoordinator.eaterManual.notifyAll();
                    }
                }

//            }
        }catch (InterruptedException ie){
            System.out.println("JellierManual interrupted from Toaster Coordinator "+toastCoordinator.description);
        }
    }
}

class EaterManual implements Runnable{

    private int counter;
    private ToastCoordinator toastCoordinatorOne;
    private ToastCoordinator toastCoordinatorTwo;
    private int count;

    public EaterManual(ToastCoordinator toastCoordinatorOne, ToastCoordinator toastCoordinatorTwo) {
        this.toastCoordinatorOne = toastCoordinatorOne;
        this.toastCoordinatorTwo = toastCoordinatorTwo;
    }

    @Override
    public void run() {
        System.out.println(" Starting EaterManual");
        try {
            while (!Thread.interrupted()) {

                synchronized (this) {
                    while ((toastCoordinatorOne.toast == null || toastCoordinatorOne.toast.getStatus() != Status.PEANUTBUTTERED)
                    && (toastCoordinatorTwo.toast == null || toastCoordinatorTwo.toast.getStatus() != Status.JELLIED))  {
                        System.out.println(" EaterManual sleeping");
                        wait();
                    }
                    TimeUnit.MILLISECONDS.sleep(200);
                }

                if (toastCoordinatorOne.toast.getStatus() == Status.PEANUTBUTTERED){
                    toastCoordinatorOne.toast.setStatus(Status.TASTY);
                    System.out.println("Eating " + toastCoordinatorOne.toast + " coordinator " + toastCoordinatorOne.description);

                    synchronized (toastCoordinatorOne.toasterManual){
                        toastCoordinatorOne.toasterManual.notifyAll();
                    }
                }

                if (toastCoordinatorTwo.toast.getStatus() == Status.JELLIED){
                    toastCoordinatorTwo.toast.setStatus(Status.TASTY);
                    System.out.println("Eating "+toastCoordinatorTwo.toast+" coordinator "+toastCoordinatorTwo.description);

                    synchronized (toastCoordinatorTwo.toasterManual){
                        toastCoordinatorTwo.toasterManual.notifyAll();
                    }
                }
//                checkToast(toastCoordinatorOne.toast);

                if(count++ == 10) {
                    System.out.println("Out of food, closing");
                    toastCoordinatorOne.executorService.shutdownNow();
                    toastCoordinatorTwo.executorService.shutdownNow();
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


