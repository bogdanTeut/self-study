package bublesort;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by bogdan.teut on 24/11/2014.
 */
public class BubbleSortTest {

    @Test
    public void whenSortedArray(){
        Assert.assertEquals(new int[]{0, 1}, BubbleSort.sort(new int[]{0, 1}));
    }

    @Test
    public void whenUnsortedTwoElementsArray(){
        Assert.assertEquals(new int[]{0, 1}, BubbleSort.sort(new int[]{1, 0}));
    }

    @Test
    public void whenPartialUnsortedThreeElementsArray(){
        Assert.assertEquals(new int[]{0, 1, 2}, BubbleSort.sort(new int[]{0, 2, 1}));
    }

    @Test
    public void whenReallyUnsortedThreeElementsArray(){
        Assert.assertEquals(new int[]{0, 1, 2}, BubbleSort.sort(new int[]{2, 1, 0}));
    }

    @Test
    public void whenArrayIsEmpty(){
        Assert.assertEquals(null, BubbleSort.sort(new int[]{}));
    }
}
