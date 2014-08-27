package rtti.io;

import java.util.*;

/**
 * Created by bogdan.teut on 27/08/2014.
 */

//SERVER (LIBRARY)
class Event{
    
}

class SwitchLightOn extends Event{
//    static {
//        Factory.addToMap(EventType.SWITCH_ON, new SwitchLightOn());
//    }    
}

class SwitchLightOff extends Event{
//    static {
//        Factory.addToMap(EventType.SWITCH_OFF, new SwitchLightOff());
//    }
}

enum EventType{
    SWITCH_ON,
    SWITCH_OFF;
}

abstract class Factory{
    //private static Map<EventType, Event> maps = new HashMap<EventType, Event>();
    
//    public static void addToMap(EventType eventType,Event clazz){
//        maps.put(eventType, clazz);        
//    }
//    
//    public static Event createEvent(EventType eventType) throws IllegalAccessException, InstantiationException {
//        return maps.get(eventType);        
////        switch (eventType){
////            case SWITCH_ON: return new SwitchLightOn();            
////            case SWITCH_OFF: return new SwitchLightOff();            
////        }
////        return null;
//    };
        abstract Event createEvent();
}

class SwitchLightOnFactory extends Factory{

    @Override
    Event createEvent() {
        return new SwitchLightOn();
    }
}

class SwitchLightOffFactory extends Factory{

    @Override
    Event createEvent() {
        return new SwitchLightOff();
    }
}

//CLIENT
public class GreenHouse {
    static{
        try {
            Class.forName("rtti.io.SwitchLightOn");
            Class.forName("rtti.io.SwitchLightOff");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
    private static List<Event> events = new ArrayList<Event>();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        
        Event switchLightOn = new SwitchLightOnFactory().createEvent();
        Event switchLightOff = new SwitchLightOffFactory().createEvent();  
        events.add(switchLightOn);
        events.add(switchLightOff);

        System.out.println(events);
    }
}
