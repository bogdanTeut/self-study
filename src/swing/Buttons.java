package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bogdan.teut on 19/11/2014.
 */
public class Buttons extends JFrame {

    private JButton button1 = new JButton("Button 1");
    private JButton button2 = new JButton("Button 2");
    private JButton button3 = new JButton("Button 3");
    private JTextField label = new JTextField(10);

    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText(((JButton)e.getSource()).getText());
        };
    };

    public Buttons() throws HeadlessException {
        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);
        button3.addActionListener(actionListener);
        setLayout(new FlowLayout());
        add(button1);
        add(button2);
        add(button3);
        add(label);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Buttons(), 300, 100);
    }
}
