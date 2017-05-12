/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

import java.util.*;
import java.io.*;

public class Apriori {

    double support;
    ArrayList<String> trans;
    ArrayList<Set<Integer>> trans1;
    Map<String, Integer> map;
      long start;

    void print(ArrayList<String> temp) {

        for (int i = 0; i < temp.size(); i++) {
     //       System.out.println(temp.get(i));
        }
        System.out.println("Count :" + temp.size());
    }

    void run() throws FileNotFoundException {

          start = System.currentTimeMillis();
        trans = new ArrayList<>();
        trans1 = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter support %");
        support = sc.nextDouble();

        System.out.println("Enter file name");
        String file = sc.next();

        sc = new Scanner(new File(file));
        int lines = 0;
        while (sc.hasNextLine()) {

            String s = sc.nextLine();
            if (s.matches("\\s*")) {
                continue;
            }
            lines++;

            String[] spl = s.split("\\s+");
            ArrayList<Integer> elem = new ArrayList<>();

            for (int i = 0; i < spl.length; i++) {

                String cand;
                int n = Integer.parseInt(spl[i]);

                cand = spl[i].trim();

                if (!elem.contains(n)) {
                    elem.add(n);
                }

                if (map.containsKey(cand)) {
                    int count = map.get(cand);
                    map.put(cand, count + 1);
                } else {
                    map.put(cand, 1);
                }
            }

            Collections.sort(elem);
            Set se = new HashSet<>();
            se.addAll(elem);
            
            String con = " ";
            for (int i = 0; i < elem.size(); i++) {
                con = con + elem.get(i) + " ";
                String s1 = String.valueOf(elem.get(i)).trim();
                if(!temp.contains(s1))
                    temp.add(s1);
            }

            trans.add(con);
            trans1.add(se);
        }

        support = (support * lines) / 100;
        System.out.println(System.currentTimeMillis() - start);
        apriori(temp, 1);

    }

    public static void main(String[] args) throws FileNotFoundException {

        new Apriori().run();
    }

    public void apriori(ArrayList<String> temp, int m) {

        System.out.println("S--------------------------" + (System.currentTimeMillis() - start) );
        Set<String> diff = null;

        if (m == 1) {
            diff = new HashSet<>();
        }

        for (int i = 0; i < temp.size(); i++) {
            if (map.get(temp.get(i)) < support) {

                if (m == 1) {
                    diff.add(temp.get(i));
                }

                temp.remove(i);
                i--;
            }
        }

        for (int i = 0; i < trans.size() && m == 1; i++) {

            for (String j : diff) {
                String rep = " " + j + " ";
                trans.get(i).replace(rep, " ");
            }

        }

        if (temp.size() == 0) {
            return;
        }

        System.out.println("Size " + m + " :");
        print(temp);

        ArrayList<String> ntemp = new ArrayList<>();

        int n = temp.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                StringTokenizer st1 = new StringTokenizer(temp.get(i), " ");
                StringTokenizer st2 = new StringTokenizer(temp.get(j), " ");

                String str1 = "", str2 = "";

                for (int s = 0; s < m - 2; s++) {
                    str1 = str1 + " " + st1.nextToken();
                    str2 = str2 + " " + st2.nextToken();
                }

                if (str2.compareToIgnoreCase(str1) == 0) {

                    int s1 = Integer.parseInt(st1.nextToken()), s2 = Integer.parseInt(st2.nextToken());
                    String s3;    
                        
                    if (s1 <= s2) {
                       s3 = (str1 + " " + s1 + " " + s2).trim();
                    } else {
                       s3 = (str1 + " " + s2 + " " + s1).trim();
                    }
                    
                    if(!ntemp.contains(s3)){
                        ntemp.add(s3);
                    }
                    
                }
            }
        }
        temp.clear();

        for (int j = 0; j < ntemp.size(); j++) {
            
            int c = 0;
            for (int i = 0; i < trans.size(); i++) {

                int check = 0;
               // String tr = trans.get(i);
                Set<Integer> st = trans1.get(i);
                StringTokenizer st1 = new StringTokenizer(ntemp.get(j)," ");
                
                while(st1.hasMoreElements()){
                    String str = st1.nextToken();
                    if(!st.contains(Integer.parseInt(str))){
                        check = 1;
                        break;
                    }
                }
                
                if(check == 0){
                    c= 1;
                    if (map.containsKey(ntemp.get(j))) {
                        int count = map.get(ntemp.get(j));
                        map.put(ntemp.get(j), count + 1);
                    } else {
                        map.put(ntemp.get(j), 1);
                    }
                }
            
            }
            if (c == 0) {
                ntemp.remove(j);
                j--;
            }
        }
System.out.println("E--------------------" + (System.currentTimeMillis() - start) );

        apriori(ntemp, m + 1);

    }
}
