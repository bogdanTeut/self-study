package rtti;

import org.testng.Assert;
import org.testng.annotations.Test;
import rtti.AnotherSortedSet;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by bogdan on 10/08/14.
 */
public class TestAnotherSortedSet {

//    SortedSet sortedSetToTest = new SortedSet() {
//        @Override
//        public SortedSet subSet(Object fromElement, Object toElement) {
//            return null;
//        }
//
//        @Override
//        public SortedSet headSet(Object toElement) {
//            return null;
//        }
//
//        @Override
//        public SortedSet tailSet(Object fromElement) {
//            return null;
//        }
//
//        @Override
//        public Object first() {
//            return null;
//        }
//
//        @Override
//        public Object last() {
//            return null;
//        }
//
//        @Override
//        public int size() {
//            return 0;
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return false;
//        }
//
//        @Override
//        public boolean contains(Object o) {
//            return false;
//        }
//
//        @Override
//        public Iterator iterator() {
//            return null;
//        }
//
//        @Override
//        public Object[] toArray() {
//            return new Object[0];
//        }
//
//        @Override
//        public T[] toArray(Object[] a) {
//            return new T[0];
//        }
//
//        @Override
//        public boolean add(Object o) {
//            return false;
//        }
//
//        @Override
//        public boolean remove(Object o) {
//            return false;
//        }
//
//        @Override
//        public boolean containsAll(Collection c) {
//            return false;
//        }
//
//        @Override
//        public boolean addAll(Collection c) {
//            return false;
//        }
//
//        @Override
//        public boolean retainAll(Collection c) {
//            return false;
//        }
//
//        @Override
//        public boolean removeAll(Collection c) {
//            return false;
//        }
//
//        @Override
//        public void clear() {
//
//        }
//    };

    private AnotherSortedSet sortedSet = new AnotherSortedSet();

    @Test
    public void createASortedSet(){
        assertNotNull(sortedSet);
    }
    @Test
    public void iterator(){
        Iterator<Integer> iterator = sortedSet.iterator();
        assertNotNull(iterator);
    }

    @Test
    public void addAnElement(){
        Iterator<Integer> iterator = sortedSet.iterator();
        assertFalse(iterator.hasNext());
        sortedSet.add(1);
        iterator = sortedSet.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().intValue());
    }

    @Test
    public void noDuplicatedElement(){
        Iterator<Integer> iterator = sortedSet.iterator();
        assertFalse(iterator.hasNext());
        sortedSet.add(1);
        sortedSet.add(1);
        iterator = sortedSet.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());

    }

    @Test
    public void comparator(){
        Comparator<Integer> integerComparator = getComparator();

        sortedSet = new AnotherSortedSet(integerComparator);
        Comparator<Integer> comparator = sortedSet.comparator();
        assertNotNull(comparator);
        assertEquals(comparator, sortedSet.comparator());

    }

    @Test
    public void subSet(){
        sortedSet.subSet(2,7);
    }

    private Comparator<Integer> getComparator() {
        return new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return 0;
                }
            };
    }
}
