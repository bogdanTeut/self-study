package concurrency.housebuilding;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class HouseAssembler implements Runnable {

    private LinkedBlockingQueue<House> footingsQueue;
    private LinkedBlockingQueue<House> steelConcreteQueue;
    protected CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    protected House house;
    private BuilderPool builderPool;

    public HouseAssembler(LinkedBlockingQueue<House> footingsQueue, LinkedBlockingQueue<House> steelConcreteQueue, BuilderPool builderPool) {
        this.footingsQueue = footingsQueue;
        this.steelConcreteQueue = steelConcreteQueue;
        this.builderPool = builderPool;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                house = footingsQueue.take();

                builderPool.hireBuilder(SteelBuilder.class, this);
                builderPool.hireBuilder(ConcreteFormsBuilder.class, this);

                cyclicBarrier.await();
                steelConcreteQueue.put(house);
            }
        }catch (InterruptedException ie){
            System.out.println("HouseAssembler interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("CyclicBarrier interrupted");
        }
    }
}
