package permutations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bogdan.teut on 24/11/2014.
 */
public class StringPermutation {
    public static String[] permute(String string) {
        if (string == null || string.trim().length()==0) return null;
        if (string.length() == 1) return new String[]{string};

        if (string.length() == 2){
            return permuteTwoLetersString(string);
        }

        String last = string.substring(string.length()-1);
        return insertIntoPermutations(permute(string.substring(0, string.length()-1)), last);

    }

    private static String[] insertIntoPermutations(String[] substringArray, String string) {
        String[] result = new String[substringArray.length*(substringArray[0].length()+1)];
        int counter = 0;
        for (String element : substringArray) {
            for (int i=0;i<=element.length();i++){
                result[counter++] = insertStringAtIndex(element, i, string);
            }
        }

        return result;
    }

    private static String insertStringAtIndex(String element, int index, String string) {
        if (index == 0) return string+element;
        if (index == element.length()) return element+string;
        return element.substring(0,index)+string+element.substring(index, element.length());
    }

    private static String[] permuteTwoLetersString(String string) {
        String one = string;
        String two = string.substring(1, 2) + string.substring(0, 1);

        return new String[]{one, two};
    }
}
