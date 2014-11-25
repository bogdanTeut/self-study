package permutations;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by bogdan.teut on 24/11/2014.
 */
public class StringPermutationTest {

    @Test
    public void whenOneLetterString(){
        assertEquals(new String[]{"a"}, StringPermutation.permute("a"));
    }

    @Test
    public void whenNullString(){
        assertEquals(null, StringPermutation.permute(null));
    }

    @Test
    public void whenEmptyString(){
        assertEquals(null, StringPermutation.permute(""));
    }

    @Test
    public void whenTwoLettersString(){
        assertEquals(new String[]{"ab", "ba"}, StringPermutation.permute("ab"));
    }

    @Test
    public void whenThreeLettersString(){
        assertEquals(new String[]{"cab", "acb", "abc", "cba", "bca", "bac"}, StringPermutation.permute("abc"));
    }

    @Test
    public void whenFourLettersString(){
        assertEquals(new String[]{"dcab", "cdab", "cadb", "cabd", "dacb", "adcb", "acdb", "acbd", "dabc", "adbc", "abdc", "abcd", "dcba", "cdba", "cbda", "cbad", "dbca", "bdca", "bcda", "bcad", "dbac", "bdac", "badc", "bacd"}, StringPermutation.permute("abcd"));
    }
}
