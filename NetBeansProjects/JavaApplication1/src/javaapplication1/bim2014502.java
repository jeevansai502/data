/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package javaapplication1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import static javaapplication1.critic.ans;
import static javafx.scene.input.KeyCode.T;
import javafx.util.*;
/**
 *
 * @author placements2017
 */
public class bim2014502 {
    
    static Map<Character,List<String>> pred;
    static Map<Character,Double> time;
    static Map<Character,Pair<Double,Double>> ans;
    static ArrayList<Character> tot;
    static ArrayList<Character> cri ;
    static Map<Character,Double> copy;
    static ArrayList<Character> copyans;
    static FileWriter writer;
    static File file;
    static int check = 0;
    
    public static void printOutput() throws IOException {     
     
        if(check == 0){
            writer.write("Initial critical path:-  ");
        }else{
            writer.write("New critical path:-  ");
        }
        
        for(int j=cri.size()-1;j>=0;j--){
            
            writer.write(cri.get(j));
            if(check == 0)
                copyans.add(cri.get(j));
            if(j!=0){
                writer.write(" > ");
                writer.flush();
            }
        }
        writer.write("\n");
        writer.flush();
        
        check = 1;
    }
    
    public static void readInput() throws FileNotFoundException, IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        
        String current;
        int che = 0;
        while((current = br.readLine()) != null){       
            
            if(che == 0){
                che = 1;
                continue;
            }
            
            
            String line = current;
            List<String>  linesplit = Arrays.asList(line.split("\\s+"));
            
            char c = linesplit.get(0).charAt(0);
            String s = linesplit.get(1);
            List<String> l = Arrays.asList(s.split(","));
            
            for(int i=0;i<l.size();i++){
                String pre = l.get(i);
                l.set(i, pre.trim());
            }
            
            Double t = Double.parseDouble(linesplit.get(2));
            
            tot.add(c);
            pred.put(c,l);
            copy.put(c, t);
            
        }
        br.close();
    }
    
    
    static void fun() throws IOException{
        
        Double m=-1.0;
        char ind = '-';
        while(ans.size() != tot.size()){
            
            for(int i=0;i<tot.size();i++){
                
                if(pred.get(tot.get(i)).size() == 1 && pred.get(tot.get(i)).get(0).charAt(0) == '-'){
                    
                    Pair<Double,Double>  p = new Pair(0,time.get(tot.get(i)));
                    ans.put(tot.get(i), p);
                    
                }else{
                    
                    List<String> al = pred.get(tot.get(i));
                    Double max = -1.0;
                    int j;
                    for(j=0;j<al.size();j++){
                        char c = al.get(j).charAt(0);
                        if(ans.containsKey(c)){
                            if(ans.get(c).getValue() > max){
                                max = ans.get(c).getValue();
                            }
                        }else{
                            break;
                        }
                    }
                    if(j == al.size()){
                        
                        Pair<Double,Double>  p = new Pair(max,max+time.get(tot.get(i)));
                        ans.put(tot.get(i), p);
                        
                        if(max > m){
                            m = max;
                            ind = tot.get(i);
                        }
                    }
                    
                }
                
            }
        }
        
        cri = new ArrayList<Character>();
        
        cri.add(ind);
        
        while(pred.get(ind).size() != 0 && pred.get(ind).get(0).charAt(0) != '-'){
            
            List<String> a = pred.get(ind);
            Double cost = -1.0;
            char next = '-';
            for(int j=0;j<a.size();j++){
                if(ans.get(a.get(j).charAt(0)).getValue() > cost){
                    cost = ans.get(a.get(j).charAt(0)).getValue() ;
                    next = a.get(j).charAt(0);
                }
            }
            cri.add(next);
            ind = next;
        }
        
        printOutput();
        
    }
    
    
    public static void main(String args[]) throws IOException{
        
        Scanner sc = new Scanner(System.in);
        
        
        tot = new ArrayList<Character>();
        ans = new HashMap<Character,Pair<Double,Double>>();
        time = new HashMap<Character,Double>();
        pred = new HashMap<Character,List<String>>();
        copy = new HashMap<Character,Double>();
        copyans = new ArrayList<Character>();
        
        file = new File("bim2014502.txt");
        
        file.createNewFile();
        
        writer = new FileWriter(file);
        
        readInput();
        
        Iterator<Character> itr = copy.keySet().iterator();
        while(itr.hasNext()){
            
            Character c = itr.next();
            
            Double tim = copy.get(c);
            time.put(c, tim);
        }
        
        fun();
        
        ans.clear();
        time.clear();
        
        for(int j=1;j<copyans.size();j++){
            
            Character cc = copyans.get(j);
            itr = copy.keySet().iterator();
            while(itr.hasNext()){
                
                Character c = itr.next();
                
                Double tim = copy.get(c);
                
                if(cc.equals(c)){
                    tim = tim *(9/10.0);
                }
                
                time.put(c, tim);
                
            }
            
            writer.write("After reduction in '" + cc + "'\n");
            fun();
            
            ans.clear();
            time.clear();
        }
        writer.close();
    }
}
