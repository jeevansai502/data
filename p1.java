import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;
import java.awt.*;
public class p1{

static int[][] cx,cy,tx,ty;
static int tminx,tminy,tmaxx,tmaxy;
static int cminx,cminy,cmaxx,cmaxy;

static Polygon[] tpoly,cpoly; 


static void check(int x,int y){

		for(int i=0;i<3;i++){
			tpoly[i].contains(x,y);
			System.out.println("IEEE");
			return;
		}

		for(int i=0;i<3;i++){
			cpoly[i].contains(x,y);
			System.out.println("RAS");
			return;
		}
		System.out.println("ACM");

	}




	public static void main(String args[]){

		Scanner sc = new Scanner(System.in);

		tx = new int[3][4];
		ty = new int[3][4];
		cx = new int[3][4];
		cy = new int[3][4];
		tpoly = new Polygon[3];
		cpoly = new Polygon[3];

		for(int j=0;j<3;j++){
			for(int i=0;i<4;i++){
				tx[j][i] = sc.nextInt();
				ty[j][i] = sc.nextInt();

			}
		}

		for(int j=0;j<3;j++){
tpoly[j] = new Polygon();
			for (int i = 0; i < 4; i++) {
				tpoly[j].addPoint(tx[j][i], ty[j][i]);
			}
		}

		for(int j=0;j<3;j++){
cpoly[j] = new Polygon();		
	for(int i=0;i<4;i++){
				cx[j][i] = sc.nextInt();
				cy[j][i] = sc.nextInt();
			}
		}

		for(int j=0;j<3;j++){
			for (int i = 0; i < 4; i++) {
				cpoly[j].addPoint(cx[j][i], cy[j][i]);
			}
		}

		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("/home/jeevansai/p1.txt"));
			while ((sCurrentLine = br.readLine()) != null) {
				String str[] = sCurrentLine.split(",");
				int x1 = Integer.parseInt(str[0]);
				int y1 = Integer.parseInt(str[1]);
				check(x1,y1);
			}     

		}catch(Exception e){
			e.printStackTrace();
		}


	}
}
