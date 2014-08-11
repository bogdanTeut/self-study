package rtti.individuals;

import java.util.*;

/**
 * Created by bogdan.teut on 07/08/2014.
 */

interface Changeable<T>{
    public T changeIt(T t);
}

class HoldValueA implements Changeable<Integer>{
    private Integer t;

    public Integer changeIt(Integer t){
        t = t*2;
        return t;
    }

}

class HoldValueB implements Changeable<Integer>{
    private Integer t;

    public Integer changeIt(Integer t){
        t = t/2;
        return t;
    }
}

public class Functional {

    static <T>void modifyAll(List<T> genericList, Changeable<T> hoToChangeIt){
        for(T t:genericList){
            System.out.println(hoToChangeIt.changeIt(t));
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        modifyAll(integers, new HoldValueA());
        modifyAll(integers, new HoldValueB());
    }
}
