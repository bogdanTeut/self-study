package io.greenhouse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by bogdan.teut on 28/08/2014.
 */
public class EventFileReader extends ArrayList<String> {

    public EventFileReader(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src//rtti//io//greenhouse//"+fileName));
        String s = null;
        while ((s = bufferedReader.readLine()) != null){
            add(s);    
        }
    }
}
