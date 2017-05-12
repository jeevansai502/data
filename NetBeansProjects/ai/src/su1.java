import java.io.*;
import java.util.*;

class su1 {

	static Set<Integer> set;
	static Map<Integer , ArrayList<Integer>> map;
	static ArrayList<ArrayList<Integer>> arr;
	static int n;
	static int found = 0;

	static void printArr(ArrayList<ArrayList<Integer>>  a){

		for(int i=0;i<n*n;i++){
			for(int j=0;j<n*n;j++){
				System.out.print(a.get(i).get(j) + " ");	
			}
			System.out.println();
		}

	}

	static int get(int r,int c,int val,ArrayList<ArrayList<Integer>> a){

		//int b = r/n + c/n;

		//return b;
		int s1 = r/n , s2 = (r+n)/n;
		int e1 = c/n , e2 = (c+n)/n; 
		for(int i=s1*n;i<s2*n;i++){
			for(int j=e1*n;j<e2*n;j++){

				if(a.get(i).get(j) == val)
					return 0;
			}       
		}


		return 1;
	} 

	static int check(int r,int c,int val,ArrayList<ArrayList<Integer>>  a){

		if(a.get(r).contains(val)){
			return 0;
		}


		for(int i=0;i<n*n;i++){
			if(a.get(i).get(c) == val){
				return 0;
			}
		}

		int b = get(r,c,val,a);


		if(b == 0){
			return 0;
		}


		return 1;
	}


	static int fc(ArrayList<ArrayList<Integer>>  a){

		int che = 0;
		for(int i=0;i<n*n;i++){
			for(int j=0;j<n*n;j++){
				if(a.get(i).get(j) == 0){
					che = 1;
					break;
				}
			}
		}

		if(che == 0){

			found = 1;
			return 1;
		}

		return 0;
	}
        	static ArrayList<Integer> get(int i,int r,int c){
            		
            		ArrayList<Integer> al = new ArrayList<Integer>();
            		
            		switch(i){
            			
            			case 0:
            				al.add(r);	
            				al.add(c+1);
            				return al;
            			case 1:
            				al.add(r-1);	
            				al.add(c+1);
            				return al;
            			case 2:
            				al.add(r-1);	
            				al.add(c);
            				return al;
            			case 3:
            				al.add(r-1);	
            				al.add(c-1);
            				return al;
            			case 4:
            				al.add(r);	
            				al.add(c-1);
            				return al;
            			case 5:
            				al.add(r+1);	
            				al.add(c-1);
            				return al;
            			case 6:
            				al.add(r+1);	
            				al.add(c);
            				return al;
            			case 7:
            				al.add(r+1);	
            				al.add(c+1);
            				return al;
            			
            		}
            		return al;		
            	}
        
          	static int degreeH(int r1,int c1,int r2,int c2,ArrayList<ArrayList<Integer>> a){
            		
            		int count1 = 0,count2 = 0;
           			for(int i=0;i<8;i++){
           				ArrayList<Integer> al = get(i,r1,c1);
            			int r = al.get(0);
            			int c = al.get(1);
           				
           				if(r < n && c < n && r>=0 && c>=0){
           					if(a.get(r).get(c) == 0)
           						count1++;
           				}
           			}
           			
           			for(int i=0;i<8;i++){
           				ArrayList<Integer> al = get(i,r2,c2);
            			int r = al.get(0);
            			int c = al.get(1);
           				if(r < n && c < n && r>=0 && c>=0){
           					if(a.get(r).get(c)  == -1)
           						count2++;
           				}
           			}
           			
           			if(count1 > count2){
           				return 0; 
           				
           			}else if(count1 == count2){
           				if(r1 < r2){
           					return 0;
           				}else if(r1 == r2 && c1 < c2){
           					return 0;
           				}
           			}
           			return 1;
        			 
            	}
        
        
        
        static ArrayList<Integer> mrv(ArrayList<ArrayList<Integer>> a){
       
       			int min=1000000000,r=-1,c=-1; 
       			
       			ArrayList<Integer> al = new ArrayList<Integer>();
        		
        		for(int i=0;i<n*n;i++){
        			for(int j=0;j<n*n;j++){
        				if(a.get(i).get(j) == 0){
        					
        					int count = 0;
        					for(int k=0;k<9;k++){
        						int chec = check(i,j,k+1,a);
        						if(chec == 1)
        							count++;	
                                                }
        					        					
        					if(count < min){
        						min = count;
        						r = i;
        						c = j;
        					}else if(count == min){
        						
            							int deg = degreeH(r,c,i,j,a);
            						
            							if(deg == 1){
            								r = i;
            								c = j;
            							}
        					}
     
        				}
           			}
        		}
        		
        		al.add(r);
        		al.add(c);
        		return al;
        			
        	}
        
        
        

	static void arrange(ArrayList<ArrayList<Integer>> a,int p,int q){

		found = fc(a);
		if(found == 1){
			printArr(a);
			return;
		}

		for(int k=0;k<n*n && found == 0;k++){


			int chec = check(p,q,k+1,a);

			if(chec == 0){
				continue;
			}

			ArrayList<ArrayList<Integer>> ac = new ArrayList<ArrayList<Integer>>();            


			for(int p1=0;p1<n*n;p1++){
				ArrayList<Integer> al = new ArrayList<Integer>();
				for(int q1=0;q1<n*n;q1++){
					al.add(a.get(p1).get(q1));
				}
				ac.add(al);
			}

			ac.get(p).set(q,k+1);

			int p1=0,q1=0;
			for( p1=0;p1<n*n;p1++){
				for( q1=0;q1<n*n;q1++){
					if(ac.get(p1).get(q1) == 0)
						break;
				}
				if(q1 != n*n)
					break;
			}   

                        ArrayList<Integer> ind = mrv(ac);
                        if(found == 0)
                        arrange(ac,ind.get(0),ind.get(1));
			
		}

	}

	public static void main(String args[] ) throws Exception {

		Scanner sc = new Scanner(System.in);

		int t;

		t = sc.nextInt();

		for(int i=0;i<t;i++){
			found = 0;

			n = sc.nextInt();

			arr = new  ArrayList<ArrayList<Integer>>();

			for(int j=0;j<n*n;j++){

				ArrayList<Integer> al = new ArrayList<Integer>();
				for(int k=0;k<n*n;k++){

					int val = sc.nextInt();
					al.add(val);

				}
				arr.add(al);
			}
			int p,q=0;
	

			ArrayList<Integer> ind = mrv(arr);
                        if(found == 0)
                        arrange(arr,ind.get(0),ind.get(1));
                        
                        
			if(found == 0)
				System.out.println("-1");

		}
	}
	}
