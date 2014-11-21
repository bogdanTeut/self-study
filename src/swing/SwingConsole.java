package swing;

import javax.swing.*;

/**
 * Created by bogdan.teut on 19/11/2014.
 */
public class SwingConsole {
    public static void run (final JFrame jFrame, final int x, final int y){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jFrame.setTitle(jFrame.getClass().getSimpleName());
                jFrame.setSize(x, y);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.setVisible(true);
            }
        });

    }
}
