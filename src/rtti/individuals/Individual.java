package rtti.individuals;

/**
 * Created by bogdan on 27/07/14.
 */
public class Individual {

    private static int counter;

    private final int id = counter++;

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+" : "+id;
    }
}
