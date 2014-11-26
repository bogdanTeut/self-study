package mergesort;

/**
 * Created by bogdan.teut on 26/11/2014.
 */
public class MergeSort {
    public int[] sort(int[] array) {
        if (array.length < 2) return null;
        int middle = array.length/2;

        int[] left = new int[middle];
        System.arraycopy(array, 0, left, 0, middle);
        int[] right = new int[middle];
        System.arraycopy(array, middle, right, 0, middle);

        sort(left);
        sort(right);
        merge(left, right, array);
        return array;
    }

    public void merge(int[] left, int[] right, int[] array) {
        int i=0,j=0,k=0;
        while (i < left.length && j < right.length){
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            }else{
                array[k++] = right[j++];
            }
        }
        while (i < left.length){
            array[k++] = left[i++];
        }
        while (j < right.length){
            array[k++] = right[j++];
        }

    }
}
