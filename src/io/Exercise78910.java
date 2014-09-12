package io;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by bogdan.teut on 26/08/2014.
 */
public class Exercise78910 extends LinkedList<String>{

    public Exercise78910(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(
                new FileReader("src//rtti//io//"+fileName));
        String line;
        while ((line = br.readLine())!=null){
            this.add(line.toUpperCase());            
        }
    }

    public static void main(String[] args) throws IOException {
        printInReverseOrder(args);
        new DataInputStream(new FileInputStream(""));
    }

    private static void printInReverseOrder(String[] args) throws IOException {
        if (args.length == 0) throw new RuntimeException();
        Exercise78910 exercise78910 = new Exercise78910(args[0]);
        ListIterator<String> stringListIterator = exercise78910.listIterator(exercise78910.size());
        while (stringListIterator.hasPrevious()){
            System.out.println(stringListIterator.previous());                
        }
    }
}
