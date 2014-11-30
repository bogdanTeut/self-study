package quicksort;

/**
 * Created by bogdan.teut on 25/11/2014.
 */
public class QuickSort {
    public static int[] sort(int[] array, int left, int right) {
        int pivot = array[right];
        int posIndex = 0;
        for (int i=0;i<array.length-1;i++){
            if (array[i] < pivot){
                swap(array, i, posIndex++);
            }
        }

        swap(array, posIndex, array.length-1);
        posIndex++;

        return array;
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
