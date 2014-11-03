package concurrency;

import java.util.*;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * Created by bogdan on 02/11/14.
 */
public class GreenHouseControllerWithDelayQueue {

    private String thermostat = "Night";

    private volatile boolean light;
    private volatile boolean water;

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

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

    DelayQueue<DelayTask> delayQueue = new DelayQueue<DelayTask>();

    public static void main(String[] args) {

        GreenHouseControllerWithDelayQueue greenHouseController = new GreenHouseControllerWithDelayQueue();
        DelayTask terminateTask = greenHouseController.new TerminateTask(5000);
        greenHouseController.delayQueue.add(terminateTask);
//        greenHouseController.schedule(terminateTask, 5000);
        DelayTask bellTask = greenHouseController.new BellTask(1000);
        greenHouseController.delayQueue.add(bellTask);
//        greenHouseController.repeat(bellTask, 0, 1000);
        DelayTask thermostatNightTask = greenHouseController.new ThermostatNightTask(2000);
        greenHouseController.delayQueue.add(thermostatNightTask);
//        greenHouseController.repeat(thermostatNightTask, 0, 2000);
        DelayTask lightOnTask = greenHouseController.new LightOnTask(200);
        greenHouseController.delayQueue.add(lightOnTask);
//        greenHouseController.repeat(lightOnTask, 0, 200);
        DelayTask lightOffTask = greenHouseController.new LightOffTask(400);
        greenHouseController.delayQueue.add(lightOffTask);
//        greenHouseController.repeat(lightOffTask, 0, 400);
        DelayTask waterOnTask = greenHouseController.new WaterOnTask(600);
        greenHouseController.delayQueue.add(waterOnTask);
//        greenHouseController.repeat(waterOnTask, 0, 600);
        DelayTask waterOffTask = greenHouseController.new WaterOffTask(800);
        greenHouseController.delayQueue.add(waterOffTask);
//        greenHouseController.repeat(waterOffTask, 0, 800);
        DelayTask thermostatDayTask = greenHouseController.new ThermostatDayTask(1400);
        greenHouseController.delayQueue.add(thermostatDayTask);
//        greenHouseController.repeat(thermostatDayTask, 0, 1400);
        DelayTask collectDataTask = greenHouseController.new CollectionDataTask(500);
        greenHouseController.delayQueue.add(collectDataTask);
//        greenHouseController.repeat(collectDataTask, 500, 500);

        DelayedTaskConsumer delayedTaskConsumer = greenHouseController.new DelayedTaskConsumer(greenHouseController.delayQueue);
        delayedTaskConsumer.run();
    }

    private class DelayTask implements Delayed, Runnable{

        protected long delay;
        protected long trigger;

        private DelayTask(long delay) {
            this.delay = delay;
            trigger = System.nanoTime() +
                    NANOSECONDS.convert(delay, MILLISECONDS);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayTask other = (DelayTask) o;
            if (trigger < other.trigger) return -1;
            if (trigger > other.trigger) return 1;
            return 0;
        }

        @Override
        public void run() {
            trigger = System.currentTimeMillis() + delay;
            delayQueue.add(this);
        }
    }

    private class TerminateTask extends DelayTask {

        private TerminateTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Terminating");

            new Thread(){
                @Override
                public void run() {
                    for (CollectionData collectionData : data) {
                        System.out.println(collectionData);
                    }
                }
            }.start();
            delayQueue.add(new TerminateTask(delay));
        }
    }

    private class CollectionDataTask extends DelayTask {

        private CollectionDataTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Collecting data");

            synchronized (GreenHouseControllerWithDelayQueue.this){
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
            delayQueue.add(new CollectionDataTask(delay));
        }
    }

    private class ThermostatDayTask extends DelayTask {

        private ThermostatDayTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Setting thermostat to day");
            setThermostat("Day");
            delayQueue.add(new ThermostatDayTask(delay));
        }
    }

    private class WaterOffTask extends DelayTask {

        private WaterOffTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Turning the water off");
            water = false;
            delayQueue.add(new WaterOffTask(delay));
        }
    }

    private class WaterOnTask extends DelayTask {

        private WaterOnTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Turning the water on");
            water = true;
            delayQueue.add(new WaterOnTask(delay));
        }
    }

    private class LightOffTask extends DelayTask {

        private LightOffTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Turning the light off");
            light = true;
            delayQueue.add(new LightOffTask(delay));
        }
    }

    private class LightOnTask extends DelayTask {

        private LightOnTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Turning the light on "+ trigger);
            light = true;
            delayQueue.add(new LightOnTask(delay));
        }
    }

    private class BellTask extends DelayTask {

        private BellTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Bing");
            trigger = System.currentTimeMillis() + delay;
            delayQueue.add(this);
        }
    }

    private class ThermostatNightTask extends DelayTask{

        private ThermostatNightTask(long delay) {
            super(delay);
        }

        @Override
        public void run() {

            System.out.println("Setting thermostat to night");
            setThermostat("Night");
            delayQueue.add(new ThermostatNightTask(delay));
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

    private class DelayedTaskConsumer implements Runnable {
        private DelayQueue<DelayTask> q;
        public DelayedTaskConsumer(DelayQueue<DelayTask> q) {
            this.q = q;
        }
        public void run() {
            try {
                while(!Thread.interrupted())
                    q.take().run(); // Run task with the current thread
            } catch(InterruptedException e) {
            }
            System.out.println("Finished DelayTaskConsumer");
        }
    }
}
