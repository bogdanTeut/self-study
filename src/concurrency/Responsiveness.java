package concurrency;

import java.io.IOException;

/**
 * Created by bogdan.teut on 01/10/2014.
 */
class UnresponsiveUI{
    double d;

    void doStuff() throws IOException {
        while (d >= 0) d+=1;
        System.in.read();
    }
}

class ResponsiveUi extends Thread{
    double d;

    ResponsiveUi() {
        setDaemon(true);
        start();    
    }

    public void run(){
        while (d >= 0) d+=1;
    }
    
}
public class Responsiveness {

    public static void main(String[] args) throws IOException {
//        UnresponsiveUI unresponsiveUI = new UnresponsiveUI();
        ResponsiveUi responsiveUi = new ResponsiveUi();
//        unresponsiveUI.doStuff();
        System.in.read();
//        System.out.println(unresponsiveUI.d);
        System.out.println(responsiveUi.d);
    }
}
