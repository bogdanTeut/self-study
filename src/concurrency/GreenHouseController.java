package concurrency;

import java.util.concurrent.*;

/**
 * Created by bogdan on 02/11/14.
 */
public class GreenHouseController {

    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    private String thermostat = "Night";

    private volatile boolean light;

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

    void schedule(Runnable runnable, long delay){
        scheduledThreadPoolExecutor.schedule(runnable, delay, TimeUnit.MILLISECONDS);
    };

    void repeat(Runnable runnable, long initialDelay, long period){
        scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, initialDelay, period, TimeUnit.MILLISECONDS);
    };

    public static void main(String[] args) {

        GreenHouseController greenHouseController = new GreenHouseController();
        greenHouseController.schedule(terminateTask, 5000);
        Runnable bellTask = greenHouseController.new BellTask();
        greenHouseController.repeat(bellTask, 0, 1000);
        Runnable thermostatNightTask = greenHouseController.new ThermostatNightTask();
        greenHouseController.repeat(thermostatNightTask, 0, 2000, TimeUnit.MILLISECONDS);
        Runnable lightOnTask = new LightOnTask();
        greenHouseController.repeat(lightOnTask, 0, 200, TimeUnit.MILLISECONDS);
        greenHouseController.repeat(lightOffTask, 0, 400, TimeUnit.MILLISECONDS);
        greenHouseController.repeat(waterOnTask, 0, 600, TimeUnit.MILLISECONDS);
        greenHouseController.repeat(waterOffTask, 0, 800, TimeUnit.MILLISECONDS);
        greenHouseController.repeat(thermostatDayTask, 0, 1400, TimeUnit.MILLISECONDS);
        greenHouseController.repeat(collectDataTask, 0, 500, TimeUnit.MILLISECONDS);
    }

    private class LightOnTask implements Runnable {
        @Override
        public void run() {
            light = true;
        }
    }

    private class BellTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Bing");
        }
    }

    private class ThermostatNightTask implements Runnable {

        @Override
        public void run() {
            setThermostat("Night");
            System.out.println("Setting thermostat to night");
        }
    }
}
