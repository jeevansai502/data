/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.io.*;
import java.util.*;

/**
 *
 * @author placements2017
 */

class Node{
 
    char data;
    Set<Integer> firstPos;
    Set<Integer> lastPos;
    Node parent = null;
    
    Node(char data){
        this.data = data;
    }
    
    void setParent(Node parent){
        this.parent = parent;
    }
    
    Node getParent(){
        return parent;
    }
    
    void setfirstPos(Set<Integer> firstPos){
        this.firstPos = firstPos;
    }
    
    void setlastPos(Set<Integer> lastPos){
        this.lastPos = lastPos;
    }
    
}

public class Reg2Dfa {
    
        static Set<Character> operands;
        static Node root;
        static Set<Node> children;
        
        static void createTree(String[] spl){
            
              children = new HashSet<>();
              root = new Node('.');  
              Stack<Character> st1 = new Stack();  
            
              Node r = root; 
              for(int i=0;i<spl.length;i++){
                String s = spl[i]; 
                int n = s.length();
                
                 Node nod = r;
                if(i>0){
                    Node n2 = new Node('.');
                    n2.setParent(r);
                    nod = n2;
                }
                
                for(int j=0;j<n;j++){
                    char c = s.charAt(j);
                    
                    if(c == '(' | c == '[' | c == '{'){
                        removeMatch(spl[i],j);
                    }
                    
                    if(operands.contains(c)){
                        st1.push(c);
                    }else{
                        Node n1 = new Node(c);
                        n1.setParent(nod);
                        if(!st1.empty()){
                            char c1 = st1.pop();
                            Node n2 = new Node(c1);
                            n2.setParent(n1);
                            children.add(n2);
                        }
                        nod = n1;
                    }
                }
                
                if(!st1.empty()){
                    char c1 = st1.pop();
                    Node n2 = new Node(c1);
                    n2.setParent(nod); 
                    children.add(n2);
                }
            }
            
            traverse();
        }
    
        public static void main(String args[]) throws FileNotFoundException{
            
           Scanner sc = new Scanner(System.in); 
           String str = sc.next();
           operands = new HashSet<>();
           
           while(sc.hasNext()){
               operands.add(sc.next().charAt(0));
           }
           
           String[] spl = str.split(".");
           
           createTree(spl);
         
        }    

    private static String removeMatch(String string, int j) {

        char match = '\0';
        string = string.substring(0,j) + string.substring(j+1);
        if(string.charAt(j) == '(')
            match = ')';
        else if(string.charAt(j) == '[')
            match = ']';
        else if(string.charAt(j) == '{')
            match = '}';
        
        for(int i=j;i<string.length();i++){
            if(string.charAt(i) == match){
                string = string.substring(0,i) + string.substring(i+1);
            }
        }
        return string;
    }

    private static void traverse() {

        int index = 0;
       for(Node nod:children){
           
           Set<Integer> firstpos = new HashSet<>();
           Set<Integer> lastpos = new HashSet<>();
           
           firstpos.add(index);
           lastpos.add(index);  
           
           nod.setfirstPos(firstpos);
           nod.setlastPos(lastpos);
           while(nod.getParent() != null){
               nod.firstPos.addAll(firstpos);
               nod.lastPos.addAll(lastpos);
           }
           
       }
       
    }
}
