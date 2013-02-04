package Tests;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class MainClass{
  public static void main(String[] args) {
    JFrame jf = new JFrame("Demo");
    Container cp = jf.getContentPane();
    MyCanvas tl = new MyCanvas();
    cp.add(tl);
    jf.setSize(300, 200);
    jf.setVisible(true);
    
    JFrame jf2 = new JFrame("Demo");
    Container cp2 = jf.getContentPane();
    MyCanvas tl2 = new MyCanvas();
    cp2.add(tl2);
    jf2.setSize(300, 200);
    jf2.setVisible(true);
  }
}

class MyCanvas extends JComponent {
  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D)g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    Font font = new Font("Serif", Font.PLAIN, 96);
    g2.setFont(font);

    g2.drawString("jade", 40, 120); 
  }
}