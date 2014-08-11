package rtti;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import rtti.AnotherSortedSet;

import java.util.*;

import static org.testng.Assert.*;

/**
 * Created by bogdan on 10/08/14.
 */
public class TestAnotherSortedSet {

    private AnotherSortedSet sortedSet;

    @BeforeTest
    public void refresh(){
        sortedSet = new AnotherSortedSet();
    }

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
        sortedSet =  new AnotherSortedSet();
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
    public void orderedInsertion(){
        sortedSet.add(2);
        sortedSet.add(11);
        sortedSet.add(3);
        sortedSet.add(9);
        sortedSet.add(6);
        sortedSet.add(4);
        sortedSet.add(5);
        sortedSet.add(12);
        sortedSet.add(8);
        sortedSet.add(7);
        sortedSet.add(1);
        sortedSet.add(10);

        Iterator<Integer> iterator = sortedSet.iterator();
        assertEquals(1, iterator.next().intValue());
        assertEquals(2, iterator.next().intValue());
        assertEquals(3, iterator.next().intValue());
        assertEquals(4, iterator.next().intValue());
        assertEquals(5, iterator.next().intValue());
        assertEquals(6, iterator.next().intValue());
        assertEquals(7, iterator.next().intValue());
        assertEquals(8, iterator.next().intValue());
        assertEquals(9, iterator.next().intValue());
        assertEquals(10, iterator.next().intValue());
        assertEquals(11, iterator.next().intValue());
        assertEquals(12, iterator.next().intValue());
    }

    @Test
    public void first() {
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(1);
        Integer first = sortedSet.first();
        assertEquals(1, first.intValue());
    }

    @Test
    public void last() {
        sortedSet =  new AnotherSortedSet();
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(1);
        Integer last = sortedSet.last();
        assertEquals(3, last.intValue());
    }

    @Test
    public void containsAll() {
        sortedSet.add(1);
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(4);
        sortedSet.add(5);

        AnotherSortedSet contained = new AnotherSortedSet();
        contained.add(2);
        contained.add(3);

        assertTrue(sortedSet.containsAll(contained));
    }

    @Test
    public void subSet() {
        sortedSet =  new AnotherSortedSet();
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(12);
        sortedSet.add(6);
        sortedSet.add(0);

        AnotherSortedSet result = sortedSet.subSet(2, 12);

        AnotherSortedSet subSet = new AnotherSortedSet();
        subSet.add(2);
        subSet.add(3);
        subSet.add(6);
        assertTrue(subSet.equals(result));
    }

    @Test
    public void tailSet() {
        sortedSet =  new AnotherSortedSet();
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(12);
        sortedSet.add(6);
        sortedSet.add(0);

        AnotherSortedSet result = sortedSet.tailSet(6);

        AnotherSortedSet tailSet = new AnotherSortedSet();
        tailSet.add(6);
        tailSet.add(12);
        assertTrue(tailSet.equals(result));
    }

    @Test
    public void headSet() {
        sortedSet =  new AnotherSortedSet();
        sortedSet.add(2);
        sortedSet.add(3);
        sortedSet.add(12);
        sortedSet.add(6);
        sortedSet.add(0);

        AnotherSortedSet result = sortedSet.headSet(6);

        AnotherSortedSet headSet = new AnotherSortedSet();
        headSet.add(0);
        headSet.add(2);
        headSet.add(3);
        assertTrue(headSet.equals(result));
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
