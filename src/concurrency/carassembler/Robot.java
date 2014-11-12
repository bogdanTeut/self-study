package concurrency.carassembler;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public abstract class Robot implements Runnable{

    protected boolean engaged;
    protected Assembler assembler;

    @Override
    public synchronized void run() {
        try{
            powerDown();
            while (!Thread.interrupted()){
                performService();
                TimeUnit.MILLISECONDS.sleep(2000);
                assembler.cyclicBarrier.await();
                powerDown();
            }
        }catch (InterruptedException ie){
            System.out.println(this.getClass().getSimpleName()+" interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("Cyclic barrier interrupted inside "+this.getClass().getSimpleName());
        }
    }

    public abstract void performService();

    public synchronized void engage(Assembler assembler){
        this.assembler = assembler;
        engaged = true;
        notifyAll();
    }

    public synchronized void powerDown() throws InterruptedException {
        engaged = false;
        while (!engaged){
            wait();
        }
    }
}
