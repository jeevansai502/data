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
public class lcom {

	public static void main(String args[]) throws FileNotFoundException{

		Pattern p1,p2,p3;

		p1 = Pattern.compile("\\w+\\s*\\(.*\\)\\s*\\{");

		String content = new Scanner(new File("/home/placements2017/Main.java")).useDelimiter("\\Z").next();

                content = bim2014502_1.removeComments(content);
                
		p2 = Pattern.compile("(boolean|int|double|float|long|char|short|byte)\\s+\\w+(\\s*\\,\\s*\\w*)*\\;|(boolean|int|double|float|long|char|short|byte)\\s+\\w+(\\s*\\=\\s*\\w*)(\\s*)((\\+|\\-|\\*|\\/|\\%)(\\s*\\w*))*\\;|(boolean|int|double|float|long|char|short|byte)\\s+\\w+(\\s*\\=\\s*\\w*\\s*\\((\\s*\\w*\\s*\\,*)*\\))\\;");

		p3 = Pattern.compile("class\\s+\\w+\\s*\\{");

		Matcher m3 = p3.matcher(content);
		Matcher mc = p3.matcher(content);     
		mc.find();
		while(m3.find()){

			int count1=0,count2=0;
			String cl;
			int ind2;
			int ind1 = content.lastIndexOf(m3.group(0));
			if(mc.find()){
				ind2 = content.lastIndexOf(mc.group(0));
				cl = content.substring(ind1, ind2);
			}else{
				cl = content.substring(ind1);
			}

			Pattern p5 = Pattern.compile("(boolean|int|double|float|long|char|short|byte)\\s+\\w+(\\s*\\,\\s*\\w*)*\\;");

			Matcher m5 = p5.matcher(cl);

			while(m5.find()){

				String st = m5.group(0);
				String[] arr = st.split(" ", 2);
				String st2 = arr[1];
				String[] st3 = st2.split(",");

				for(int i=0;i<st3.length;i++){
					st3[i] = st3[i].replaceAll("\\s","");
					st3[i] = st3[i].replaceAll(";","");
				}

				Matcher m6 = p1.matcher(cl);
				Matcher mc1 = p1.matcher(cl);
				Matcher mx = p1.matcher(cl);
				int n=0;
				while(mx.find())
					n++;

				mc1.find();
				while(m6.find()){

					String c;
					int in2;
					int in1 = cl.lastIndexOf(m6.group(0));
					if(mc1.find()){
						in2 = cl.lastIndexOf(mc1.group(0));
						c = cl.substring(in1, in2);
					}else{
						c = cl.substring(in1);
					}


					count1 = (n*(n-1))/2;

					Pattern p7 = Pattern.compile("\\w+\\s*(\\+|\\-|\\*|\\/|\\%|\\=)");

					Matcher m8 = p7.matcher(c);

					int x=0;
					while(m8.find()){
                                           
                                                                                      
                                   	if(Arrays.asList(st3).contains(m8.group(0).charAt(0))){

							x=1;
							break;
						}
						if(x==1)
							count2++;
					}
				}

				System.out.println(count1-2*count2);


			}

		} 

	}
}
