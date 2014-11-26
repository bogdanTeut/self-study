package mergesort;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by bogdan.teut on 26/11/2014.
 */
public class MergeSortTest {

    @Test
    public void whenSortedArray(){
        assertEquals(new int[]{0, 1}, sort(new int[]{0, 1}));
    }

    @Test
    public void whenUnSortedArrayOfTwoElements(){
        assertEquals(new int[]{0, 1}, sort(new int[]{1, 0}));
    }

    @Test
    public void whenUnSortedArrayOfFourElements(){
        assertEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, sort(new int[]{2, 4, 1, 6, 8, 5, 3, 7}));
    }

    private int[] sort(int[] array) {
        return new MergeSort().sort(array);
    }

    @Test
    public void whenTwoElementsArrayAreMerged(){
        int[] array = {0, 0, 0, 0};
        new MergeSort().merge(new int[]{0, 4}, new int[]{2, 3}, array);
        assertEquals(new int[]{0, 2, 3, 4}, array);
    }

    @Test
    public void whenFourElementsArrayAreMerged(){
        int[] array = {0, 0, 0, 0, 0, 0, 0, 0};
        new MergeSort().merge(new int[]{1, 2, 4, 6}, new int[]{3, 5, 7, 8}, array);
        assertEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }
}
