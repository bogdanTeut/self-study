package rtti;

import rtti.individuals.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: bogdan.teut
 * Date: 28/07/14
 * Time: 09:54
 * To change this template use File | Settings | File Templates.
 */
public class ForNameCreator extends AbstractCreator<Pet> {

    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

    public ForNameCreator(int size) {
        super(size, Pet.class);
    }

    @Override
    protected List<Class<? extends Pet>> types() {
        return types;
    }

    private static String[] classes = {
            "rtti.individuals.Cat",
            "rtti.individuals.Cymric",
            "rtti.individuals.Dog",
            "rtti.individuals.EgyptianMau",
            "rtti.individuals.Hamster",
            "rtti.individuals.Manx",
            "rtti.individuals.Mouse",
            "rtti.individuals.Mutt",
            "rtti.individuals.Pug",
            "rtti.individuals.Rat",
            "rtti.individuals.Rodent",
    };

    private static void loader(){
        for (String type:classes){
            try {
                Class<? extends Pet> classReference = (Class<? extends Pet>)Class.forName(type);
                types.add(classReference);
            } catch (ClassNotFoundException e) {
                System.out.println("Class could not be found");
            }
        }
    }

    static{loader();}
}
