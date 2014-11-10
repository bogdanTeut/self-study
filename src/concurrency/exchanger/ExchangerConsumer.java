package concurrency.exchanger;

import enums.vendingmachine.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by bogdan.teut on 05/11/2014.
 */
public class ExchangerConsumer<T> implements Runnable{

    private List<T> holder;
    private Exchanger<List<T>> exchanger;
    private volatile T value;

    public ExchangerConsumer(List<T> holder, Exchanger<List<T>> exchanger) {
        this.holder = holder;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                holder = exchanger.exchange(holder);
                for (int i = 0; i < holder.size(); i++) {
                    value =  holder.get(i);
                    holder.remove(value);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("ExchangerConsumer interrupted");
        }
        System.out.println("Last value: "+value);
    }
}
