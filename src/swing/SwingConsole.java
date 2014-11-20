package swing;

import javax.swing.*;

/**
 * Created by bogdan on 20/11/14.
 */
public class SwingConsole {
    public static void run(final JFrame jFrame, final int x, final int y){

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                jFrame.setSize(x ,y);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
            }
        });
    }
}
