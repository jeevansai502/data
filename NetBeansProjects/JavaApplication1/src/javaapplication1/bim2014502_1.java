/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import java.util.regex.*;
/**
 *
 * @author placements2017
 */
public class bim2014502_1 {
 
    
    static String removeComments(String data){
   
        String scomments = "//.*?\\n";
        
        Pattern single = Pattern.compile(scomments,Pattern.DOTALL);
        Matcher msingle = single.matcher(data);
        data = msingle.replaceAll("\n");
        
        String mcomments = "/\\*.*?\\*/";
        
        Pattern multi = Pattern.compile(mcomments,Pattern.DOTALL);
        Matcher mmulti = multi.matcher(data);
        data = mmulti.replaceAll("");
        
        return data;
        
        
    }
    
    static String removeQuotes(String data){
        
        String quotes = "\".*?\"";
        
        Pattern rq = Pattern.compile(quotes,Pattern.DOTALL);
        Matcher mq = rq.matcher(data);
        data = mq.replaceAll("");
        
        return data;
    }
    
    
}
