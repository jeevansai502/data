    import java.io.*;
    import java.util.*;
    import java.lang.*;
    import java.text.*;
    import java.math.*;
     
    class node implements Comparable<node>{
    	
    	int x;
    	int y;	
     
    	node(int x,int y){
    		this.x = x;
    		this.y = y;	
    	}
    	
    	public int compareTo(node o){
    		
    		if(Ideone.cost[o.x][o.y] == Ideone.cost[x][y])
    			return 1;
    		else{
    			int d=Ideone.cost[x][y] - Ideone.cost[o.x][o.y];
    			if(d >0)
    			return 1;
    			else 
    			return -1;
    		}
    	}
    	
    }
     
    class Ideone {
    	
    	Scanner sc;
    	static int[][] arr;
    	static int sx,sy,found = 0,m,n,t,dx,dy;
    	static PriorityQueue<node> p;
    	static Map<node,node> map;
    	public static int[][] cost;
    	
    	static void print(node nd){
    	
    			if(map.containsKey(nd)){
    				nd = map.get(nd);
    				print(nd);
    					System.out.print(nd.x + " " + nd.y + " ");
    			}
    	}
    	
    	static void fun(node nd,int cst){
    	  		
    	  	int x,y;
    	    
    	        x = nd.x;
    	        y = nd.y;
    	        
    		if(arr[x][y] == 2){
    			
    			found = 1;
    			dx = x;
    			dy = y;
    			print(nd);
    			return;
    		}
    		
     
    		if(y + 1 < n ){
    			if(arr[x][y+1] != 9){
    				node n1 = new node(x,y+1);
    			
    				if(cost[x][y+1] == -1 || cost[x][y+1] > cst + 10){
    					cost[x][y+1] = cst + 10;
    					p.add(n1);
    					map.put(n1,nd);
    				}
    			}
    		}
    		
		
    		
    		if(x + 1 < m){
        			if(arr[x+1][y] != 9){
        				node n1 = new node(x+1,y);
        				
        				if(cost[x+1][y] == -1 || cost[x+1][y] > cst + 5){
        					cost[x+1][y] = cst + 5;
        					p.add(n1);
        					map.put(n1,nd);
        				}    				
        			}
        		}

    		if(y - 1 >= 0 ){
           			if(arr[x][y-1] != 9){
           				node n1 = new node(x,y-1);
           				
           				if(cost[x][y-1] == -1 || cost[x][y-1] > cst + 10){
           					cost[x][y-1] = cst + 10;
           					p.add(n1);
           					map.put(n1,nd);       				
           				}
           			}
           		}
    	

           		
           		if(x - 1 >= 0 ){
           			if(arr[x-1][y] != 9){
           				node n1 = new node(x-1,y);
           				
           				if(cost[x-1][y] == -1 || cost[x-1][y] > cst + 5){ 
           					cost[x-1][y] = cst + 5;
           					p.add(n1);
           					map.put(n1,nd);  
           				}     				
           			}
           		}

    	
           	node no = p.poll();	
        	fun(no,cost[no.x][no.y]);
        	
        	
           			if(found == 1){
          				return;
        			}
           					
           		}
    		
    	public static void main(String args[] ) throws Exception {
            
        	Scanner sc = new Scanner(System.in);
            
            	t = sc.nextInt();
        	    
            	for(int i=0;i<t;i++){
            	
            		m = sc.nextInt();
            		n = sc.nextInt();
     
    			arr = new int[m][n];
    			cost = new int[m][n];
     
    			for(int j=0;j<m;j++){
            			for(int k=0;k<n;k++){
            				arr[j][k] = sc.nextInt();	
            			}
            		}
            		
            		for(int[] row: cost)
            			Arrays.fill(row,-1);
            	
        		sx = sc.nextInt();
            		sy = sc.nextInt();
            		
            		if(arr[sx][sy] == 1){
            			found = 1;
            			System.out.println(sx + " " + sy + " ");
            			return;
            		}
            		
            		cost[sx][sy] = 0;
    			
    			p = new PriorityQueue<node>();
    			map = new HashMap<>();
    			
    			fun(new node(sx,sy),0);
    			
    			System.out.println(dx + " " + dy + " ");
    		}
    	}
    }	
