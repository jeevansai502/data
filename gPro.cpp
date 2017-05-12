#include<bits/stdc++.h>
#include<GL/glut.h>
#include <GL/gl.h>
using namespace std;

void init2D(float r , float g , float b){

	glClearColor(r,g,b,0.0);
	glMatrixMode(GL_PROJECTION);
}

void display(){


	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(0.647059 ,  0.164706, 0.164706);

float x = -1,y = -1;



for(int i=0;i<5;i++){

	glBegin(GL_POLYGON);
	for(int j=0;j<3;j++){
		float x1 = x+j*0.25;
		float y1 = y+pow(-1,j+1)*j*0.25;
		glVertex2f(x1,y1);
		x = x1;
		y = y1;
	}
			
	glEnd();
	
}	


        glutSwapBuffers();
}

int main(int argc,char *argv[]){
	
	glutInit(&argc,argv);
	glutInitDisplayMode (GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize (500, 500);
	glutCreateWindow("gPro");
	init2D(0.0,0.0,0.0);
	glutDisplayFunc(display);
	glutMainLoop();	

	return 0;
}
