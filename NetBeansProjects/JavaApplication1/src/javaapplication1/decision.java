/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author placements2017
 */
public class decision {

	FileInputStream in;
	BufferedReader br;
	ArrayList<String> al;
	ArrayList<ArrayList<String>> cond;

	void fun() throws FileNotFoundException, IOException{

		//		br = new BufferedReader(new FileReader("/home/placements2017/input.txt"));
		cond = new ArrayList<ArrayList<String>>();

		String line;
		al = new ArrayList<String>();

		getInput();
		/*   while((line = br.readLine()) != null){

		     String[] tokens = line.split("\\s+");

		     if(tokens[0] == "Actions")
		     continue;


		     int x=0;
		     String s = "";
		     while(true && x < tokens.length){

		     if(tokens[x].equals("Y") || tokens[x].equals("N") || tokens[x].equals("X") || tokens[x].matches("^-?\\d+$"))
		     break;


		     s = s + " " + tokens[x];
		     x++;
		     }

		     al.add(s);
		     ArrayList<String> temp = new ArrayList<String>();


		     for(int i=x;i<tokens.length;i++){
		     temp.add(tokens[i]);
		     }
		     cond.add(temp);
		     }

		     System.out.println(cond.get(0).size() + " " + al.size());
		     print();
		 */
	}

	void getInput() throws FileNotFoundException, IOException{

		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		String current;
		while((current = br.readLine()) != null){

			current = current.trim();
			String[] tokens = current.split("\\s+");
			if((tokens[0].equals("Actions")) || (tokens[0].equals("Condition")))
				continue;

			int x=0;
			String s = "";
			while(true && x < tokens.length){

				if(tokens[x].equals("Y") || tokens[x].equals("N") || tokens[x].equals("X") || tokens[x].matches("^-?\\d+$"))
					break;

				s = s + " " + tokens[x];
				x++;
			}
			al.add(s);
			ArrayList<String> temp = new ArrayList<String>();
			for(int i=x;i<tokens.length;i++){
				temp.add(tokens[i]);
			}
			cond.add(temp);

		}
		print();

	}
	void print(){

		int size = cond.get(0).size();
		for(int i=0;i<size;i++){

			for(int j=0;j<al.size();j++){

				String st = al.get(j).trim();
				if(!st.equals("Discount"))
					System.out.print(cond.get(j).get(i) + " " + al.get(j) + " -> " );
				else
					System.out.println(cond.get(j).get(i));

			}
		}

	}

	public static void main(String args[]) throws FileNotFoundException, IOException{
		new decision().fun();
	}
}
