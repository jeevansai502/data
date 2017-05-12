/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prob2;

/**
 *
 * @author placements2017
 */
import java.util.*;
import java.lang.*;
import java.io.*;
import javafx.util.*;

class stac implements Comparable<stac>{
	Stack<Integer> pa,pb,pc;

	stac(Stack<Integer> pa,Stack<Integer> pb,Stack<Integer> pc){
		this.pa = pa;
		this.pb = pb;
		this.pc =pc;  
	}

    @Override
    public int compareTo(stac o) {
         if(pa.equals(o.pa) && pb.equals(o.pb) && pc.equals(o.pc))
        return 1;
        else
            return -1;
       
    }

}

public class prob2 {
	static Scanner sc;
	static int n,t,count,count_p=-1,found=0;
	static Stack<Integer> pao,pbo,pco;
	static Map<Integer , Integer> map;
	static Map<stac,stac> map1;
	static PriorityQueue<stac> p;


static void print(stac st){

if(map1.containsKey(st)){
count++;
print(map1.get(st));
}
}


	static void fun(stac st){

		Stack<Integer> pa1,pb1,pc1;
pa1 = st.pa;
pb1 = st.pb;
pc1 = st.pc;


if(pa1.size() == n ||pb1.size() == n||pc1.size() == n){
found =1;
print(st);
}

		for(int i=0;i<3;i++){

			for(int j=1;j<=2;j++){

				Stack<Integer> pa = (Stack<Integer>) pa1.clone();
				Stack<Integer> pb = (Stack<Integer>) pb1.clone();
				Stack<Integer> pc = (Stack<Integer>) pc1.clone();

				if(i==0 && pa.size()!=0){

					if(j==1){
						int x=0;
						int cmp1 = pa.peek(); 
						int cmp2;

						if(pb.size() != 0){
							cmp2 = pb.peek();

							if(map.get(cmp2) >= cmp1){
								pa.pop();
								pb.push(cmp1);
								count++;
								System.out.println("A->B");
							}else{
								x=1;

							}  

						}else{
							System.out.println("A->B");                           
							pa.pop();
							pb.push(cmp1);
							count++;
						}
						if(x!=1){
							stac s = new stac(pa,pb,pc);       
							p.add(s);
							map1.put(s, st);
						}							
					}
					if(j==2){
						int x=0;

						int cmp1 = pa.peek();
						int cmp2;

						if(pc.size() != 0){
							cmp2 = pc.peek();

							if(map.get(cmp2) >= cmp1){
								pa.pop();
								pc.push(cmp1);
								count++;
								System.out.println("A->C");                               
							}else{
								x=1;
							}

						}else{
							System.out.println("A->C");                        
							pa.pop();
							pc.push(cmp1);
							count++;
						}

		stac s = new stac(pa,pb,pc);       
						if(x!=1 && !map1.containsKey(s)){
					
							p.add(s);
							map1.put(s, st);
						}
					}

				}

				if(i==1 && pb.size()!=0){
					if(j==1){
						int x=0;
						int cmp1 = pb.peek();
						int cmp2;
						if(pc.size() != 0){
							cmp2 = pc.peek();

							if(map.get(cmp2) >= cmp1){
								pb.pop();
								pc.push(cmp1);
								count++;
								System.out.println("B->C");                    
							}else{
								x=1;
							}
						}else{
							System.out.println("B->C");                      
							pb.pop();
							pc.push(cmp1);
							count++;
						}

stac s = new stac(pa,pb,pc);     
						if(x!=1 && !map1.containsKey(s)){
							  
							p.add(s);
							map1.put(s, st);
						}
					}
					if(j==2){
						int x=0;

						int cmp1 = pb.peek();
						int cmp2;

						if(pa.size() != 0){
							cmp2 = pa.peek();

							if(map.get(cmp2) >= cmp1){
								System.out.println("B->A");                                      
								pb.pop();
								pa.push(cmp1);
								count++;
							}else{
								x=1;
							}

						}else{
							System.out.println("B->A");                                  
							pb.pop();
							pa.push(cmp1);     
							count++;
						}

	stac s = new stac(pa,pb,pc);
						if(x!=1 && !map1.containsKey(s)){
						       
							p.add(s);
							map1.put(s, st);
						}


					}
                                }
					if(i==2 && pc.size()!=0){

						if(j==1){
							int x=0;
							int cmp1 = pc.peek();
							int cmp2;

							if(pa.size() != 0){
								cmp2 = pa.peek();

								if(map.get(cmp2) >= cmp1){
									System.out.println("C->A");                            
									pc.pop();
									pa.push(cmp1);
									count++;
								}else{
									x=1;
								}

							}else{
								System.out.println("C->A");                           
								pc.pop();
								pa.push(cmp1); 
								count++;
							}
						stac s = new stac(pa,pb,pc); 
                                                        if(x!=1 && !map1.containsKey(s)){
							      
								p.add(s);
								map1.put(s, st);
							}



						}

						if(j==2){
							int x=0;
							int cmp1 = pc.peek();
							int cmp2;

							if(pb.size()!=0){
								cmp2 = pb.peek();

								if(map.get(cmp2) >= cmp1){
									System.out.println("C->B");               
									pc.pop();
									pb.push(cmp1);
									count++;
								}else{
									x=1;
								}
							}else{
								System.out.println("C->B");          
								pc.pop();
								pb.push(cmp1);
								count++;
							}
                                                        	stac s = new stac(pa,pb,pc); 
							if(x!=1 && !map1.containsKey(s)){
							      
								p.add(s);
								map1.put(s, st);
							}

						}

				}

                        }
                }

if(p.size()!=0 && found==0){
stac s = p.poll();
fun(s);
}

			}

			public static void main (String[] args) throws java.lang.Exception{

                            sc = new Scanner(System.in);
		t = sc.nextInt();
				for(int i=0;i<t;i++){

					count = 0;
					n = sc.nextInt();

					pao = new Stack<>(); 
					pbo = new Stack<>(); 
					pco = new Stack<>(); 


					map = new HashMap();
					map1 = new HashMap();
					p = new PriorityQueue();

					for(int j=0;j<n;j++){

						int wei,lo;
						String rod;

						wei = sc.nextInt();
						lo = sc.nextInt();
						rod = sc.next();

						map.put(wei, lo);

						if(rod.equals("A")){

							pao.push(wei);
						}else if(rod.equals("B")){

							pbo.push(wei);
						}else{

							pco.push(wei);
						}



					}

					stac st = new stac(pao,pbo,pco);


					fun(st);
System.out.println(count);

				}



			}
		}
