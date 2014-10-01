package concurrency;

/**
 * Created by bogdan on 28/09/14.
 */
public class TaskRunThreeTimes implements Runnable {

    private static int count;
    private final int id =  count++;

    public TaskRunThreeTimes() {
        System.out.println("Constructor of TaskRunThreeTimes "+id);
    }

    @Override
    public void run() {
        System.out.printf("first call "+id);
        Thread.yield();
        System.out.printf("second call "+id);
        Thread.yield();
        System.out.printf("third call "+id);
        Thread.yield();
        System.out.println("Finishing "+id);
    }

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            Thread t = new Thread(new TaskRunThreeTimes());
            t.start();
        }
    }
}
