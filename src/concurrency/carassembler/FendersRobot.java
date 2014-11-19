package concurrency.carassembler;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class FendersRobot extends Robot implements Runnable{

    public FendersRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    public void performService() {
        assembler.car.setFenders(true);
    }

}
