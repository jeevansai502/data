/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcodes;

import java.util.*;
import java.io.*;

public class Apriori_p {

    double support;
    ArrayList<String> trans;
    Map<String, Integer> map;
    long start;

    void print(ArrayList<String> temp) {

        for (int i = 0; i < temp.size(); i++) {
            //       System.out.println(temp.get(i));
        }
        System.out.println("Count :" + temp.size());
    }

    void run() throws FileNotFoundException {

        trans = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter support %");
        support = sc.nextDouble();

        System.out.println("Enter file name");
        String file = sc.next();

        start = System.currentTimeMillis();

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
            String con = " ";
            for (int i = 0; i < elem.size(); i++) {
                con = con + elem.get(i) + " ";
                String s1 = String.valueOf(elem.get(i)).trim();
                if (!temp.contains(s1)) {
                    temp.add(s1);
                }
            }

            trans.add(con);
        }

        support = (support * lines) / 100;
        System.out.println(System.currentTimeMillis() - start);
        apriori(temp, 1);

    }

    public static void main(String[] args) throws FileNotFoundException {

        new Apriori_p().run();
    }

    public void apriori(ArrayList<String> temp, int m) {

        System.out.println("S--------------------------" + (System.currentTimeMillis() - start));
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

        ArrayList<List<String>> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> a = new ArrayList<>();
            String st1[] = temp.get(i).split("\\s+");
            a = Arrays.asList(st1);
            al.add(a);
        }

        if (m == 1) {

            for (int i = 0; i < n; i++) {
                List<String> st1 = al.get(i);
                int v1 = Integer.parseInt(st1.get(0));

                for (int j = i + 1; j < n; j++) {

                    List<String> st2 = al.get(j);

                    int v2 = Integer.parseInt(st2.get(0));

                    String v3;
                    if (v1 < v2) {
                        v3 = (v1 + " " + v2).trim();
                        if (!ntemp.contains(v3)) {
                            ntemp.add(v3);
                        }

                    } else if (v1 > v2) {
                        v3 = (v2 + " " + v1).trim();
                        if (!ntemp.contains(v3)) {
                            ntemp.add(v3);
                        }

                    }

                }

            }

        } else {

            for (int i = 0; i < n; i++) {
                List<String> st1 = al.get(i);
                for (int j = i + 1; j < n; j++) {

                    List<String> st2 = al.get(j);
                    String str1 = "", str2 = "";
                    int count = 0;

                    for (int s = 0; s < m - 2; s++) {

                        if (count > 1) {
                            break;
                        }
                        if (!st1.get(s).equals(st2.get(s))) {
                    //        System.out.println(st1.get(s) + " " + st2.get(s));
                            count++;

                            if (Integer.parseInt(st1.get(s)) <= Integer.parseInt(st2.get(s))) {
                                str1 = str1 + " " + st1.get(s) + " " + st2.get(s);
                            } else {
                                str1 = str1 + " " + st2.get(s) + " " + st1.get(s);
                            }
                            continue;
                        }

                        str1 = str1 + " " + st1.get(s);
                    }
//System.out.println(st1 + "--------" + st2 + "-------" + m);

                    int s1 = -1, s2 = -1;

                    s1 = Integer.parseInt(st1.get(m - 2));
                    s2 = Integer.parseInt(st2.get(m - 2));

                    if (s1 == s2 && count == 1) {

                        String s3 = (str1 + " " + s1).trim();
                        if (!ntemp.contains(s3)) {
                            ntemp.add(s3);
                        }

                    } else if (count == 0 && s1 != s2) {

                        String s3;

                        if (s1 < s2) {
                            s3 = (str1 + " " + s1 + " " + s2).trim();

                            if (!ntemp.contains(s3)) {
                                ntemp.add(s3);
                            }
                        } else if (s2 > s1) {
                            s3 = (str1 + " " + s2 + " " + s1).trim();

                            if (!ntemp.contains(s3)) {
                                ntemp.add(s3);
                            }
                        }

                    }
                }
            }

        }
        temp.clear();

        ArrayList<List<String>> a1 = new ArrayList<>();

        n = ntemp.size();
        for (int i = 0; i < n; i++) {
            List<String> a = new ArrayList<>();
            String st1[] = ntemp.get(i).split("\\s+");
            a = Arrays.asList(st1);
            a1.add(a);
        }

        for (int j = 0; j < n; j++) {

            int c = 0;
            List<String> a = a1.get(j);
            for (int i = 0; i < trans.size(); i++) {

                int check = 0;
                String tr = trans.get(i);

                for (int k = 0; k < a.size(); k++) {
                    String str = a.get(k);
                    if (!tr.contains(" " + str + " ")) {
                        check = 1;
                        break;
                    }
                }

                if (check == 0) {
                    c = 1;
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
        System.out.println("E--------------------" + (System.currentTimeMillis() - start));

        apriori(ntemp, m + 1);

    }
}
