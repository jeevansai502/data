#include<GL/glut.h>
#include<GL/gl.h>

void display_func(void)
{
static	GLfloat	vert[][4]={
	{1.0,	1.0,	1.0},
	{-1.0,	1.0,	1.0},
	{-1.0,	-1.0,	1.0},
	{1.0,	-1.0,	1.0},
	{1.0,	1.0,	-1.0},
	{-1.0,	1.0,	-1.0},
	{-1.0,	-1.0,	-1.0},
	{1.0,	-1.0,	-1.0},
};

static	GLfloat	color[][4]={
	{1.0,	0.0,	0.0,	0.0},
	{0.0,	1.0,	0.0,	0.0},
	{0.0,	0.0,	1.0,	0.0},
	{0.0,	1.0,	1.0,	0.0},
	{1.0,	0.0,	1.0,	0.0},
	{1.0,	1.0,	0.0,	0.0},
};

glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

glPushMatrix();
glEnd();


}

int main(int argc,char *argv[]){

glutInit(&argc,	argv);
glutInitDisplayMode(GLUT_RGB|GLUT_DOUBLE|GLUT_DEPTH);
glutInitWindowSize(300,300);
glutCreateWindow("Cube");
glutDisplayFunc(display_func);
glEnable(GL_DEPTH_TEST);
glutMainLoop();
return	0;
}
