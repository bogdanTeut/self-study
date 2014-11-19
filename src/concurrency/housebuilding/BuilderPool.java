package concurrency.housebuilding;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bogdan.teut on 13/11/2014.
 */
public class BuilderPool {

    private Set<Builder> builders = new HashSet<Builder>();

    public synchronized void hireBuilder(Class<? extends Builder> builderType, HouseAssembler assembler) throws InterruptedException {
        for (Builder builder:builders){
            if (builder.getClass().equals(builderType)){
                builders.remove(builder);
                builder.engage(assembler);
                return;
            }
        }
        wait();
        hireBuilder(builderType, assembler);
    }

    public synchronized void release(Builder builder) {
        builders.add(builder);
        notifyAll();
    }
}
