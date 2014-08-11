package rtti.individuals;

import rtti.CounterUsingClassPolymorphically;
import rtti.Creator;
import rtti.ForNameCreator;
import rtti.LiteralsCreator;

import java.util.*;

/**
 * Created by bogdan.teut on 01/08/2014.
 */
public class CounterWorker {
    public static void main(String[] args) {
        CounterUsingClassPolymorphically<Pet> petCounter = new CounterUsingClassPolymorphically<Pet>();
        CounterUsingClassPolymorphically<Pet> petCounterTwo = new CounterUsingClassPolymorphically<Pet>();
        CounterUsingClassPolymorphically<Integer> intCounter = new CounterUsingClassPolymorphically<Integer>();
        LiteralsCreator literalsPetCreator = new LiteralsCreator(30);
        ForNameCreator forNamePetCreator = new ForNameCreator(30);
        FibonacciCreator fibonacciCreator = new FibonacciCreator(10);
        count(petCounter, literalsPetCreator);
        count(petCounterTwo, forNamePetCreator);
        count(intCounter, fibonacciCreator);

        System.out.println(petCounter);
        System.out.println(petCounterTwo);
        System.out.println(intCounter);



    }

    private static <E>void count(CounterUsingClassPolymorphically<E> petCounter, Creator<E> literalsPetCreator) {
        for(Iterator<E> it = literalsPetCreator.iterator();it.hasNext();){
            petCounter.count(it.next().getClass());
        }
    }
}
