package concurrency.housebuilding;

import concurrency.carassembler.Assembler;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public abstract class Builder implements Runnable{

    private boolean engaged;
    protected HouseAssembler assembler;
    protected BuilderPool builderPool;

    protected Builder(BuilderPool builderPool) {
        this.builderPool = builderPool;
    }

    @Override
    public synchronized void run() {
        try{
            powerDown();
            while (!Thread.interrupted()){
                build();
                TimeUnit.MILLISECONDS.sleep(1000);
                assembler.cyclicBarrier.await();
                powerDown();
            }
        }catch (InterruptedException ie){
            System.out.println(this.getClass().getSimpleName()+" interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("CyclicBarrier interrupted");
        }
    }

    public synchronized void engage(HouseAssembler assembler) throws InterruptedException {
        this.assembler = assembler;
        engaged = true;
        notifyAll();
    }

    private synchronized void powerDown() throws InterruptedException {
        engaged = false;
        builderPool.release(this);
        while (!engaged){
            wait();
        }
    }

    protected abstract void build();

}
