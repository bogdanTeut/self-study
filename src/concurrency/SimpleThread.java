package concurrency;

/**
 * Created by bogdan on 30/09/14.
 */
public class SimpleThread extends Thread{
    private static int countThread;
    private int countDown = 5;

    public SimpleThread() {
        super(Integer.toString(countThread++));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" +countDown + ")";
    }

    @Override
    public void run() {
        while(true){
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            new SimpleThread();
        }
    }
}
