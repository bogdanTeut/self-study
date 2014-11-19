package concurrency.housebuilding;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class SteelBuilder extends Builder implements Runnable{

    protected SteelBuilder(BuilderPool builderPool) {
        super(builderPool);
    }

    @Override
    protected void build() {
        assembler.house.addSteel();
    }
}
