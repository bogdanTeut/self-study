package binarysearch;

/**
 * Created by bogdan.teut on 24/11/2014.
 */
public class BinarySearch {
    public int getIndexOf(int key, int[] array) {
        return indexOf(key, array, 0, array.length-1);
    }

    private int indexOf(int key, int[] array, int min, int max) {
        if (max < min) return -1;
        int midpoint = (min + max)/2;
        if (array[midpoint] == key){
            return midpoint;
        }
        if (array[midpoint] < key){
            return indexOf(key, array, midpoint+1, max);
        }else {
            return indexOf(key, array, min, midpoint-1);
        }
    }
}
