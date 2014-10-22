package concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by bogdan.teut on 22/10/2014.
 */
public class Horse implements Runnable{

    private final int id;
    private CyclicBarrier cyclicBarrier;
    private int strides;
    private Random random = new Random();

    public Horse(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    strides += random.nextInt(3);
                }
                cyclicBarrier.await();
            }
        }catch (InterruptedException ie){
            System.out.println("Interrupted "+this);
        } catch (BrokenBarrierException e) {
            System.out.println("Barrier broken");
        }
    }

    public synchronized int getStrides(){
        return strides;
    }

    public String printStrides(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<getStrides();i++){
            stringBuilder.append("*");
        }
        stringBuilder.append(id);

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Horse "+id;
    }
}
