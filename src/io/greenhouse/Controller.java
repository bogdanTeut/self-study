package io.greenhouse;

import java.util.*;

/**
 * Created by bogdan.teut on 28/08/2014.
 */
public class Controller {
    private static List<Event> events = new ArrayList<Event>(); 
    
    public static void addEvent(Event event){
        events.add(event);        
    }                     
    
    public void run(){
        while (events.size()>0){
            for (Event event : new ArrayList<Event>(events)) {
                if (event.ready()) {
                    System.out.println(event);
                    event.action();
                    events.remove(event);
                }
            }
        }
    }
}
