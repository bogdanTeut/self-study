package rtti.io.greenhouse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bogdan.teut on 28/08/2014.
 */
enum EventType{
    SWITCH_LIGHT_ON,
    SWITCH_LIGHT_OFF,
    THERMOSTAT_NIGHT,
    THERMOSTAT_DAY;
    
    private static Map<String, EventType> map = new HashMap<String, EventType>();  
        
    static{
        for (EventType eventType:EventType.values()){
            map.put(getEventTypeAsLiteral(eventType), eventType);            
        }
    }

    public static EventType forName(String type) {
        return map.get(type);            
    }

    public static String getEventTypeAsLiteral(EventType eventType) {
        String[] words = eventType.name().split("_");
        String result = "";
        for (int i=0;i<words.length;i++){
            result += words[i].toLowerCase();                        
        }
        return result;
    }
}
