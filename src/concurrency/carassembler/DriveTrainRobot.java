package concurrency.carassembler;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class DriveTrainRobot extends Robot implements Runnable{

    public DriveTrainRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    public void performService() {
        assembler.car.setDriveTrain(true);
    }
}
