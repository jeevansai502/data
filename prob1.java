/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author placements2017
 */
import java.util.*;
import java.lang.*;
import java.io.*;
import javafx.util.*;



public class prob1 {

	static Scanner sc;
	static int n,t,count,count_p=-1,found=0;
	static Stack<Integer> pa,pb,pc;
	static Map<Integer , Integer> map;


	static void fun(int size){


		while(pc.size() != n){

			if(count_p == count){
				found = -1;
				break;
			}else{
				count_p = count;
			}


			for(int i=0;i<3;i++){

				for(int j=1;j<=2;j++){

					if(i==0){

						if(j==1){

							while(pa.size()!=0){

								int cmp1 = pa.peek(); 
								int cmp2;

								if(pb.size() != 0){
									cmp2 = pb.peek();

									if(map.get(cmp2) >= cmp1){
										pa.pop();
										pb.push(cmp1);
										count++;
							//			System.out.println("A->B");
									}else{

										break;
									}  

								}else{
							//		System.out.println("A->B");                           
									pa.pop();
									pb.push(cmp1);
									count++;
								}


							}
						}
						if(j==2){
							while(pa.size()!=0){
								int cmp1 = pa.peek();
								int cmp2;

								if(pc.size() != 0){
									cmp2 = pc.peek();

									if(map.get(cmp2) >= cmp1){
										pa.pop();
										pc.push(cmp1);
										count++;
							//			System.out.println("A->C");                               
									}else{
										break;
									}

								}else{
							//		System.out.println("A->C");                        
									pa.pop();
									pc.push(cmp1);
									count++;
								}
							}

						}

					}

					if(i==1){
						if(j==1){
							while(pb.size()!=0){
								int cmp1 = pb.peek();
								int cmp2;
								if(pc.size() != 0){
									cmp2 = pc.peek();

									if(map.get(cmp2) >= cmp1){
										pb.pop();
										pc.push(cmp1);
										count++;
						//				System.out.println("B->C");                    
									}else{
										break;
									}
								}else{
						//			System.out.println("B->C");                      
									pb.pop();
									pc.push(cmp1);
									count++;
								}
							}

						}
						if(j==2){
							while(pb.size()!=0){
								int cmp1 = pb.peek();
								int cmp2;

								if(pa.size() != 0){
									cmp2 = pa.peek();

									if(map.get(cmp2) >= cmp1){
							//			System.out.println("B->A");                                      
										pb.pop();
										pa.push(cmp1);
										count++;
									}else{
										break;
									}

								}else{
							//		System.out.println("B->A");                                  
									pb.pop();
									pa.push(cmp1);     
									count++;
								}
							}
						}



					}
					if(i==2){

						if(j==1){
							while(pc.size()!=0){
								int cmp1 = pc.peek();
								int cmp2;

								if(pa.size() != 0){
									cmp2 = pa.peek();

									if(map.get(cmp2) >= cmp1){
								//		System.out.println("C->A");                            
										pc.pop();
										pa.push(cmp1);
										count++;
									}else{
										break;
									}

								}else{
								//	System.out.println("C->A");                           
									pc.pop();
									pa.push(cmp1); 
									count++;
								}
							}


						}
						if(j==2){
							while(pc.size()!=0){
								int cmp1 = pc.peek();
								int cmp2;

								if(pb.size()!=0){
									cmp2 = pb.peek();

									if(map.get(cmp2) >= cmp1){
								//		System.out.println("C->B");               
										pc.pop();
										pb.push(cmp1);
										count++;
									}else{
										break;
									}
								}else{
								//	System.out.println("C->B");          
									pc.pop();
									pb.push(cmp1);
									count++;
								}
							}

						}

					}

				}


			}


		}

		if(size == n){
			found =1;
			System.out.println(count);
			return;
		}

		if(found == -1){

		}

	}      


	public static void main (String[] args) throws java.lang.Exception{

		sc = new Scanner(System.in);
		t = sc.nextInt();

		for(int i=0;i<t;i++){

			count = 0;
			n = sc.nextInt();

			pa = new Stack<>(); 
			pb = new Stack<>(); 
			pc = new Stack<>(); 

			map = new HashMap();

			for(int j=0;j<n;j++){

				int wei,lo;
				String rod;

				wei = sc.nextInt();
				lo = sc.nextInt();
				rod = sc.next();

				map.put(wei, lo);

				if(rod.equals("A")){

					pa.push(wei);
				}else if(rod.equals("B")){

					pb.push(wei);
				}else{

					pc.push(wei);
				}

			}

			fun(pc.size());

		}

	}

}
