package concurrency.waxing;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by bogdan.teut on 18/11/2014.
 */
class CarActiveObject{
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Random random = new Random();
    private String name;

    public CarActiveObject(String name) {
        this.name = name;
    }

    public List<Future<String>> getMessages() {
        return messages;
    }

    private List<Future<String>> messages = new CopyOnWriteArrayList<Future<String>>();

    public void waxOn(){
        executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(name + " Starting Waxing On");
                pause(1000);
                System.out.println(name + " Waxing On Done");
                return null;
            };
        });

    }

    public void waxOff(){
        executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(name + " Starting Waxing Off");
                pause(1000);
                System.out.println(name + " Waxing Off Done");
                return null;
            };
        });

    }

    private void pause(int i) {
        try {
            TimeUnit.MILLISECONDS.sleep(500+random.nextInt(i));
        } catch (InterruptedException e) {
            System.out.println("Pause interrupted");
        }
    }

    public void shutDown() {
        executorService.shutdown();
    }

}

public class WaxOMaticActivObjects {
    public static void main(String[] args) {
        CarActiveObject bmw = new CarActiveObject("Bmw");
        bmw.waxOn();
        bmw.waxOff();
        bmw.waxOn();
        bmw.waxOff();
        bmw.waxOn();
        bmw.waxOff();


//        CarActiveObject mercedes = new CarActiveObject("Mercedes");
//        mercedes.waxOn();
//        mercedes.waxOff();
//        mercedes.waxOn();
//        mercedes.waxOff();
//        mercedes.waxOn();
//        mercedes.waxOff();
//
//        final List<Future<String>> messages = mercedes.getMessages();
//        while (messages.size() > 0){
//            for (int i = 0; i < messages.size(); i++) {
//                Future<String> stringFuture = messages.get(i);
//                if (stringFuture.isDone()){
//                    try {
//                        System.out.println(stringFuture.get());
//                    } catch (InterruptedException e) {
//                        System.out.println("Get interrupted");
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                    messages.remove(stringFuture);
//                }
//            }
//        }

        final List<Future<String>> bmwMessages = bmw.getMessages();
        while (bmwMessages.size() > 0){
            for (int i = 0; i < bmwMessages.size(); i++) {
                Future<String> stringFuture = bmwMessages.get(i);
                if (stringFuture.isDone()){
                    try {
                        System.out.println(stringFuture.get());
                    } catch (InterruptedException e) {
                        System.out.println("Get interrupted");
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    bmwMessages.remove(stringFuture);
                }
            }
        }

//        mercedes.shutDown();
        bmw.shutDown();

    }

}
