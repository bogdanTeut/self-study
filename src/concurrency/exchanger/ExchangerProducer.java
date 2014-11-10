package concurrency.exchanger;

import enums.vendingmachine.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by bogdan.teut on 05/11/2014.
 */
public class ExchangerProducer<T> implements Runnable{

    private List<T> holder;
    private Generator<T> generator;
    private Exchanger<List<T>> exchanger;

    public ExchangerProducer(List<T> holder, Generator<T> generator, Exchanger<List<T>> exchanger) {
        this.holder = holder;
        this.generator = generator;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                for (int i = 0; i <10 ; i++) {
                    holder.add(generator.next());
                }
                exchanger.exchange(holder);
            }
        } catch (InterruptedException e) {
            System.out.println("ExchangerProducer interrupted");
        }

    }
}
