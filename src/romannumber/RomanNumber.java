package romannumber;

/**
 * Created by bogdan.teut on 26/11/2014.
 */
public class RomanNumber {
    public String translate(int arabicNumber) {

        if (arabicNumber <4){
            return addOneToThree(arabicNumber, "", 0);
        }

        if (arabicNumber == 4) return "IV";

        if (arabicNumber < 9){
            return addOneToThree(arabicNumber, "V", 5);
        }

        if (arabicNumber == 9){
            return "IX";
        }

        if (arabicNumber<14){
            return addOneToThree(arabicNumber, "X", 10);
        }

        if (arabicNumber == 14){
            return "XIV";
        }

        if (arabicNumber<19){
            return addOneToThree(arabicNumber, "XV", 15);
        }

        return null;
    }

    private String addOneToThree(int arabicNumber, String baseNumber, int difference) {
        String result = baseNumber;
        for (int i=0;i<arabicNumber-difference;i++){
            result += "I";
        }
        return result;
    }
}
