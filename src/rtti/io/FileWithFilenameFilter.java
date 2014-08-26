package rtti.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bogdan.teut on 21/08/2014.
 */
public class FileWithFilenameFilter {
    
    private static class FilterName implements FilenameFilter{

        Pattern pattern;

        private FilterName(String regex) {
            pattern = Pattern.compile(regex);
        }

        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

    public static void main(String[] args) {
        File file = new File("src/rtti");
        for (String filename:file.list(new FilterName(args[0]))){
            System.out.println(filename);            
        }
    }
    
}
