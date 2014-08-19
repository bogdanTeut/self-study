package rtti.tester;

import java.util.*;

/**
 * Created by bogdan.teut on 19/08/2014.
 */
public class TestsTemporary {
    class AddTest extends Test<List<Integer>>{

        @Override
        public int test(List<Integer> collection, TestParam testParam) {
            int loops = testParam.loops;
            int size = testParam.size;
            
            for (int i=0;i<loops;i++){
                collection.clear();
                for (int j=0;j<size;j++){
                    collection.add(j);                    
                }
            }
            return loops * size;
        }
    }
}
