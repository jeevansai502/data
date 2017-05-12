#include<stdio.h>

#include <stdlib.h>

int found = 0;


void fun(int cx,int cy,int cst){

if(arr[x][y] == 1){
    			
    			found = 1;
    			dx = x;
    			dy = y;
    			print(nd);
    			return;
    }



}

int main(void) {

	//create a NxN array

	int N, sX, sY, g1X,g1Y,g2X,g2Y,i,j,w;

	double p;

	float r;

	printf("Give N\n");

	scanf("%d",&N);

	printf("Give p\n");

	scanf("%lf",&p);

	printf("Give S x k y\n");

	scanf("%d",&sX);

	scanf("%d",&sY);

	printf("Give G1 x & y\n");

	scanf("%d",&g1X);

	scanf("%d",&g1Y);

	printf("Give G2 x & y\n");

	scanf("%d",&g2X);

	scanf("%d",&g2Y);

	int table[N][N];

	for(i=0; i<N; i++){

		for (j=0; j<N; j++){

			r=(float)(rand() % 10)/10; // [0,1)

			if (sX==i && sY==j){
				table[i][j]=1;
			}

			else if(g1X==i && g1Y==j){
				table[i][j]=2;
			}

			else if( g2X==i && g2Y==j){
				table[i][j]=3;
			}

			else if (p>=0 && r<=p){
				table[i][j]=9;
			}

			else{
				table[i][j]=0;
			}

			printf("%d ",table[i][j]);

		}

		printf("\n");

	}

	// Create the open list

	int cX=sX, cY=sY;

	while (cX!=g1X && cY!=g1Y)

	{

		int openList[4][2];

		//TOP

		if(cX>0 && table[cX-1][cY]!=9){

			openList[0][0]=(cX-1);

			openList[0][1]=cY;

		}

		else{

			openList[0][0]=-1;

			openList[0][1]=-1;

		}

		//BOTTOM

		if(cX+1<N && table[cX+1][cY]!=9 ){

			openList[1][0]=(cX+1);

			openList[1][1]=cY;

		}

		else{

			openList[1][0]=-1;

			openList[1][1]=-1;

		}

		//RIGHT

		if(cY+1<N && table[cX][cY+1]!=9){

			openList[2][0]=cX;

			openList[2][1]=(cY+1);

		}

		else{

			openList[2][0]=-1;

			openList[2][1]=-1;

		}

		//LEFT

		if(cY>0 && table[cX][cY-1]!=9){

			openList[3][0]=cX;

			openList[3][1]=(cY-1);

		}

		else{

			openList[3][0]=-1;

			openList[3][1]=-1;

		}

		printf("Open List of current cell:%d,%d\n",&cX, &cY);

		for (i=0;i<4;i++){

			printf("%d , %d\n",openList[i][0],openList[i][1]);

			cX=g1X; cY=g2Y;

		}

	}

	return 0;

} 
