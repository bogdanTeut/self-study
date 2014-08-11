package rtti;

import java.util.Random;
import java.util.RandomAccess;

/**
 * Created by bogdan on 27/07/14.
 */

class ClassToLoadOne{
    public static final int constantOne = 14;
    public static final int constantTwo = new Random(47).nextInt();

    static {
        System.out.println("Loading ClassToLoadOne");
    }

    public ClassToLoadOne(){
        System.out.println("ClassToLoadOne constructor");
    }
}

class ClassToLoadTwo{
    public static int constantOne = 14;
    static {
        System.out.println("Loading ClassToLoadTwo");

    }
}

class ClassToLoadThree{
    public static int constantOne = 14;
    static {
        System.out.println("Loading ClassToLoadThree");

    }
}


public class LoadingLinkingInitialising {
    public static void main(String[] args) {
        System.out.println("Starting the main");
        System.out.println(ClassToLoadOne.constantOne);
        System.out.println("After ClassToLoadOne.constantOne");
        System.out.println(ClassToLoadOne.constantTwo);
        System.out.println("After ClassToLoadOne.constantTwo");
        System.out.println(ClassToLoadTwo.constantOne);
        System.out.println("After ClassToLoadTwo.constantOne");
        try {
            Class.forName("rtti.ClassToLoadThree");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find the classs");
        }

    }


}
