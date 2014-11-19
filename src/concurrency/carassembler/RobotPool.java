package concurrency.carassembler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class RobotPool {

    private Set<Robot> robots = new HashSet<Robot>();

    public synchronized void hireRobot(Class<? extends Robot> robotType, Assembler assembler) throws InterruptedException {
        for (Robot r:robots){
            if (r.getClass().equals(robotType)){
                robots.remove(r);
                r.engage(assembler);
                return;
            }
        }
        wait();
        hireRobot(robotType, assembler);
    };

    public synchronized void release(Robot r){
        robots.add(r);
        notifyAll();
    }
}
