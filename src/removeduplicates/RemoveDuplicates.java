package removeduplicates;

/**
 * Created by bogdan.teut on 08/12/2014.
 */
public class RemoveDuplicates {
    public static void removeDuplicates(char[] str){
        int tail = 1;
        int len = str.length;

        for (int i=1; i<len; ++i){
            int j;
            for (j=0;j<tail;++j){
                if (str[i] == str[j]) {
                    break;
                }
            }
            if (j == tail){
                str[tail] = str[i];
                ++tail;
            }
            System.out.println();
        }
        System.out.println(str);
    }

    public static void main(String[] args) {
        removeDuplicates("started".toCharArray());
    }
}
