/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author placements2017
 */
public class cyclo {

	public static void main(String args[]) throws FileNotFoundException{

		Pattern p1,p2,p3;
		int c=0;

		p1 = Pattern.compile("\\w+\\s*\\(.*\\)\\s*\\{");

		String content = new Scanner(new File("/home/placements2017/example.c")).useDelimiter("\\Z").next();

		Matcher m1 = p1.matcher(content);
		Matcher mc = p1.matcher(content);
		int i=0,cond = 0;
		while(m1.find()){
			String s1 = m1.group(0);

			p2 = Pattern.compile("if\\(|while\\(|for\\(");
                       
			Matcher m2 = p2.matcher(s1);    

			if(!m2.find()){

                		p3 = Pattern.compile("\\w+");
				Matcher m3 = p3.matcher(s1);

				if(m3.find()){

					System.out.println(m3.group(0));

					int count = 0;

					mc.find();
					String scw = mc.group(0);
                                        Matcher m5 = p2.matcher(scw);
                                        if(m5.find()) 
							count++;
                                        while(mc.find()){
                                            String sc = mc.group(0);
                                          
                                            Matcher m4 = p2.matcher(sc);
						if(m4.find()) 
							count++;
						else
							break; 
					}
					System.out.println(count+1);
                                        c+=count+1;
				}
				cond=1;
			}

			if(cond == 0)   
				mc.find();
		}
		System.out.println(c);
	} 

}
