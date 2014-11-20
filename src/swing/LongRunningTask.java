package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan on 20/11/14.
 */
public class LongRunningTask extends JFrame {
    private JButton buttonOne = new JButton("Start long running task");
    private JButton buttonTwo = new JButton("Stop long running task");

    public LongRunningTask() throws HeadlessException {
        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    System.out.println("Sleep interrupted");
                }
            }
        });

        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.currentThread().interrupt();
            }
        });
        setLayout(new FlowLayout());
        add(buttonOne);
        add(buttonTwo);
    }

    public static void main(String[] args) {
        SwingConsole.run(new LongRunningTask(), 200, 150);
    }
}
