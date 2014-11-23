package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

/**
 * Created by bogdan on 22/11/14.
 */
public class BangBeanTest extends JFrame{
    private JTextField textField = new JTextField(20);
    class BBL implements ActionListener{
        private int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("BangBean action "+count++);
        }
    }

    public BangBeanTest() throws HeadlessException {
        BangBean bangBean = new BangBean();
        try {
            bangBean.addActionListener(new BBL());
        }catch (TooManyListenersException e) {
            textField.setText("Too many listeners");
        }
        add(bangBean);
        add(BorderLayout.SOUTH, textField);
    }

    public static void main(String[] args) {
        SwingConsole.run(new BangBeanTest(), 400, 500);
    }
}
