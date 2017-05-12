/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author placements2017
 */
public class test implements ActionListener{

    JComboBox c;
    public test(JComboBox c){
    
        this.c = c;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println(c.getSelectedItem());

    }
  
}
