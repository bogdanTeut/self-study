package rtti.tester;

/**
 * Created by bogdan.teut on 19/08/2014.
 */
public class TestParam {    
    public final int size;
    public final int loops;

    public TestParam(int size, int loops) {
        this.size = size;
        this.loops = loops;
    }
    
    public TestParam[] array(int... args){
        int size = args.length / 2;
        int n = 0;
        TestParam[] result = new TestParam[size];
        for (int i=0;i<size;i++ ){
            result[i] = new TestParam(args[n++], args[n++]);   
        }
        return result;
    }
    
    public TestParam[] array(String[] args){
        int[] intValues = new int[args.length];
        for (int i=0;i<args.length;i++){
            intValues[i] = Integer.decode(args[i]); 
        }
        return array(intValues);
    }
}
