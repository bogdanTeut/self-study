package concurrency.carassembler;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class ExhaustSystemRobot extends Robot implements Runnable{

    public ExhaustSystemRobot(RobotPool robotPool) {
        super(robotPool);
    }

    @Override
    public void performService() {
        assembler.car.setExhaustSystem(true);
    }

}
