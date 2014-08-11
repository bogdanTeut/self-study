package rtti;

import rtti.individuals.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bogdan.teut
 * Date: 28/07/14
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public class LiteralsCreator extends AbstractCreator<Pet> {

    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

    public LiteralsCreator(int size) {
        super(size, Pet.class);
    }

    @Override
    protected List<Class<? extends Pet>> types() {
        return new ArrayList<Class<? extends Pet>>(Arrays.asList(classes));
    }

    private static Class<? extends Pet>[] classes = new Class[]{
            Cat.class,
            Cymric.class,
            Dog.class,
            EgyptianMau.class,
            Hamster.class,
            Manx.class,
            Mouse.class,
            Mutt.class,
            Pug.class,
            Rat.class,
            Rodent.class
    };
}
