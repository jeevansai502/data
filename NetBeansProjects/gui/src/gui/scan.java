/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author placements2017
 */
public class scan implements ActionListener{

    JFrame f1;
    JButton b1,b2;
    JComboBox c;
    
    public scan() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

                f1 = new JFrame("scan"); 
                 f1.setSize(600, 300);
                f1.setLayout(null);
                
                String country[]={"India","Aus","U.S.A","England","Newzeland"};  
                c = new JComboBox(country);
                
                c.setBounds(200, 70, 200, 30);

                f1.setSize(600, 300);
                f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                b1 = new JButton("Add");
                b1.setSize(50, 50);
                        b1.setBounds(100, 160, 100, 30);

                 b1.addActionListener(new test(c));
                  
                b2 = new JButton("Finish");
                b2.setSize(50, 50);
                        b2.setBounds(400, 160, 100, 30);

                b2.addActionListener(new showOutput());

  
      f1.add(c);
      f1.add(b1);
      f1.add(b2);
      f1.setVisible(true);
         
         
    }
    
}
