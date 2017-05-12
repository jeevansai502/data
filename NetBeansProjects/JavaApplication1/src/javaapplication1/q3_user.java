/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.awt.event.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author placements2017
 */
public class q3_user implements ActionListener{

    JFrame f1,f2;
    JTextField t;
    Connection con;
     PreparedStatement ps;
    ResultSet rs;
     String query;
     JTable ta;
    
    q3_user(JFrame f1, JTextField t) {
        this.f1 = f1;
        this.t = t;
        f1.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        try{
            
            String username = t.getText();
            Class.forName("com.mysql.jdbc.Driver");
                 
             con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "iiita"); 
            
             query = "select stu.name,detail.height from stu inner join detail on stu.username = detail.username where stu.username = ?";
              ps = con.prepareStatement(query);
              ps.setString(1, username);
             
              rs = ps.executeQuery();
           
               f2 = new JFrame("result"); 
                f2.setSize(300, 300);
             f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             Vector<Vector<String>> l = new Vector<Vector<String>>();
           
            while(rs.next()){
                
               String nam,hei;
               
               nam = rs.getString(1);
               hei = rs.getString(2);
                
               Vector<String> v1 = new Vector<String>();
                v1.add(nam);
                v1.add(hei);
                
                l.add(v1);
            }
              
             Vector<String> col = new Vector<String>(); 
           col.add("name");
           col.add("height");
            ta = new JTable(l,col);
              
              JScrollPane scrollpane = new JScrollPane(ta);     
           f2.add(scrollpane);
           f2.setVisible(true);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        
        
        
        
    }
    
}
