package rtti.tester;

/**
 * Created by bogdan.teut on 19/08/2014.
 */
public abstract class Test<C> {
    String name;

    public void setName(String name) {
        this.name = name;
    }
    
    public abstract int test(C Collection, TestParam testParam);
}
