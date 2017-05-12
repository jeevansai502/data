/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.event.*;
import static javaapplication1.q2.f;
import javax.swing.*;

/**
 *
 * @author placements2017
 */
public class q2_username  implements ActionListener{

    JFrame f,f1; 
    JTextField t;
    JLabel l1;
    JButton b1;
    
    q2_username(JFrame f) {

        this.f =f;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        f1 = new JFrame("username"); 
        
        t = new JTextField();
           
        f1.setSize(600, 300);
        f1.setLayout(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        l1 = new JLabel("Username :");
        
        t = new JTextField();
        
        l1.setBounds(80, 70, 200, 30);
         
        t.setBounds(300, 70, 200, 30);

         
        b1 = new JButton("Go!");
         
        b1.setBounds(150, 160, 100, 30);
          
        b1.addActionListener(new q3_user(f1,t));
   
        f1.add(l1);
        f1.add(t);
        f1.add(b1);
           
        f1.setVisible(true);

    }
 
}
