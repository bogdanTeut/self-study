package bublesort;

/**
 * Created by bogdan.teut on 24/11/2014.
 */
public class BubbleSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) return null;
        boolean reverse = true;
        while (reverse)
            for (int i=0;i<array.length-1;i++){
                reverse = false;
                if (array[i] > array[i+1]){
                    int temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    reverse = true;
                }
            }
        return array;
    }
}
