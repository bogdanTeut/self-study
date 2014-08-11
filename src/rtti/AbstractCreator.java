package rtti;

import rtti.individuals.Pet;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by bogdan on 27/07/14.
 */
public abstract class AbstractCreator<T> implements Creator<T>{

    private int size;
    private Random random = new Random();
    private Class<T> type;

    protected abstract List<Class<? extends T>> types();

    protected AbstractCreator(int size, Class<T> type) {
        this.size = size;
        this.type = type;
    }

    @Override
    public T create(){
        T result = null;
        try {
            result = types().get(random.nextInt(types().size())).newInstance();
        } catch (InstantiationException e) {
            System.out.println("Couldn't find the class");
        } catch (IllegalAccessException e) {
            System.out.println("Probably the constructor was private");
        }
        return result;
    }

    class PetIterator implements Iterator<T>{

        int count = size;

        @Override
        public boolean hasNext() {
            if (count >0 ) return true;
            else return false;
        }

        @Override
        public T next() {
           count--;
           return create();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<T> iterator(){
        return new PetIterator();
    }

    public T[] createArray(int size){
        T[] result = (T[])Array.newInstance(type, size);

        for (int i=0;i<Array.getLength(result);i++){
            Array.set(result, i,create());
        }
        return result;
    }

    public ArrayList<T> arrayList(int size){
        return new ArrayList<T>(Arrays.asList(createArray(size)));
    }


}
