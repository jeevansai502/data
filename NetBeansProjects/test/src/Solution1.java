
import java.io.*;
import java.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author placements2017
 */
public class Solution1 {
    
    
    
      public static void main(String[] args){
    
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            
            ArrayList<Integer> ques = new ArrayList<>();
            ArrayList<String> ans = new ArrayList<>();
            
            for(int i=0;i<n;i++){
                
                int m = sc.nextInt();
                ques.add(m);
            }
            
            for(int i=0;i<n;i++){
                
                String m = sc.next();
                ans.add(m);  
            }
            
            int count = 0;
            if(ques.get(0)%2 == 0 && ans.get(0).equals("Even") || ques.get(0)%2 == 1 && ans.get(0).equals("Odd")){
                  System.out.println(0);
                  return;
      }
            
            for(int i=1;i<n;i++){
                
                if(ques.get(i)%2 == 0 && ans.get(i).equals("Odd") && ques.get(i-1)%2 == 1){
            System.out.println(count); 
            return;
                }else if(ques.get(i)%2 == 1 && ans.get(i).equals("Even") && ques.get(i-1)%2 == 1)
                    System.out.println(count); 
                return;
            }
         
            
            
          
      }
}
