/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author placements2017
 */
public class out {
    
    
    	public static void main(String args[]) throws FileNotFoundException, IOException{

                  File file = new File("bim2014502.txt");
                        file.createNewFile();
                              FileWriter writer = new FileWriter(file); 
                                writer.write("This\n is\n an\n example\n");     
                              writer.flush();
                      //           writer.close();
            
        }
    
}
