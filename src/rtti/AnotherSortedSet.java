package rtti;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Created by bogdan on 10/08/14.
 */
public class AnotherSortedSet {

    private LinkedList<Integer> internalLinkedList = new LinkedList<Integer>();
    private Comparator<Integer> comparator;

    public AnotherSortedSet(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    public AnotherSortedSet() {
    }

    public Iterator<Integer> iterator() {
        return internalLinkedList.iterator();
    }

    public void add(int i) {
        if (!internalLinkedList.contains(i)){
            internalLinkedList.add(i);
        }
    }

    public Comparator<Integer> comparator() {
        return comparator;
    }
}









