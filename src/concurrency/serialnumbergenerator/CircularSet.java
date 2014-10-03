package concurrency.serialnumbergenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bogdan.teut on 03/10/2014.
 */
public class CircularSet {
    
    private int[] circularSet;
    private int size;
    private int index;

    public CircularSet(int size) {
        this.size = size;
        circularSet = new int[size];
        for(int i=0;i<size;i++){
            circularSet[i] = -1;
        }
    }
    
    public synchronized boolean contains(int value){
        for(int i=0;i<size;i++){
            if (circularSet[i] == value){
                return true;
            }    
        }
        return false;
    }
    
    public synchronized void add(int value){
        circularSet[index++%size] = value;        
    }
}
