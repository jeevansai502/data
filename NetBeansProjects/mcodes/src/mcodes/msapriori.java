/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

import java.util.*;
import java.io.*;

public class msapriori {

    ArrayList<Set<Integer>> trans;
    Map<Set<Integer>, Integer> map;
    Map<Set<Integer>, Double> minSup;
    long start;

    void print(ArrayList<Set<Integer>> temp) {

        for (int i = 0; i < temp.size(); i++) {
                System.out.println(temp.get(i));
        }
        System.out.println("Count :" + temp.size());
    }

    int aprioriCheck(Set<Integer> set, ArrayList<Set<Integer>> temp, int m) {

        for (int i : set) {
            Set<Integer> sub = new HashSet<>();
            sub.addAll(set);
            sub.remove(i);

            if (!temp.contains(sub)) {
                return 0;
            }
        }
        return 1;

    }
    
    void calSup(Set<Integer> temp){
     
        double min=10;
        for(int i:temp){
            
            Set<Integer> s = new HashSet<>();
            s.add(i);
            double d = minSup.get(s);
            if(d < min){
                min = d; 
            }
        }
        minSup.put(temp, min);
    }

    void apriori(ArrayList<Set<Integer>> temp, int m) throws FileNotFoundException {

        int n;
        Set<Integer> diff = null;

        n = temp.size();

        if (m == 1) {
            diff = new HashSet<>();
        }

        for (int i = 0; i < temp.size(); i++) {
            if (map.get(temp.get(i)) < minSup.get(temp.get(i))) {

                if (m == 1) {
                    diff.addAll(temp.get(i));
                }

                temp.remove(i);
                i--;
            }
        }

        for (int i = 0; i < trans.size() && m == 1; i++) {
            trans.get(i).removeAll(diff);
        }

        if (temp.size() == 0) {
            return;
        }

        System.out.println("Size " + m + " :");
        print(temp);

        ArrayList<Set<Integer>> ntemp = new ArrayList<>();

        n = temp.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Set<Integer> nset = new HashSet<>();
                nset.addAll(temp.get(i));

                int k = temp.get(j).size();

                nset.addAll(temp.get(j));

                if (nset.size() != m + 1 || ntemp.contains(nset)) {
                    continue;
                }

                int check = aprioriCheck(nset, temp, m);

                if (check == 1) {
                    calSup(nset);
                    ntemp.add(nset);
                }

            }
        }
        //  System.out.println("S--------------------------" + (System.currentTimeMillis() - start) );

        for (int j = 0; j < ntemp.size(); j++) {
            int check = 0;
            for (int i = 0; i < trans.size(); i++) {

                if ((trans.get(i)).containsAll(ntemp.get(j))) {

                    check = 1;
                    if (map.containsKey(ntemp.get(j))) {
                        int count = map.get(ntemp.get(j));
                        map.put(ntemp.get(j), count + 1);
                    } else {
                        map.put(ntemp.get(j), 1);
                    }
                }
            }
            if (check == 0) {
                ntemp.remove(j);
                j--;
            }
        }

        //    System.out.println("E--------------------" + (System.currentTimeMillis() - start) );
        apriori(ntemp, m + 1);

    }

    void readSupport(Scanner sc,int lines) throws FileNotFoundException {
        String minsupport;

        System.out.println("Enter support file name");
        sc = new Scanner(System.in);
        minsupport = sc.next();
        sc = new Scanner(new File(minsupport));
        minSup = new HashMap<>();
        
            while (sc.hasNextLine()) {
                    
                String s = sc.nextLine();
                String[] str = s.split("\\s+");
                int val = Integer.parseInt(str[0]);
                double sup = Double.parseDouble(str[1]);
                Set<Integer> st = new HashSet<>();
                st.add(val);
                
                
                minSup.put(st, (sup*lines)/100);
                
            }        
    }

    void run() throws FileNotFoundException {

        start = System.currentTimeMillis();

        trans = new ArrayList<>();
        ArrayList<Set<Integer>> temp = new ArrayList<>();
        map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
     
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
            Set<Integer> set1 = new HashSet<>();
            for (int i = 0; i < spl.length; i++) {
                Set<Integer> set2 = new HashSet<>();
                int n = Integer.parseInt(spl[i]);

                set1.add(n);
                set2.add(n);

                if (map.containsKey(set2)) {
                    int count = map.get(set2);
                    map.put(set2, count + 1);
                } else {
                    map.put(set2, 1);
                    temp.add(set2);
                }
            }

            trans.add(set1);
        }
       
           readSupport(sc,lines);
//System.out.println(System.currentTimeMillis() - start);
        apriori(temp, 1);
    }

    public static void main(String[] args) throws FileNotFoundException {

        new msapriori().run();
    }
}
