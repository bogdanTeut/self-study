package tree;

import java.util.Comparator;

/**
 * Created by bogdan on 02/12/14.
 */
public class BinarySearchNode<T extends Comparable> implements Comparable<T> {
    T value;
    BinarySearchNode<T> left;
    BinarySearchNode<T> right;


    public BinarySearchNode(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(T o) {
        return this.compareTo(o);
    }
}

