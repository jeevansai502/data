/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author placements2017
 */
public class tokenize {
    
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
    
    public static void main(String args[]) throws FileNotFoundException{
     
       String content = new Scanner(new File("/home/placements2017/hello.c")).useDelimiter("\\Z").next();
       removeComments(content);
       
       String DIGIT = "[0-9]";
       String NUMBER = DIGIT + "+";
       String REAL =  DIGIT + " *\".\" " + DIGIT + "+";
       String TEXT = "[a-zA-Z ]+";
       String TEXT_NUMBERS = "[a-zA-Z0-9]";
       String CONDITIONALS = "\"if\"|\"else\"|\"else if\"|\"switch\"|\"case\"";
       String KEYWORD = "\"break\"|\"continue\"|\"goto\"|\"printf\"|\"scanf\"|\"sprintf\"|\"sscanf\"|\"fopen\"|\"fwrite\"|\"fread\"|\"fclose\"|\"write\"|\"read\"|\"open\"|\"close\"|\"return\"|\"int\"|\"float\"|\"char\"|\"unsigned\"|\"signed\"|\"short\"|\"long\"|\"double\"";
       String ITERATORS = " \"for\"|\"while\"|\"do\" ";
       String PREPROCESSOR = " \"#\"|\"#line\"|\"#undef\"|\"#error\"|\"#elif\"|\"#else\"|\"#endif\"|\"#if\"|\"#define\"|\"#include\"|\"#pragma\"|\"#ifndef\"|\"#ifdef\" ";
       String DELIMITER = " [; :\t\n()\"] ";
       String IDENTIFIER = " [a-zA-Z]" + TEXT_NUMBERS + "*|[a-zA-Z]" + TEXT_NUMBERS + "*[[" + NUMBER + "+]]";
       String NON_IDENTIFIER = NUMBER + "+[A-Za-z]+";
       String FORMAT_SPECIFIER = " \"%\" " + TEXT_NUMBERS + "+" ;
       String FILE = " \"<\" " + IDENTIFIER + ".h\">\" ";
       String AOPERATOR = " \"+\"|\"-\"|\"*\"|\"/\"|\"=\" ";
       String BLOCK_BEGINS = " \"{\" ";
       String BLOCK_ENDS = " \"}\" ";
       String UNARY =  " \"++\"|\"--\" ";
       String FUNCTION  = IDENTIFIER + "+\"(\"" + DELIMITER + "*" + TEXT + TEXT_NUMBERS + "*" + DELIMITER + "*\")\"" ; 
       String LOPERATOR  = " \"&\"|\"|\"|\"&&\"|\"~\"|\"||\"|\">\"|\"<\"|\">=\"|\"<=\"|\"==\" ";
     
       ArrayList<String> al = new ArrayList<String>();
       ArrayList<String> al1 = new ArrayList<String>();
   //    al.add(DIGIT);
      // al1.add("DIGIT");
  //      al.add(NUMBER);
     //   al1.add("NUMBER");
  //      al.add(REAL);
    //    al1.add("REAL");
  //      al.add(TEXT);
  //      al1.add("TEXT");
   //     al.add(TEXT_NUMBERS);
   //     al1.add("TEXT_NUMBERS");
        al.add(CONDITIONALS);
       al1.add("CONDITIONALS");
        al.add(KEYWORD);
        al1.add("KEYWORD");
        al.add(ITERATORS);
        al1.add("ITERATORS");
        al.add(PREPROCESSOR);
        al1.add("PREPROCESSOR");
        al.add(DELIMITER);
        al1.add("DELIMITER");
        al.add(IDENTIFIER);
        al1.add("IDENTIFIER");
        al.add(NON_IDENTIFIER);
        al1.add("NON_IDENTIFIER");
        al.add(FORMAT_SPECIFIER);
        al1.add("FORMAT_SPECIFIER");
        al.add(FILE);
        al1.add("FILE");
        al.add(AOPERATOR);
        al1.add("AOPERATOR");
        al.add(BLOCK_BEGINS);
        al1.add("BLOCK_BEGINS");
        al.add(BLOCK_ENDS);
        al1.add("BLOCK_ENDS");
       al.add(UNARY);
       al1.add("UNARY");
       al.add(FUNCTION);
      al1.add("FUNCTION");
       al.add(LOPERATOR);
       al1.add("LOPERATOR");
       
        for(int i=0;i<al.size();i++){
            System.out.println(al.get(i));
            Pattern p = Pattern.compile(al.get(i)); 
            Matcher m = p.matcher(content);
            
        while(m.find()){
            int r =  m.regionEnd();
                if(r == content.length()){
                    System.out.println(m.group(0) + " is a " + al1.get(i));
                }else if(content.charAt(r) == ' '){
                    System.out.println(m.group(0) + " is a " + al1.get(i));
                }
            }
            
        }
    
    }
  
}
