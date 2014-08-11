package rtti.individuals;

import java.util.*;

/**
 * Created by bogdan.teut on 04/08/2014.
 */
public class Javapc {

    public static void main(String[] args) {
//        Dog dog = new Dog();
//        Pet pet = dog;
//        Dog dog2 = (Dog)pet;

        Dog[] dogs = new Dog[5];
        Pet[] pets = new Pet[5];
        Pet[] animals =  new Dog[5];
        pets = dogs;

        //they are both failing at runtime
//        animals[0] = new Pet();
//        animals[1] = new Cat();
        //wrong pets[0] is a pet
        //Dog dog = pets[0];

        List<Pet> petList = new ArrayList<Pet>();
        petList.add(new Dog());
        List<Dog> dogList = new ArrayList<Dog>();
        //dogList.add(new Pet());
        List rawList = new ArrayList();
        rawList.add(1);

        List<?> unbounded = Arrays.asList(1);
        //unbounded.add(1);
        List<? extends Integer> extendsBound =  Arrays.asList(1);
        //extendsBound.add(new Pet());

        List<? super Integer> superBound = new ArrayList<Integer>();
        superBound.add(1);

        Holder<List<?>> rawListHolder = new Holder<List<?>>(rawList);
        doSomethingWithIt(rawListHolder);

        Holder<List<?>> unboundedListHolder = new Holder<List<?>>(unbounded);
        doSomethingWithIt(unboundedListHolder);

        Holder<List<?>> superListHolder = new Holder<List<?>>(superBound);
        doSomethingWithIt(superListHolder);

    }

    static void doSomethingWithIt(Holder<List<?>> holder){
        System.out.println(holder.getT().getClass());
        System.out.println(holder.getT().get(0).getClass());
    };
}

class Holder<T>{
    T t;

    Holder(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
