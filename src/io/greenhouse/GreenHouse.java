package io.greenhouse;

import java.io.IOException;
import java.util.*;

import static io.greenhouse.GreenHouseController.Restart.RestartFactory;
import static io.greenhouse.GreenHouseController.SwitchLightOff.SwitchLightOffFactory;
import static io.greenhouse.GreenHouseController.SwitchLightOn.SwitchLightOnFactory;
import static io.greenhouse.GreenHouseController.Terminate.TerminateFactory;
import static io.greenhouse.GreenHouseController.ThermostatDay.ThermostatDayFactory;
import static io.greenhouse.GreenHouseController.ThermostatNight.ThermostatNightFactory;

//CLIENT
public class GreenHouse {

    private static List<Event> events = new ArrayList<Event>();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
        
        //read from file
       List<String> eventsFile = new EventFileReader("events_file");
       Event[] events = new Event[eventsFile.size()];
        adaptFromStringsToEvents(eventsFile, events);
        

        GreenHouseController gc = new GreenHouseController();
        
        gc.addEvent(new RestartFactory(events).createEvent(0));
        if(args.length == 1)
            gc.addEvent(
                    new TerminateFactory().createEvent(
                            new Integer(args[0])));
        gc.run();
    }

    private static void adaptFromStringsToEvents(List<String> eventsFile, Event[] events) {
        for (int i=0;i<eventsFile.size();i++){
            events[i]=(getFactory(eventsFile.get(i)).createEvent(0));
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
