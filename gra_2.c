#include<GL/glut.h>

void init2D(float r , float g , float b){

	glClearColor(r,g,b,0.0);
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(0.0,200.0,0.0,150.0);

}

void display(){

	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0, 0.0, 0.0);

	glBegin(GL_POLYGON);
	glVertex



	glEnd();

}

void main(int argc,char *argv[]){

	glutInit(&argc,argv);
	glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB);
	glutInitWindowSize (500, 500);
	glutInitWindowPosition (100, 100);
	glutCreateWindow("problem 1");
	init2D(0.75,0.75,0.75);	
	glutDisplayFunc(display);
	glutMainLoop();


}

