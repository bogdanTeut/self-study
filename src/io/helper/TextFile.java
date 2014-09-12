package io.helper;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by bogdan on 04/09/14.
 */
public class TextFile extends ArrayList<String>{

    public static String read(String fileName){
        StringBuffer sb =  new StringBuffer();

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src//rtti//io//"+fileName).getAbsoluteFile()));
            try {
                String s = "";
                while ((s = br.readLine())!=null){
                    sb.append(s);
                    sb.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void write(String fileName, String text){
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("src//rtti//io//"+fileName).getAbsoluteFile());
            pw.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            pw.close();
        }
    }
    
    public TextFile(String fileName, String splitter){
        super(Arrays.asList(read(fileName).split(splitter)));
        if (get(0) == "") remove(0); 
    }

    public static void main(String[] args) {
        String file = read("commands_file");
        write("commands_file_bis", file);
        Set<String> wordsSet = new TreeSet<String>(new TextFile("commands_file","\\W+"));
        System.out.println(wordsSet);
    }

}
