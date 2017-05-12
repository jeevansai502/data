/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author placements2017
 */
public class q2 {
    
          static JFrame f;
          static JPanel p;
 
      public static void main(String args[]) {
    
          f = new JFrame("search");
          
          p = new JPanel();
          f.setSize(600, 300);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
             
          JButton b1,b2;
       
          b1 = new JButton("username");
          b2 = new JButton("height");
                
          b1.addActionListener(new q2_username(f));     
    
          b2.addActionListener(new q3_height(f));
            
          b1.setSize(50, 50);
          b2.setSize(50, 50);
           
          GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                   
                p.add(b1,gbc);
                
                gbc.gridx = 5;
                gbc.gridy = 0;
                
                p.add(b2,gbc); 
                f.add(p);
                f.setVisible(true);
                    
                    
      }
}
