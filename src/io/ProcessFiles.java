package io;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by bogdan.teut on 22/08/2014.
 */

interface Strategy{
    void process(File file) throws IOException;
}

public class ProcessFiles {
    private Strategy strategy;
    private String ext;

    public ProcessFiles(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start (String[] args) throws IOException {
        File file = new File(args[0]);
        if (file.isDirectory()){
            for (File currentFile:Directory.walk(file, ".*\\."+ext)){
                strategy.process(currentFile);        
            }    
        }else{
            strategy.process(file);
        } 
    }

    public static void main(String[] args) throws IOException {
//        ProcessFiles processFiles =  new ProcessFiles(new Strategy() {
//            @Override
//            public void process(File file) throws IOException {
//                System.out.println(file.getCanonicalPath());
//            }
//        }, "java");
//        processFiles.start(args);

        ProcessFiles processFiles =  new ProcessFiles(new Strategy() {
            @Override
            public void process(File file) throws IOException {
                Calendar yesterday = new GregorianCalendar();
                yesterday.add(Calendar.DAY_OF_MONTH, -1);
                Date lastModfied = new Date(file.lastModified());
                if (lastModfied.after(yesterday.getTime())){
                    System.out.println(file.getCanonicalPath());
                }
            }
        }, "java");
        processFiles.start(args);
    }
}
