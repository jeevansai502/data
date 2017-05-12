#include<GL/glut.h>
#include<GL/gl.h>

void display(){
 glClear( GL_COLOR_BUFFER_BIT); 
glColor3f(1.0,0.0,0.0);
glBegin(GL_POLYGON);
glVertex3f(-0.5,-0.5,1.0);
glVertex3f(-0.5,0.5,1.0);
glVertex3f(0.5,0.5,-1.0);
glVertex3f(0.5,-0.5,-1.0);
glEnd();
glFlush();
}

int main(int argc,char *argv[]){

glutInit(&argc,argv);
glutInitWindowSize(300,300);
glutInitDisplayMode ( GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH); 
 glutCreateWindow ("square"); 
 glutInitWindowPosition(0,0); 
glutDisplayFunc(display);
glutMainLoop();



}
