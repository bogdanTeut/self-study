package rtti.io;

import java.util.*;

/**
 * Created by bogdan.teut on 27/08/2014.
 */

//SERVER (LIBRARY)
class Event{
    
}

class SwitchLightOn extends Event{
}

class SwitchLightOff extends Event{
}

enum EventType{
    SWITCH_ON,
    SWITCH_OFF;
}

abstract class Factory{
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

    private static List<Event> events = new ArrayList<Event>();

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        
        Event switchLightOn = new SwitchLightOnFactory().createEvent();
        Event switchLightOff = new SwitchLightOffFactory().createEvent();  
        events.add(switchLightOn);
        events.add(switchLightOff);

        System.out.println(events);
    }
}
