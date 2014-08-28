package rtti.io.greenhouse;

import java.io.IOException;
import java.util.*;

import static rtti.io.greenhouse.GreenHouseController.Restart.RestartFactory;
import static rtti.io.greenhouse.GreenHouseController.SwitchLightOff.SwitchLightOffFactory;
import static rtti.io.greenhouse.GreenHouseController.SwitchLightOn.SwitchLightOnFactory;
import static rtti.io.greenhouse.GreenHouseController.Terminate.TerminateFactory;
import static rtti.io.greenhouse.GreenHouseController.ThermostatDay.ThermostatDayFactory;
import static rtti.io.greenhouse.GreenHouseController.ThermostatNight.ThermostatNightFactory;

//CLIENT
public class GreenHouse {

    private static List<Event> events = new ArrayList<Event>();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        
        //read from file
       List<String> eventsFile = new EventFileReader("events_file");
       List<Factory> factories = new ArrayList<Factory>();    
        //method/collaborator to select proper generator
        adaptFromStringsToGenerators(eventsFile, factories);
        
        //method which take generator and puts it within a list

        System.out.println(EventType.forName("thermostatnight"));
        
        Event[] eventList =  null;
        //filler(eventList, size);

        GreenHouseController gc = new GreenHouseController();

//        gc.addEvent(gc.new Bell(900));
//        Event[] eventList = {
//                new ThermostatNightFactory().createEvent(0),
//                new SwitchLightOnFactory().createEvent(0),
//                new SwitchLightOffFactory().createEvent(0),
////                gc.new WaterOn(600),
////                gc.new WaterOff(800),
//                new ThermostatDayFactory().createEvent(0)
//        };
        gc.addEvent(new RestartFactory(eventList).createEvent(0));
        if(args.length == 1)
            gc.addEvent(
                    new TerminateFactory().createEvent(
                            new Integer(args[0])));
        gc.run();
    }

    private static void adaptFromStringsToGenerators(List<String> eventsFile, List<Factory> factories) {
        for (String event:eventsFile){
            factories.add(getFactory(event));
        }    
    }

    private static Factory getFactory(String event) {
        EventType eventType = EventType.forName(event);
        switch (eventType){
            case SWITCH_LIGHT_OFF: return new SwitchLightOffFactory();             
            case SWITCH_LIGHT_ON: return new SwitchLightOnFactory();             
            case THERMOSTAT_DAY: return new ThermostatDayFactory();             
            case THERMOSTAT_NIGHT: return new ThermostatNightFactory();
            default: return null;
        }
        
    }

    static void filler (Event[] eventList, int size){
        
    }
}
