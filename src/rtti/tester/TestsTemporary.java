package rtti.tester;

import java.util.*;

/**
 * Created by bogdan.teut on 19/08/2014.
 */
public class TestsTemporary {
    
    static Random random = new Random();
    
     static class AddTest extends Test<List<Integer>>{

         AddTest(String name) {
            this.name = name;             
         }

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

    static class GetTest extends Test<List<Integer>>{

        GetTest(String name) {
            this.name = name;
        }

        @Override
        public int test(List<Integer> collection, TestParam testParam) {
            int loops = testParam.loops * testParam.size;
            int size = collection.size();

            for (int i=0;i<loops;i++){
                collection.get(random.nextInt(size-1));
            }
            return loops * size;
        }
    }

    static class SetTest extends Test<List<Integer>>{

        SetTest(String name) {
            this.name = name;
        }

        @Override
        public int test(List<Integer> collection, TestParam testParam) {
            int loops = testParam.loops * testParam.size;
            int size = collection.size();

            for (int i=0;i<loops;i++){
                collection.set(random.nextInt(size-1), 47);
            }
            return loops * size;
        }
    }
    
    public static void performOneTest(Test addTest, List<Integer> list, TestParam testParam){
        System.out.println(addTest.name);
        long startTime = System.nanoTime();
        int reps = addTest.test(list, testParam);
        long timeToPerform = System.nanoTime()-startTime;
        System.out.println(timeToPerform/reps);                            
    }

    public static void main(String[] args) {
        performOneTest(new AddTest("Add"), new ArrayList<Integer>(), new TestParam(10, 10));    
        performOneTest(new GetTest("Get"), new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,0)), new TestParam(10, 10));    
        performOneTest(new SetTest("Set"), new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,0)), new TestParam(10, 10));    
    }
}
