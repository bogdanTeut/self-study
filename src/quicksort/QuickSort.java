package quicksort;

/**
 * Created by bogdan.teut on 25/11/2014.
 */
public class QuickSort {
    public static int[] sort(int[] array, int left, int right) {
        if (left > right) return null;
        int index = partition(array, left, right);
        sort(array, left, index - 1);
        sort(array, index + 1, right);
        return array;
    }

    private static int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int posIndex = left;
        for (int i=left;i<right;i++){
            if (array[i] < pivot){
                swap(array, i, posIndex++);
            }
        }

        swap(array, posIndex, right);
//        posIndex++;

        return posIndex;
    }

    private static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
