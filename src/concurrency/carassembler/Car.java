package concurrency.carassembler;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class Car {
    private static int counter;
    private int id = counter++;

    private boolean engine, driveTrain, wheels;

    public void setEngine(boolean engine) {
        this.engine = engine;
    }

    public void setDriveTrain(boolean driveTrain) {
        this.driveTrain = driveTrain;
    }

    public void setWheels(boolean wheels) {
        this.wheels = wheels;
    }

    @Override
    public String toString() {
        return "Car "+id+" [ engine "+engine+" , drive train "+driveTrain+" , wheels "+wheels+" ]";
    }
}
