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
    protected CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
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

                Robot engineRobot = (EngineRobot) robotPool.getRobot(EngineRobot.class);
                engineRobot.engage(this);

                Robot driveTrainRobot = robotPool.getRobot(DriveTrainRobot.class);
                driveTrainRobot.engage(this);

                Robot wheelsRobot = robotPool.getRobot(WheelsRobot.class);
                wheelsRobot.engage(this);

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
