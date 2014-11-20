package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 20/11/14.
 */
public class LongInterruptingRunningTask extends JFrame {
    private JButton buttonOne = new JButton("Start long running task");
    private JButton buttonTwo = new JButton("Stop long running task");
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LongInterruptingRunningTask() throws HeadlessException {
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                TimeUnit.SECONDS.sleep(3);
                            } catch (InterruptedException e1) {
                                System.out.println("Sleep interrupted");
                            }
                        }
                    });
            }
        });

        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executorService.shutdownNow();
            }
        });
        setLayout(new FlowLayout());
        add(buttonOne);
        add(buttonTwo);
    }

    public static void main(String[] args) {
        SwingConsole.run(new LongInterruptingRunningTask(), 200, 150);
    }
}
