package src;

import view.JDBCFrame;

import javax.swing.JFrame;

public class Main
{  
   public static void main(String[] args)
   {  
      JDBCFrame frame = new JDBCFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}