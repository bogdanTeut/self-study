package rtti;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bogdan.teut on 11/08/2014.
 */

class KeyTest{

    private int id;

    KeyTest(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "KeyTest" + id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((KeyTest) obj).id == this.id;
    }


    @Override
    public int hashCode() {
        return id;
    }
}

public class MapTest {

    static void fill(Map<KeyTest, Integer> map, int size){
        for (int i=0 ; i<size; i++){
            map.put(new KeyTest(i), i);
        }
    }

    static int indexFor(int h, int length) {
        // assert Integer.bitCount(length) == 1 : "length must be a non-zero power of 2";
        return h & (length-1);
    }

    public static void main(String[] args) {
        Map<KeyTest, Integer> map = new HashMap<KeyTest, Integer>();
        fill(map, 10);
        System.out.println(map);
        System.out.println(map.get(new KeyTest(3)));
        map.put(new KeyTest(3), 10);
        System.out.println(map);

        System.out.println(indexFor(1, 4));
        System.out.println(indexFor(2, 4));
        System.out.println(indexFor(3, 4));
        System.out.println(indexFor(4, 4));
        System.out.println(indexFor(5, 4));
        System.out.println(indexFor(6, 4));
        System.out.println(indexFor(7, 4));
    }

}
