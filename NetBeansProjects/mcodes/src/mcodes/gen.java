/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author placements2017
 */
public class gen {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        Scanner sc = new Scanner(new File("chess.dat"));
        Set<Integer> set1 = new HashSet<>();
        while (sc.hasNextLine()) {
            
            String s = sc.nextLine();
            if (s.matches("\\s*")) {
                continue;
            }
            
            String[] spl = s.split("\\s+");
            
            for (int i = 0; i < spl.length; i++) {
                set1.add(Integer.parseInt(spl[i]));
            }
            
        }
        
        File file = new File("support.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);        
        
        for (int i : set1) {
            
            double random = ThreadLocalRandom.current().nextDouble(0.5, 0.9);            
            String s = i + " " + random + "\n";
            writer.write(s);
            writer.flush();            
            
        }
        
    }
    
}
