/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author placements2017
 */
public class cfg {
    
    
         void findCfg(String filename) throws FileNotFoundException{
    
             URL path = cfg.class.getResource(filename);
            String data = new Scanner(new File(path.getFile())).useDelimiter("\\Z").next();
            data = bim2014502_1.removeComments(data);
            data = bim2014502_1.removeQuotes(data);
            
            getControls(data);
        }
        
         void getControls(String data){
    
            String control = "(if|for|while|switch)\\s*\\(.*?\\)\\s*\\{";
            
            Pattern pcond = Pattern.compile(control);
            
            Matcher mcond = pcond.matcher(data);
            
            while(mcond.find()){
            
                int start = mcond.end();
                int end = findEnd(start,data);
                
                getControls(data.substring(start,end));
                
            }
            
            
        }
        
        int findEnd(int ind,String data){
            
            int count = 0;
            for(int i=ind;i<data.length();i++){
           
                if(data.charAt(i) == '{'){
                    count++;
                }else if(data.charAt(i) == '}'){
                    count--;
                }
                
                if(count < 0)
                    return i;
            }
            return -1;
            
        }
        
        
    
        public static void main(String args[]) throws FileNotFoundException{

              /*      if(args.length != 1){
                        System.out.println("Error in filename");
                    }
            */
                    new cfg().findCfg("input.c");
            
        }
    
}
