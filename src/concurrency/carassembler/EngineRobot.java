package concurrency.carassembler;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class EngineRobot extends Robot implements Runnable{

    @Override
    public void performService() {
        assembler.car.setEngine(true);
    }

}
