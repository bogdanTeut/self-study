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
    public void whenPartiallyUnsortedArrayAndReapplyingAlgorithmIsNecessary(){
        Assert.assertEquals(new int[]{0, 1, 2}, sort(new int[]{1, 0, 2}));
    }

    private int[] sort(int[] array) {
        return QuickSort.sort(array);
    }


}
