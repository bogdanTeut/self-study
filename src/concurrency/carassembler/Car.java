package concurrency.carassembler;

/**
 * Created by bogdan.teut on 12/11/2014.
 */
public class Car {
    private static int counter;
    private int id = counter++;

    private boolean engine, driveTrain, wheels;
    private boolean exhaustSystem;
    private boolean fenders;
    private boolean body;

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
        return "Car "+id+" [ engine "+engine+" , drive train "+driveTrain+" , wheels "+wheels+" ,exhaust system "+exhaustSystem+" ,fenders "+fenders+" ,body "+body+" ]";
    }

    public void setExhaustSystem(boolean exhaustSystem) {
        this.exhaustSystem = exhaustSystem;
    }

    public void setFenders(boolean fenders) {
        this.fenders = fenders;
    }

    public void setBody(boolean body) {
        this.body = body;
    }

    public boolean isBody() {
        return body;
    }
}
