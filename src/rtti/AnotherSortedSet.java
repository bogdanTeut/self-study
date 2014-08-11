package rtti;

import java.util.*;

/**
 * Created by bogdan on 10/08/14.
 */
public class AnotherSortedSet {

    private List<Integer> internalLinkedList = new LinkedList<Integer>();
    private Comparator<Integer> comparator;

    public AnotherSortedSet(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    public AnotherSortedSet(List<Integer> internalLinkedList) {
        this.internalLinkedList = internalLinkedList;
    }

    public AnotherSortedSet() {
    }

    public Iterator<Integer> iterator() {
        return internalLinkedList.iterator();
    }

    public void add(Integer elementToBeAdded) {
        if (internalLinkedList.contains(elementToBeAdded)){
            return;
        }

        if (internalLinkedList.size() == 0){
            internalLinkedList.add(elementToBeAdded);
            return;
        }
        boolean elementAdded = false;
        for (int i=0;i<internalLinkedList.size();i++){
           Integer currentElement = internalLinkedList.get(i);
            boolean checkRightBound = i == internalLinkedList.size() - 1 ? true : elementToBeAdded.compareTo(internalLinkedList.get(i + 1)) < 0;
            boolean checkLeftBound = elementToBeAdded.compareTo(currentElement) > 0;
            if (checkLeftBound && checkRightBound){
                internalLinkedList.add(i+1, elementToBeAdded);
                elementAdded =  true;
                break;
           }
        }
        if (!elementAdded){
            internalLinkedList.add(0, elementToBeAdded);
        }

    }

    public Comparator<Integer> comparator() {
        return comparator;
    }

    public Integer first() {
        return internalLinkedList.get(0);
    }

    public Integer last() {
        if (internalLinkedList.size() == 0) return null;
        return internalLinkedList.get(internalLinkedList.size()-1);
    }

    public AnotherSortedSet subSet(Integer from, Integer to) {
        List<Integer> subList = internalLinkedList.subList(internalLinkedList.indexOf(from), internalLinkedList.indexOf(to));
        return new AnotherSortedSet(subList);
    }

    public boolean containsAll(AnotherSortedSet contained){
        for (Iterator<Integer> it = contained.iterator();it.hasNext();){
            if (!internalLinkedList.contains(it.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        List other = ((AnotherSortedSet)obj).internalLinkedList;
        return internalLinkedList.containsAll(other) && other.containsAll(internalLinkedList);
    }

    public AnotherSortedSet tailSet(int from) {
        List<Integer> subList = internalLinkedList.subList(internalLinkedList.indexOf(from), internalLinkedList.size() - 1);
        subList.add(internalLinkedList.get(internalLinkedList.size()-1));
        return new AnotherSortedSet(subList);
    }

    public AnotherSortedSet headSet(int to) {
        List<Integer> subList = internalLinkedList.subList(0, internalLinkedList.indexOf(to));
        return new AnotherSortedSet(subList);
    }
}









