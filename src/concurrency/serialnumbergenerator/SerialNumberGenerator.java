package concurrency.serialnumbergenerator;

/**
 * Created by bogdan.teut on 03/10/2014.
 */
public class SerialNumberGenerator {
    private volatile int serialNumber;
    
    public int generateNext(){
        return serialNumber++;    
    }
}
