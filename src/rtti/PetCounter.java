package rtti;

import rtti.individuals.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: bogdan.teut
 * Date: 28/07/14
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 */
public class PetCounter extends HashMap<String,Integer>{

    public void count(String type){
        Integer counter = get(type);
        this.put(type, counter!=null?counter+1:1);
    }

    public void countPets(ArrayList<Pet> pets){
        for (Pet pet:pets){
            if (pet instanceof Cymric) {
                count("Cymric");continue;
            }
            if (pet instanceof Manx) {
                count("Manx");continue;
            }
            if (pet instanceof EgyptianMau) {
                count("EgyptianMau");continue;
            }
            if (pet instanceof Hamster) {
                count("Hamster");continue;
            }
            if (pet instanceof Mouse) {
                count("Mouse");continue;
            }
            if (pet instanceof Mutt) {
                count("Mutt");continue;
            }
            if (pet instanceof Pug) {
                count("Pug");continue;
            }
            if (pet instanceof Rat) {
                count("Rat");continue;
            }
            if (pet instanceof Rodent) {
                count("Rodent");continue;
            }
            if (pet instanceof Cat) {
                count("Cat");continue;
            }
            if (pet instanceof Dog) {
                count("Dog");
            }
        }
    }

    public static void main(String[] args) {
        PetCounter petCounter = new PetCounter();
        //petCounter.countPets(new ForNamePetCreator(), 30);
        LiteralsCreator literalsPetCreator = new LiteralsCreator(10);
        petCounter.countPets(literalsPetCreator.arrayList(30));
        System.out.println(petCounter);
    }

}
