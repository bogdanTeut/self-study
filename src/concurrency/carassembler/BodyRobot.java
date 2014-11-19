package concurrency.carassembler;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class BodyRobot extends Robot implements Runnable{

    public BodyRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    public void performService() {
        assembler.car.setBody(true);
    }

}
