package rtti.io.greenhouse;

import java.util.*;
        

/**
 * Created by bogdan.teut on 28/08/2014.
 */
public class GreenHouseController extends Controller{
    private static boolean light;
    private static String thermostat;
    
    static class SwitchLightOn extends Event{

        SwitchLightOn(long delay) {
            super(delay);
        }

        @Override
        public void action() {
            light =  true;
        }

        static class SwitchLightOnFactory extends Factory{

            @Override
            Event createEvent(long delay) {
                return new SwitchLightOn(delay);
            }
        }
    }
    
    static class SwitchLightOff extends Event{

        SwitchLightOff(long delay) {
            super(delay);
        }

        @Override
        public void action() {
            light = false;
        }

        static class SwitchLightOffFactory extends Factory{

            @Override
            Event createEvent(long delay) {
                return new SwitchLightOff(delay);
            }
        }
    }

    static class ThermostatDay extends Event{

        ThermostatDay(long delay) {
            super(delay);
        }

        @Override
        public void action() {
            thermostat = "day";
        }

        static class ThermostatDayFactory extends Factory{

            @Override
            Event createEvent(long delay) {
                return new ThermostatDay(delay);
            }
        }
    }

    static class ThermostatNight extends Event{

        ThermostatNight(long delay) {
            super(delay);
        }

        @Override
        public void action() {
            thermostat = "night";
        }

        static class ThermostatNightFactory extends Factory{

            @Override
            Event createEvent(long delay) {
                return new ThermostatNight(delay);
            }
        }
    }
    
    static class Restart extends Event{
        
        private Event[] events; 
        
        Restart(long delay, Event[] events) {
            super(delay);
            this.events = events;
            for(Event e : events)
                addEvent(e);
        }

        @Override
        public void action() {
            for (Event event:events){
                event.start();
                addEvent(event);                        
            }
            start();
            addEvent(this);
        }

        static class RestartFactory extends Factory{
            private Event[] events;
            
            RestartFactory(Event[] events) {
                this.events = events;    
            }

            @Override
            Event createEvent(long delay) {
                return new Restart(delay, events);
            }
        }
    }

    public static class Terminate extends Event {
        public Terminate(long delayTime) { 
            super(delayTime); 
        }
        public void action() { 
            System.exit(0); 
        }
        
        public String toString() { return "Terminating"; }

        static class TerminateFactory extends Factory{

            @Override
            Event createEvent(long delay) {
                return new Terminate(delay);
            }
        }
    }
}
