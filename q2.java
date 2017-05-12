/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.sql.*;
/**
 *
 * @author placements2017
 */
public class q2 {
    
   static Scanner s ;
   static Connection con;
   static PreparedStatement ps;
   static ResultSet rs;
   static void add() throws ClassNotFoundException, SQLException{
   
       String id,name,fname,address,mobile,query;
       int age;
       double cgpa;
       
       
        System.out.println("Enter id");
        id =s.next();
        
        System.out.println("Enter name");
        name = s.next();
        
        System.out.println("Enter fname"); 
        fname = s.next();
        
        System.out.println("Enter address");
        address = s.next();
        
        System.out.println("Enter mobile number");
        mobile = s.next();
        
        System.out.println("Enter age");
        age = s.nextInt();
        
        System.out.println("Enter cgpa");
        cgpa = s.nextDouble();
        
       query = "insert into data values(?,?,?,?,?,?,?)";
       
       ps = con.prepareStatement(query);
       ps.setString(1, id);
       ps.setString(2, name);
       ps.setString(3, fname);
       ps.setString(4, address);
       ps.setInt(5, age);
       ps.setString(6,mobile);
       ps.setDouble(7,cgpa);
       
       ps.executeUpdate();
       
   }
    
   static void delete() throws SQLException{
   
        String id,query;
        
        System.out.println("Enter id");
        id =s.next();
       
       query = "delete from data where id=?";
       ps = con.prepareStatement(query);

       ps.setString(1, id);
       ps.executeUpdate();

       
       
   }
    
   static void update() throws SQLException{
       
          String id,name,fname,address,mobile,query;
       int age;
       double cgpa;
       
       
        System.out.println("Enter id");
        id =s.next();
        
        System.out.println("Enter name");
        name = s.next();
        
        System.out.println("Enter fname"); 
        fname = s.next();
        
        System.out.println("Enter address");
        address = s.next();
        
        System.out.println("Enter mobile number");
        mobile = s.next();
        
        System.out.println("Enter age");
        age = s.nextInt();
        
        System.out.println("Enter cgpa");
        cgpa = s.nextDouble();

       
         query = "update data set name=?,fname=?,address=?,mobile=?,age=?,cgpa=? where id=?";
            ps = con.prepareStatement(query);

         ps.setString(1, name);
       ps.setString(2, fname);
       ps.setString(3, address);
       ps.setInt(4, age);
       ps.setString(5,mobile);
       ps.setDouble(6,cgpa);
       ps.setString(7, id);

       ps.executeUpdate();
       
   
   }

   static void search() throws SQLException{
       String id,name,fname,address,mobile;
    int age;
       Double cgpa;
        System.out.println("Enter id");
        id =s.next();
        
       String query = "select * from data where id =?";
        ps = con.prepareStatement(query);
       ps.setString(1,id);
   
       rs = ps.executeQuery();
       
       while(rs.next()){
       
           name = rs.getString("name");
           fname = rs.getString("fname");
           address = rs.getString("address");
           mobile = rs.getString("mobile");
           age = rs.getInt("age");
           cgpa = rs.getDouble("cgpa");
           
       
           System.out.println(name + " " + fname + " " + address + " " + mobile + " " + age +" " + cgpa);
       }
       
       
   } 
   
    public static void main() throws SQLException, ClassNotFoundException{
    
    int choice;
       
        
     s = new Scanner(System.in);
     Class.forName("com.mysql.jdbc.driver");
     con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "iiita"); 
     
    System.out.println("Select\n1.Add\n2.Delete\n3.Update\n4.Search\n");
    
    choice = s.nextInt();
    
    switch(choice){
        
        case 1:
            add();
        case 2:
            delete();
        case 3:
            update();
        case 4:
            search();
        default:
            System.out.println("error");
        
    }
    
    
    }
}
