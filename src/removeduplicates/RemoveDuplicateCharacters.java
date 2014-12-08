package removeduplicates;

/**
 * Created by bogdan.teut on 08/12/2014.
 */
public class RemoveDuplicateCharacters {
    public String removeDuplicates(char[] word) {

        int n=1;
        int len = word.length;
        for (int i=1; i<len; i++){
            int j;
            for (j=0;j<n;j++){
                if (word[i] == word[j]) break;
            }
            if (j == n){
                word[n] = word[i];
                n++;
            }
        }
        word[n] = 0;
        return new String(word).trim();
    }
}
