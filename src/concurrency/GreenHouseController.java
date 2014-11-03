package concurrency;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by bogdan on 02/11/14.
 */
public class GreenHouseController {

    static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
    private String thermostat = "Night";

    private volatile boolean light;
    private volatile boolean water;

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

    List<CollectionData> data = Collections.synchronizedList(new ArrayList<CollectionData>());
    Calendar lastTime = Calendar.getInstance();
    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }
    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);

    public static void main(String[] args) {

        GreenHouseController greenHouseController = new GreenHouseController();
        Runnable terminateTask = greenHouseController.new TerminateTask();
        greenHouseController.schedule(terminateTask, 5000);
        Runnable bellTask = greenHouseController.new BellTask();
        greenHouseController.repeat(bellTask, 0, 1000);
        Runnable thermostatNightTask = greenHouseController.new ThermostatNightTask();
        greenHouseController.repeat(thermostatNightTask, 0, 2000);
        Runnable lightOnTask = greenHouseController.new LightOnTask();
        greenHouseController.repeat(lightOnTask, 0, 200);
        Runnable lightOffTask = greenHouseController.new LightOffTask();
        greenHouseController.repeat(lightOffTask, 0, 400);
        Runnable waterOnTask = greenHouseController.new WaterOnTask();
        greenHouseController.repeat(waterOnTask, 0, 600);
        Runnable waterOffTask = greenHouseController.new WaterOffTask();
        greenHouseController.repeat(waterOffTask, 0, 800);
        Runnable thermostatDayTask = greenHouseController.new ThermostatDayTask();
        greenHouseController.repeat(thermostatDayTask, 0, 1400);
        Runnable collectDataTask = greenHouseController.new CollectionDataTask();
        greenHouseController.repeat(collectDataTask, 500, 500);
    }

    private class TerminateTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Terminating");
            scheduledThreadPoolExecutor.shutdownNow();

            new Thread(){
                @Override
                public void run() {
                    for (CollectionData collectionData : data) {
                        System.out.println(collectionData);
                    }
                }
            }.start();
        }
    }

    private class CollectionDataTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Collecting data");

            synchronized (GreenHouseController.this){
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE)+30);

                if(rand.nextInt(5)==4){
                    humidityDirection = -humidityDirection;
                }
                lastHumidity += humidityDirection*rand.nextFloat();

                if(rand.nextInt(5)==4){
                    tempDirection = -tempDirection;
                }
                lastTemp += tempDirection*(1.0f+rand.nextFloat());

                data.add(new CollectionData((Calendar)lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }

    private class ThermostatDayTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Setting thermostat to day");
            setThermostat("Day");
        }
    }

    private class WaterOffTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning the water off");
            water = false;
        }
    }

    private class WaterOnTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning the water on");
            water = true;
        }
    }

    private class LightOffTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Turning the light off");
            light = true;
        }
    }

    private class LightOnTask implements Runnable {
        @Override
        public void run() {
            System.out.println("Turning the light on");
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
            System.out.println("Setting thermostat to night");
            setThermostat("Night");
        }
    }

    private class CollectionData {
        final Calendar calendar;
        final float temperature;
        final float humidity;

        private CollectionData(Calendar calendar, float temperature, float humidity) {
            this.calendar = calendar;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return calendar.getTime() + String.format("temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }
}
