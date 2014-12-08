package removeduplicates;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by bogdan.teut on 08/12/2014.
 */
public class RemoveDuplicateCharactersTest {
    @Test
    public void removeDuplicatesOneForTwoSameChars() {
        Assert.assertEquals("st", new RemoveDuplicateCharacters().removeDuplicates("st".toCharArray()));
    }

    @Test
    public void removeDuplicatesOneForThreeDifferentChars() {
        Assert.assertEquals("st", new RemoveDuplicateCharacters().removeDuplicates("sts".toCharArray()));
    }

}
