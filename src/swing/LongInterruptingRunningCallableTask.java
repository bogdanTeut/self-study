package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by bogdan on 20/11/14.
 */
public class LongInterruptingRunningCallableTask extends JFrame {
    private JButton buttonOne = new JButton("Start long running task"),
    buttonTwo = new JButton("End Long Running Task"),
    buttonThree = new JButton("Get results");
    private TaskManager taskManager = new TaskManager();

    public LongInterruptingRunningCallableTask() throws HeadlessException {
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CallableTask callableTask = new CallableTask();
                taskManager.add(callableTask);
                System.out.println("Adding callable task "+callableTask);
            }
        });

        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : taskManager.purge()) {
                    System.out.println(s);
                }
            }
        });

        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String s : taskManager.getResults()) {
                    System.out.println(s);
                }
            }
        });

        setLayout(new FlowLayout());
        add(buttonOne);
        add(buttonTwo);
        add(buttonThree);
    }

    public static void main(String[] args) {
        SwingConsole.run(new LongInterruptingRunningCallableTask(), 200, 150);
    }
}
