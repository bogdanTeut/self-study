package rtti.individuals;

import rtti.Creator;

import java.util.Iterator;

/**
 * Created by bogdan.teut on 01/08/2014.
 */
public class FibonacciCreator implements Creator<Integer>{

    private int size;

    public FibonacciCreator(int size) {
        this.size = size;
    }

    private int fibo(int step){
        if (step < 2) return 1;
        return fibo(step-1)+fibo(step-2);
    }

    @Override
    public Integer create() {
        return null;
    }

    public Iterator<Integer> iterator(){
        return new Iterator<Integer>(){
            int count;

            @Override
            public boolean hasNext() {
                return (count <= size)?true:false;
            }

            @Override
            public Integer next() {
                return FibonacciCreator.this.fibo(count++);

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
