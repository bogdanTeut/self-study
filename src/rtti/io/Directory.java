package rtti.io;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by bogdan.teut on 22/08/2014.
 */
public class Directory {
    public static void main(String[] args) throws IOException {
        TreeInfo treeInfo = walk (new File("src/rtti"), args[0]);
        System.out.println(treeInfo);
    }

    private static void print(TreeInfo treeInfo) throws IOException {
        for (File file:treeInfo){
            System.out.println(file.getCanonicalPath());    
        }    
    }

    public static TreeInfo walk(File directory, String regex) {
        TreeInfo treeInfo = new TreeInfo();
        
        File[] files = directory.listFiles();
        for (File file:files){
            if (file.isDirectory()){
                treeInfo.directories.add(file);
                treeInfo.addAll(walk(file, regex));
            }else{
                if (file.getName().matches(regex))
                treeInfo.files.add(file);                
            }             
        };
        return treeInfo;
    }
}

class TreeInfo implements Iterable<File>{
    public List<File> directories = new ArrayList<File>();                     
    public List<File> files = new ArrayList<File>();


    public void addAll(TreeInfo other) {
        directories.addAll(other.directories);
        files.addAll(other.files);
    }

    @Override
    public Iterator<File> iterator() {
        return files.iterator();
    }

    @Override
    public String toString() {
        return "directories: "+directories +
                "\n" +
                "files: "+files;
    }
}
