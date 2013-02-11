package Tests;

import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.*;  
import javax.swing.*; 

public class MainClass {
 public static void main(String[] args){  
   
   JFrame jf = new JFrame();
   jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   jf.setSize(50,50);
   jf.setVisible(true);
   Dimension size = jf.getSize();
   System.out.println(size); 
   Insets insets = jf.getInsets();
   int insetwidth = insets.left + insets.right;
   int insetheight = insets.top + insets.bottom;
   System.out.println("Insets left and right = " + insetwidth);
   System.out.println("Insets top and bottom = " + insetheight); 
   jf.setSize((int)size.getWidth() + insetwidth,(int)size.getHeight() + insetheight); 
   System.out.println(jf.getSize());
 }
}