package quicksort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by bogdan.teut on 25/11/2014.
 */
public class QuickSortTest {

    @Test
    public void whenSortedArray(){
        Assert.assertEquals(new int[]{0, 1}, sort(new int[]{0, 1}));
    }

    @Test
    public void whenUnsortedArray(){
        Assert.assertEquals(new int[]{0, 1}, sort(new int[]{1, 0}));
    }

    @Test
    public void whenPartiallyUnsortedArrayAndPartitioningIsEnough(){
        Assert.assertEquals(new int[]{0, 1, 2}, sort(new int[]{0, 2, 1}));
    }

    @Test
    public void whenReapplyingAlgorithmIsNecessaryLowerIndex(){
        Assert.assertEquals(new int[]{0, 1, 2}, sort(new int[]{1, 0, 2}));
    }

    @Test
    public void whenReapplyingAlgorithmIsNecessaryUpperIndex(){
        Assert.assertEquals(new int[]{0, 1, 2}, sort(new int[]{1, 2, 0}));
    }

    @Test
    public void whenReapplyingAlgorithmIsNecessaryBothSides(){
        Assert.assertEquals(new int[]{ 1, 2, 3, 4, 5, 5, 7 ,8 ,9}, sort(new int[]{3, 8, 7, 4, 5, 2, 1, 9, 5}));
    }

    private int[] sort(int[] array) {
        return QuickSort.sort(array, 0, array.length-1);
    }


}
