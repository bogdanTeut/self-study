package io.greenhouse;

import java.io.*;

/**
 * Created by bogdan.teut on 08/09/2014.
 */
public class Blips implements Externalizable{
    
    private int i;
    private String s;

    public Blips() {
        System.out.println("Default constructor");
    }

    public Blips(int i, String s) {
        this.i = i;
        this.s = s;
    }

    @Override
    public String toString() {
        return s+i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("write external");
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("read external");
        s = (String)in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serialize_blip"));
        Blips blips = new Blips(5, "blips");
        objectOutputStream.writeObject(blips);
        objectOutputStream.close();
        
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serialize_blip"));
        System.out.println(objectInputStream.readObject());
    }
}
