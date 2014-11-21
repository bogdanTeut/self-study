package swing;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdan.teut on 19/11/2014.
 */
public class SubmitSwingProgram extends JFrame {

    JLabel label;

    public SubmitSwingProgram() {
        label = new JLabel("A label");
        add(label);
    }

    static SubmitSwingProgram submitSwingProgram;
    public static void main(String[] args) throws InterruptedException {

        submitSwingProgram = new SubmitSwingProgram();

        SwingConsole.run(submitSwingProgram, 300, 100);

        TimeUnit.SECONDS.sleep(1);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                submitSwingProgram.label.setText("Hey this is different");
            }
        });
    }
}
