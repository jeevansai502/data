            import java.io.*;
            import java.util.*;
             
            class TestClass {
            	
            	static Set<Integer> set;
            	static Map<Integer , ArrayList<Integer>> map;
            	static int[][] arr;
                static int m,n;
                static int found = 0;
             
            	static void printArr(){
            		
            		for(int i=0;i<m;i++){
            			for(int j=0;j<n;j++){
            				String s = String.valueOf(arr[i][j]);
            				int l = s.length();
            					for( int k = 0;k <  3 - l ; k++){
            						s = '0' + s;
            					}
            					s = "iit2014" + s;
            					System.out.print(s + " ");	
            			}
            			System.out.println();
            		}
            				
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
             
            	static int check(int r,int c,int val){
            		
            		for(int i=0;i<8;i++){
            			
            			ArrayList<Integer> al = get(i,r,c);
            			int r1 = al.get(0);
            			int c1 = al.get(1);
            			if(r1 < m && c1 < n && r1>=0 && c1>=0){
            				if(arr[r1][c1] != -1){
            					if(!map.get(val).contains(arr[r1][c1]))
            						return 0;
            				}
            			}
            		}
            		return 1;
            		
            	}
            	
            	static int degreeH(int r1,int c1,int r2,int c2){
            		
            		int count1 = 0,count2 = 0;
           			for(int i=0;i<8;i++){
           				ArrayList<Integer> al = get(i,r1,c1);
            			int r = al.get(0);
            			int c = al.get(1);
           				
           				if(r < m && c < n && r>=0 && c>=0){
           					if(arr[r][c] == -1)
           						count1++;
           				}
           			}
           			
           			for(int i=0;i<8;i++){
           				ArrayList<Integer> al = get(i,r2,c2);
            			int r = al.get(0);
            			int c = al.get(1);
           				if(r < m && c < n && r>=0 && c>=0){
           					if(arr[r][c] == -1)
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
            	
               	static ArrayList<Integer> mrv(Set<Integer> s1){
           
           			int min=1000000000,r=-1,c=-1; 
           			
           				ArrayList<Integer> al = new ArrayList<Integer>();
            		
            			for(int i=0;i<m;i++){
            				for(int j=0;j<n;j++){
            					if(arr[i][j] == -1){
            					
            						int count = 0;
            						for(int s : s1){
            							int chec = check(i,j,s);
            							if(chec == 1)
            								count++;	
            						}
            					
            						if(count < min){
            							min = count;
            							r = i;
            							c = j;
            						}else if(count == min){
            						
            							int deg = degreeH(r,c,i,j);
            						
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
            	
           	
            	static void arrange(int r,int c,Set<Integer> s){
            		
            		ArrayList<Integer> al = new ArrayList<Integer>(s);
            		Collections.sort(al);
             
            		if(found == 1)
            			return;
            		
            		for(int i=0;i<al.size() && found == 0;i++){
             
            			int chec = check(r,c,al.get(i));
            			
            			if(chec == 0){
            				continue;
            			}
            			
            			if(s.size() == 1){
            					arr[r][c] = al.get(i);
            					printArr();
            					found = 1;
            					return;
            			}
            			
            			Set<Integer> s1 = new TreeSet<Integer>();
            			s1.addAll(s);
            			s1.remove(al.get(i));
            				
            				arr[r][c] = al.get(i);	
            			
            					ArrayList<Integer> ind = mrv(s1);
            					arrange(ind.get(0),ind.get(1),s1);
            			
            			if(found == 0){
            				arr[r][c] = -1;
            			}
            		}
            		
            	}
            	
                public static void main(String args[] ) throws Exception {
                  
                  Scanner sc = new Scanner(System.in);
                  
                  int t;
                  
                  t = sc.nextInt();
                  
                  for(int i=0;i<t;i++){
                  	
                  	found = 0;
                  	set = new TreeSet<Integer>();
                  	map = new HashMap<Integer , ArrayList<Integer>>();
                  	
                  	m = sc.nextInt();
                  	n = sc.nextInt();
                  	
                  	arr = new int[m][n];
                  	
                  	for(int[] row:arr )
                  		Arrays.fill(row,-1);
                  	
                  	for(int j=0;j<m*n;j++){
                  		
                  		ArrayList<Integer> al = new ArrayList<Integer>();
                  		int r1 = Integer.parseInt(sc.next().substring(7,10));
                  		set.add(r1);
                  		
                  		int c = sc.nextInt();
                  		
                  			for(int k=0;k<c;k++){
                  			
                  				int r2 = Integer.parseInt(sc.next().substring(7,10));
                  				set.add(r2);
                  				al.add(r2);
                  			}
                  		
                  			map.put(r1 , al);
                  		
                  		}
                  		
                  		ArrayList<Integer> ind = mrv(set);
                  		 int in1 = ind.get(0);
                  		 int in2 = ind.get(1);
                  		arrange(in1,in2,set);
                  	}
                }
            }
