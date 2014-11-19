package concurrency.housebuilding;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class ConcreteFormsBuilder extends Builder implements Runnable{

    protected ConcreteFormsBuilder(BuilderPool builderPool) {
        super(builderPool);
    }

    @Override
    protected void build() {
        assembler.house.addConcreteForms();
    }
}
