/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author placements2017
 */
public class Gui {

    /**
     * @param args the command line arguments
     */

         JFrame f;
          JPanel p;
          JButton b1;
          
   public void fun() {

         f = new JFrame("Search");

         p = new JPanel(new GridBagLayout());
         f.setSize(600, 300);
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         b1 = new JButton("Search");
         b1.setSize(50, 50);
         b1.addActionListener(new scan());
                 
         p.add(b1);
         
         f.add(p);
         f.setVisible(true);
         
        
    }
    
    public static void main(String[] args) {
        new Gui().fun();
    }

}
