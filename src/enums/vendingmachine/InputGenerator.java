package enums.vendingmachine;

import java.util.Enumeration;
import java.util.Random;

/**
 * Created by bogdan.teut on 22/09/2014.
 */
public class InputGenerator implements Generator<Input>{
    @Override
    public Input next() {
        Input[] values = Input.values();
        return values[new Random().nextInt(values.length)];
    }
}
