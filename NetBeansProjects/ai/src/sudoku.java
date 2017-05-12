import java.io.*;
import java.util.*;

class sudoku {

	static Set<Integer> set;
	static Map<Integer , ArrayList<Integer>> map;
	static int[][] arr;
	static int n;
	static int found = 0;

	static void printArr(int a[][]){

		for(int i=0;i<n*n;i++){
			for(int j=0;j<n*n;j++){
				System.out.print(a[i][j] + " ");	
			}
			System.out.println();
		}

	}

	static int get(int r,int c,int val,int a[][]){

		//int b = r/n + c/n;

		//return b;
                int s1 = r/n , s2 = (r+n)/n;
                int e1 = c/n , e2 = (c+n)/n; 
                for(int i=s1*n;i<s2*n;i++){
                    for(int j=e1*n;j<e2*n;j++){
                     
                //      	System.out.println(i + " " + j + " " + val + " " + arr[r][i]);

                        
                        if(a[i][j] == val)
                        return 0;
                    }       
                }
                
                
            return 1;
	} 

	static int check(int r,int c,int val,int a[][]){

//printArr(a);
//System.out.println(r + " " + c +" " + val);
            
		for(int i=0;i<n*n;i++){
               //     System.out.println(r + " " + i + " " + val + " " + arr[r][i]);
			if(a[r][i] == val){
				return 0;
                        }
		}

		for(int i=0;i<n*n;i++){
		//	System.out.println(i + " " + c + " " + val + " " + arr[r][i]);
                    if(a[i][c] == val){
                            return 0;
                        }
		}

		int b = get(r,c,val,a);
               
               // System.out.println(r + " " + c +" " + val);

		if(b == 0){
			return 0;
		}

                
		return 1;
	}


	static int fc(int a[][]){

		int che = 0;
		for(int i=0;i<n*n;i++){
			for(int j=0;j<n*n;j++){
				if(a[i][j] == 0){
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

	static void arrange(int a[][]){
        //   printArr(a);
            
		found = fc(a);
		if(found == 1){
			printArr(a);
			return;
		}

		for(int i=0;i<n*n && found == 0;i++){

			for(int j=0;j<n*n && found == 0;j++){    

				if(a[i][j] != 0)
					continue;

				for(int k=0;k<n*n && found == 0;k++){


					int chec = check(i,j,k+1,a);

					if(chec == 0){
						continue;
					}

					int ac[][] = new int[n*n][n*n];            

					for(int p=0;p<n*n;p++){
						for(int q=0;q<n*n;q++){
							ac[p][q] = a[p][q];

						}
					}

					ac[i][j] = k+1;

                                        if(found == 0)
                                            arrange(ac);
				}

			}
		}
        

	}

	public static void main(String args[] ) throws Exception {

		Scanner sc = new Scanner(System.in);

		int t;

		t = sc.nextInt();

		for(int i=0;i<t;i++){

			found = 0;
		//	map = new HashMap<Integer , ArrayList<Integer>>();

			n = sc.nextInt();

			arr = new int[n*n][n*n];

			for(int[] row:arr )
				Arrays.fill(row,0);

			for(int j=0;j<n*n;j++){

				for(int k=0;k<n*n;k++){

					int val = sc.nextInt();

					arr[j][k] = val;

				}
			}

			int c = 0,s=0,e=0;
					arrange(arr);
                                        
                        if(found == 0)
                            System.out.println("-1");

		}
	}
}
