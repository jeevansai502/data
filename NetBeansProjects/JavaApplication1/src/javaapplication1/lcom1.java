/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;
import javafx.util.*;
/**
 *
 * @author placements2017
 */
public class lcom1 {
    
    static List < String > varl;
    static int count1=0,count2=0;
    
    void findlcom(String filename) throws FileNotFoundException {

        URL path = cfg.class.getResource(filename);
        String data = new Scanner(new File(path.getFile())).useDelimiter("\\Z").next();
        data = bim2014502_1.removeComments(data);
        data = bim2014502_1.removeQuotes(data);

        findClass(data);
    }

    void findClass(String data) {

        Pattern p = Pattern.compile("class\\s+(.*?)\\{");
        Matcher m = p.matcher(data);

   
        int start = -1, end;
        while (m.find()) {
            
        count1=0;
        count2=0;
            start = m.end();
            end = findEnd(start, data);
            varl = findVarC(data.substring(start, end));

            System.out.println(m.group(1));
            findMethod(data.substring(start, end));

        }
       
    }


    void findMethod(String data) {

        Pattern p = Pattern.compile("(void|int|double|float|char)\\s+(.*?)\\s*\\(.*?\\)\\s*\\{");
        Matcher m = p.matcher(data);
        
        ArrayList<Pair<String , List<String>>> al = new ArrayList<Pair<String , List<String>>>(); 
        
        while(m.find()){
            
            int start,end;
            String name = m.group(2);
            System.out.println(name);
            
            start = m.end();
            end = findEnd(start , data);
            List<String> fv = findVar(data.substring(start,end));
            al.add(new Pair(name,fv));
        }
        
        ArrayList<Pair<String,String>> ax = new ArrayList<Pair<String,String>>();
        for(int i=0;i<al.size();i++){
          
            for(int j=i+1;j<al.size();j++){
                             
                if(!Collections.disjoint(al.get(i).getValue(), al.get(j).getValue()) && !ax.contains(new Pair(al.get(i).getKey(),al.get(j).getKey())) && !ax.contains(new Pair(al.get(j).getKey(),al.get(i).getKey()))){
                    count1++;
                    ax.add(new Pair(al.get(i).getKey(),al.get(j).getKey()));
                }else{
                    count2++;
                }
            }
           
            
        }
        System.out.println(count2 + " " + count1);
    }
    
    
    List < String > findVar(String data) {
     
        Pattern
        var = Pattern.compile("(int|double|float|char|\\s)\\s+(.*?)\\s*\\;");
        Matcher mvar =
            var.matcher(data);

        List < String > items = new ArrayList < String > ();
        while (mvar.find()) {

            List < String > i = new ArrayList < String > ();
            
            String sf = mvar.group(2) + " ";

            Pattern pval = Pattern.compile("([a-zA-Z0-9]+)(\\s*\\=|\\s*\\+|\\s*\\-|\\s*\\*|\\s*/|\\s*%|\\s*^|\\s*&|\\s*,|\\s*;)");
            Matcher mval = pval.matcher(sf);
            
            while(mval.find()){
   
                if(!i.contains(mval.group(1))){
                    i.add(mval.group(1));
                }
                
            }
        
            pval = Pattern.compile("\\((.*?)\\)");           
            mval = pval.matcher(sf);
            
            while(mval.find()){
             
              String sx = mval.group(1);
                List<String> list = new ArrayList<String>(Arrays.asList(sx.split(" , ")));
              i.addAll(list);
            }
             
           items.addAll(i);
        }
        
        items.removeAll(findVarC(data));
        
     
       return items; 
        
    }
    
    List < String > findVarC(String data) {

        Pattern p = Pattern.compile("(void|int|double|float|char)\\s+.*?\\s*\\(.*?\\)\\s*\\{");

        while (true) {
            Matcher m = p.matcher(data);
            if (m.find()) {
                int start = m.start();
                int end = findEnd(m.end(), data);
                data = data.substring(0, start) + data.substring(end, data.length());
            } else {
                break;
            }
        }

        Pattern
        var = Pattern.compile("(int|double|float|char)\\s+(.*?)\\s*\\;");
        Matcher mvar =
            var.matcher(data);

        List < String > items = new ArrayList < String > ();
        while (mvar.find()) {

            String v = mvar.group(2);
            v = v.replaceAll("\\s", "");
            List < String > i = new ArrayList < String > (Arrays.asList(v.split(",")));
            items.addAll(i);
        }

        return items;

    }

    int findEnd(int ind, String data) {

        int count = 0;
        for (int i = ind; i < data.length(); i++) {

            if (data.charAt(i) == '{') {
                count++;
            }else if (data.charAt(i) == '}') {
                count--;
            }

            if (count < 0)
                return i;
        }
        return -1;

    }

    public static void main(String args[]) throws FileNotFoundException {

        new lcom1().findlcom("input.c");
        
    }

}