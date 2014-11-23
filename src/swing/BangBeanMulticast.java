package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Created by bogdan on 22/11/14.
 */
public class BangBeanMulticast extends JPanel implements Serializable {
    private int xm, ym;
    private int cSize = 20;
    private String text = "Bang!";
    private int fontSize = 48;
    private Color color = Color.RED;
    private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

    public BangBeanMulticast() {
        addMouseListener(new ML());
        addMouseMotionListener(new MML());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawOval(xm-cSize/2, ym-cSize/2, cSize, cSize);
    }

    public synchronized void addActionListener(ActionListener actionListener) throws TooManyListenersException {
        actionListeners.add(actionListener);
    }

    class ML extends MouseAdapter{

        @Override
        public void mousePressed(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(color);
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            int width = g.getFontMetrics().stringWidth(text);
            g.drawString(text, (getSize().width - width)/2, getSize().height/2 );
            g.dispose();
            notifyListeners();
        }
    }

    private void notifyListeners() {
        ActionEvent actionEvent = new ActionEvent(BangBeanMulticast.this, ActionEvent.ACTION_PERFORMED, null);

        List<ActionListener> lv = null;
        synchronized (this){
            lv = new ArrayList<ActionListener>(actionListeners);
        }

        for (ActionListener actionListener : lv) {
            actionListener.actionPerformed(actionEvent);
        }
    }

    class MML extends MouseMotionAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
            xm = e.getX();
            ym = e.getY();
            repaint();
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    public synchronized int getXm() {
        return xm;
    }

    public synchronized void setXm(int xm) {
        this.xm = xm;
    }

    public synchronized int getYm() {
        return ym;
    }

    public synchronized void setYm(int ym) {
        this.ym = ym;
    }

    public synchronized int getcSize() {
        return cSize;
    }

    public synchronized void setcSize(int cSize) {
        this.cSize = cSize;
    }

    public synchronized String getText() {
        return text;
    }

    public synchronized void setText(String text) {
        this.text = text;
    }

    public synchronized int getFontSize() {
        return fontSize;
    }

    public synchronized void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public synchronized Color getColor() {
        return color;
    }

    public synchronized void setColor(Color color) {
        this.color = color;
    }

    public static void main(String[] args) throws TooManyListenersException {
        BangBeanMulticast bb2 = new BangBeanMulticast();
        bb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent" + e);
            }
        });
        bb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("BangBean2 action");
            }
        });
        bb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("More action");
            }
        });
        JFrame frame = new JFrame();
        frame.add(bb2);
        SwingConsole.run(frame, 300, 300);
    }
}
