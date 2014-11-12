package concurrency.carassembler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Car> chassisQueue = new LinkedBlockingQueue<Car>(),
                                 reportingQueue = new LinkedBlockingQueue<Car>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ChassisBuilder(chassisQueue));

        RobotPool robotPool = new RobotPool();
        EngineRobot engineRobot = new EngineRobot();
        executorService.execute(engineRobot);
        DriveTrainRobot driveTrainRobot = new DriveTrainRobot();
        executorService.execute(driveTrainRobot);
        WheelsRobot wheelsRobot = new WheelsRobot();
        executorService.execute(wheelsRobot);
        executorService.execute(new Assembler(chassisQueue, reportingQueue, robotPool));
        executorService.execute(new CarReporter(reportingQueue));

        TimeUnit.SECONDS.sleep(200);
        executorService.shutdownNow();
    }
}
