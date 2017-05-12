#include<GL/glut.h>

void init2D(float r , float g , float b){

	glClearColor(r,g,b,0.0);
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(0.0,200.0,0.0,150.0);

}

void display(){

	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.0, 0.0, 0.0);
	glBegin(GL_LINE_LOOP);
	glVertex2i(100,50);
	glVertex2i(150,75);
	glVertex2i(150,125);
	glVertex2i(100,150);
	glVertex2i(50,125);
	glVertex2i(50,75);		
	glEnd();	
	glPolygonMode(GL_FRONT_AND_BACK,GL_LINE);
	glBegin(GL_TRIANGLES);
	glVertex2i(100,50);
	glVertex2i(150,125);
	glVertex2i(50,125);
	glEnd();
	glBegin(GL_TRIANGLES);
	glVertex2i(150,75);
	glVertex2i(100,150);
	glVertex2i(50,75);
	glEnd();
	glBegin(GL_LINES);
	glVertex2i(100,50);
	glVertex2i(100,150);
	glEnd();
	glBegin(GL_LINES);
	glVertex2i(150,75);
	glVertex2i(50,125);
	glEnd();
	glBegin(GL_LINES);
	glVertex2i(150,125);
	glVertex2i(50,75);
	glEnd();


}

void main(int argc,char *argv[]){

	glutInit(&argc,argv);
	glutInitDisplayMode (GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize (500, 500);
	glutInitWindowPosition (100, 100);
	glutCreateWindow("problem 1");
	init2D(0.0,0.0,0.0);	
	glutDisplayFunc(display);
	glutMainLoop();


}

