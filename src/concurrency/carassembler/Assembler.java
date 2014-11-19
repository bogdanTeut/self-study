package concurrency.carassembler;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class Assembler implements Runnable {

    private LinkedBlockingQueue<Car> chassisQueue;
    private LinkedBlockingQueue<Car> reportingQueue;
    protected CyclicBarrier cyclicBarrier = new CyclicBarrier(7);
    protected Car car;
    private RobotPool robotPool;

    public Assembler(LinkedBlockingQueue<Car> chassisQueue, LinkedBlockingQueue<Car> reportingQueue, RobotPool robotPool) {
        this.chassisQueue = chassisQueue;
        this.reportingQueue = reportingQueue;
        this.robotPool = robotPool;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                car = chassisQueue.take();

                robotPool.hireRobot(EngineRobot.class, this);

                robotPool.hireRobot(DriveTrainRobot.class, this);

                robotPool.hireRobot(WheelsRobot.class, this);

                robotPool.hireRobot(BodyRobot.class, this);

                robotPool.hireRobot(FendersRobot.class, this);

                robotPool.hireRobot(ExhaustSystemRobot.class, this);

                cyclicBarrier.await();
                reportingQueue.put(car);
            }
        }catch (InterruptedException ie){
            System.out.println("Assembler interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println("CyclicBarrier interrupted");
        }
    }
}
