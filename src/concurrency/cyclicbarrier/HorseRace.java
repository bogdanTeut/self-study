package concurrency.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 22/10/2014.
 */
public class HorseRace{
    private static final int FINISH = 75;
    private CyclicBarrier cyclicBarrier;
    private int numberOfHorses;
    private Horse[] horses;
    ExecutorService executorService;

    HorseRace(final int numberOfHorses, final ExecutorService executorService){
        this.numberOfHorses = numberOfHorses;
        horses = new Horse[numberOfHorses];

        cyclicBarrier = new CyclicBarrier(numberOfHorses, new Runnable() {

            @Override
            public void run() {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=0;i<FINISH;i++){
                    stringBuilder.append("=");
                };
                System.out.println(stringBuilder.toString());

                for (Horse horse:horses){
                    System.out.println(horse.printStrides());
                }

                for (Horse horse:horses){
                    if (horse.getStrides()>=FINISH){
                        System.out.println(horse+ " won");
                        executorService.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(400);
                } catch(InterruptedException e) {
                    System.out.println("barrier-action sleep interrupted");
                }
            }
        });

        for(int i=0;i<numberOfHorses;i++){
            horses[i] = new Horse(i, cyclicBarrier);
            executorService.execute(horses[i]);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        new HorseRace(10, executorService);
    }
}
