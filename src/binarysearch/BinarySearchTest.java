package binarysearch;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by bogdan.teut on 24/11/2014.
 */
public class BinarySearchTest {

    @Test
    public void whenArrayHasOneElementItReturnsZero(){
        assertEquals(0, indexOf(1, new int[]{1}));
    }

    @Test
    public void whenKeyIsNotPResentItReturnsMinusOne(){
        assertEquals(-1, indexOf(0, new int[]{1}));
    }

    @Test
    public void whenKeyIsTheMidpoint(){
        assertEquals(1, indexOf(2, new int[]{1, 2, 3}));
    }

    @Test
    public void whenKeyIsMidpointUpper(){
        assertEquals(2, indexOf(3, new int[]{1, 2, 3}));
    }

    @Test
    public void whenKeyIsMidpointLower(){
        assertEquals(0, indexOf(1, new int[]{1, 2, 3}));
    }

    private int indexOf(int key, int[] array) {
        return new BinarySearch().getIndexOf(key, array);
    }
}
