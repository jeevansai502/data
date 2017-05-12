/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author placements2017
 */
public class halstead {

	int uoperat = 0,toperat=0;
	int uoperan = 0,toperan=0;  
	File file;
	FileWriter writer;

	private String findComparisions(String content, String[] comparisons) throws IOException{

		Pattern p;
		Matcher m;

		for(String op : comparisons){

			p = Pattern.compile(op);
			m = p.matcher(content);

			int count = 0;
			while(m.find()){
				count++;
			}

			if(count != 0){
				uoperat++;
				toperat += count;
				writer.write(op + " " + count + "\n");
			}
			content.replaceAll(op, " ");
		}
		writer.flush();
		return content;
	}

	private String findBrackets(String content, String[] brackets)  throws IOException{

		Pattern p;
		Matcher m;

		for(String op : brackets){

			p = Pattern.compile(Pattern.quote(op));
			m = p.matcher(content);

			int count = 0;
			while(m.find()){
				count++;
			}

			if(count != 0){
				uoperat++;
				toperat += count;

				if(op.equals("[")){
					writer.write(op + getClosing(op) + " " + count + "\n");
				}else if(op.equals("}")){
					writer.write(op + " " + (count-1) + "\n");
				}else{
					writer.write(op + " " + count + "\n");
				}
			}

			String st = "\\" + op;
			content.replaceAll(st, " ");

		}
		writer.flush();

		return content;
	}

	private String findArithmetic(String content, String[] arithmetic)  throws IOException{

		Pattern p;
		Matcher m;

		for(String op : arithmetic){

			if (op.equals("+") || op.equals("-")) {
				p = Pattern.compile(Pattern.quote(op) + "(?!" + Pattern.quote(op) + ")");
			} else {
				p = Pattern.compile(Pattern.quote(op));
			}
			m = p.matcher(content);

			int count = 0;
			while(m.find()){
				if(op.equals("+") || op.equals("-")){
					String left = String.valueOf(content.charAt(m.start()-1)),right = String.valueOf(content.charAt(m.start()+1));
					if(!(left.equals(op)) && !(right.equals(op))){
						count++;						
					}

				}else{
					count++;
				}
			}

			if(count != 0){
				uoperat++;
				toperat += count;
				writer.write(op + " " + count + "\n");
			}
		}
		writer.flush();

		return content;
	}

	private String findDataTypes(String content, String[] datatypes)  throws IOException{

		for (String op : datatypes) {
			Pattern pattern = Pattern.compile("\\b"+Pattern.quote(op)+"\\b");
			Matcher m = pattern.matcher(content);
			int count = 0;
			while (m.find()) {
				count++;
			}
			if (count != 0) {
				uoperat++;
				toperat += count;
				writer.write(op + " " + count + "\n");
			}
			content = m.replaceAll(" ");
		}
		writer.flush();

		return content;

	}

	private String findJumps(String content, String[] jumps)  throws IOException{


		for (String op : jumps) {
			Pattern pattern = Pattern.compile("\\b"+Pattern.quote(op)+"\\b");
			Matcher m = pattern.matcher(content);
			int count = 0;
			while (m.find()) {
				count++;
			}
			if (count != 0) {
				uoperat++;
				toperat += count;
				writer.write(op + " " + count + "\n");
			}
			content = m.replaceAll(" ");
		}
		writer.flush();
		return content;

	}

	private String findConditions(String content, String[] conditions)  throws IOException{



		for (String op : conditions) {
			Pattern pattern = Pattern.compile("\\b"+Pattern.quote(op)+"\\b");
			Matcher m = pattern.matcher(content);
			int count = 0;
			while (m.find()) {
				count++;
			}
			if (count != 0) {
				uoperat++;
				toperat += count;
				writer.write(op + " " + count + "\n");
			}
			content = m.replaceAll(" ");
		}
		writer.flush();
		return content;

	}

	private String findMisc(String content, String[] misc)  throws IOException{

		for (String op : misc) {
			Pattern pattern = Pattern.compile(op);
			Matcher m = pattern.matcher(content);
			int count = 0;
			while (m.find()) {
				count++;
			}
			if (count != 0) {
				uoperat++;
				toperat += count;
				if (op.equals("=(?!=)")) {
					writer.write("= " + count + "\n");
				} else {
					writer.write(op + " " + count + "\n");
				}
			}
			content = m.replaceAll(" ");
		}

		writer.flush();
		return content;
	}

	void findOperands(String data)  throws IOException{
		HashMap<String, Integer> operands = new HashMap<String, Integer>();

		Pattern pattern = Pattern.compile("\\b\\w+\\b");
		Matcher m = pattern.matcher(data);
		while (m.find()) {
			String operand = m.group(0);
			if (operands.get(operand) == null) {
				uoperan++;
				operands.put(operand, 1);
			} else {
				operands.put(operand, operands.get(operand) + 1);
			}
		}
		for (String operand : operands.keySet()) {
			toperan += operands.get(operand);
			writer.write(operand + " " + operands.get(operand) + "\n");
		}
		writer.flush();

	}

	String findHeader(String content) throws IOException{

		Pattern pattern = Pattern.compile("(void|int|char|float|double)\\s+(.+)\\(.*\\)\\s*\\{");
		Matcher m = pattern.matcher(content);

		content = m.replaceAll("");

		return content;
	}

	void fun() throws FileNotFoundException,IOException{

		file = new File("bim2014502.txt");
		file.createNewFile();
		writer = new FileWriter(file);		

		String content = new Scanner(new File("input.c")).useDelimiter("\\Z").next();

		content = bim2014502_1.removeComments(content);
		content = bim2014502_1.removeQuotes(content);

		String[] comparisons = {"<", ">", "=="};
		String[] brackets = {"(", ")", "}", "{", "["};
		String[] arithmetic = {"++", "--", "+", "-", "/", "*", "%"};
		String[] conditions = {"for", "while", "if", "switch", "case", "else", "default"};
		String[] datatypes = {"char", "int", "float", "double"};
		String[] jumps = {"return", "break", "continue"};
		String[] misc = {",", ";", "=(?!=)"};


		writer.write("Operators    " + "Count\n");
		content = findHeader(content);
		content = findComparisions(content,comparisons);
		content = findBrackets(content,brackets);
		content = findArithmetic(content,arithmetic);
		content = findConditions(content,conditions);
		content = findDataTypes(content,datatypes);
		content = findJumps(content,jumps);
		content = findMisc(content,misc);
		writer.write("\nOperands    " + "Count\n");
		findOperands(content);

		writer.write("          " + "Total  " + "Unique   \n");
		writer.write("Operators  " + " N1=" + toperat + " n1=" + uoperat + "\n");
		writer.write("Operands   " + " N2=" + toperan + " n2=" + uoperan + "\n");
		writer.flush();

	}

	public static void main(String args[]) throws FileNotFoundException, IOException{
		new halstead().fun();
	}

	private String getClosing(String op) {

		if (op.equals("(")) {
			return ")";
		} else if (op.equals("[")) {
			return "]";
		} else if (op.equals("{")) {
			return "}";
		} else {
			return "ERROR";
		}

	}

}
