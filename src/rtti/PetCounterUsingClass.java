package rtti;

import rtti.individuals.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: bogdan.teut
 * Date: 28/07/14
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 */
public class PetCounterUsingClass extends HashMap<Class<? extends Pet>,Integer>{

    public void count(Class<? extends Pet> type){
        Integer counter = get(type);
        this.put(type, counter!=null?counter+1:1);
    }

    public void countPets(ArrayList<Pet> pets){
        for (Pet pet:pets){
            count(pet.getClass());
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("{");
        for (Map.Entry< Class<? extends Pet>, Integer > entry:entrySet()){
            sb.append(entry.getKey().getSimpleName());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        PetCounterUsingClass petCounter = new PetCounterUsingClass();
        //petCounter.countPets(new ForNamePetCreator(), 30);
        LiteralsCreator literalsPetCreator = new LiteralsCreator(10);
        petCounter.countPets(literalsPetCreator.arrayList(30));
        System.out.println(petCounter);
    }

}
