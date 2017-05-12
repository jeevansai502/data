/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author placements2017
 */
public class showOutput implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        
        JFrame f;
        
        JLabel l;
        
        f = new JFrame();
        f.setSize(600, 300);        
        f.setLayout(null);

        l = new JLabel();
        l.setText("Output");
        f.add(l);
        f.setVisible(true);

    }
      
}
