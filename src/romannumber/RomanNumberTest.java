package romannumber;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by bogdan.teut on 26/11/2014.
 */
public class RomanNumberTest {

    @DataProvider(name = "translator")
    public Object[][] translator(){
        return new Object[][]{
            {"I", 1, "one to I"},
            {"II", 2, "two to II"},
            {"III", 3, "three to III"},
            {"IV", 4, "four to IV"},
            {"V", 5, "five to V"},
            {"VI", 6, "six to VI"},
            {"VII", 7, "seven to VII"},
            {"VIII", 8, "eight to VIII"},
            {"IX", 9, "nine to IX"},
            {"X", 10, "ten to X"},
            {"XI", 11, "eleven to XI"},
            {"XII", 12, "twelve to XII"},
            {"XIII", 13, "thirteen to XIII"},
            {"XIV", 14, "fourteen to XIV"},
            {"XV", 15, "fourteen to XV"},
            {"XVI", 16, "fourteen to XVI"},
            {"XVII", 17, "fourteen to XVII"},
            {"XVIII", 18, "fourteen to XVIII"},
        };
    }

    @Test(dataProvider = "translator")
    public void translateOne(String expectedResult, int arabicNumber, String message){
        Assert.assertEquals(expectedResult, translate(arabicNumber), message);
    }

    private String translate(int arabicNumber) {
        return new RomanNumber().translate(arabicNumber);
    }
}
