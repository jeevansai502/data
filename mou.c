#include<stdio.h>
#include<math.h>
#include<GL/glut.h>
#include<string.h>

int day = 0;

void myinit()
{
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(0,1000,0,500);
}
void startscreen()
{
	glClearColor(1,1,1,1);
	glClear(GL_COLOR_BUFFER_BIT);
}

void cloud(){

		






}

int mount1(int base,int height,int dir){

	glColor3f(0.91,0.76,0.65);
	glBegin(GL_POLYGON);

	int x,y;
	for(int i=0;i<1000;i++){

		x = base + i;
		y = (-1)*pow(i,2)/40 + height;

		glVertex2i(x,y);
		glVertex2i(base-i,y);           

		if(y < 0){
			if(dir > 0)
				base = x;
			else
				base = base -i;
			break;
		}         

	}

	glVertex2i(base,0);
	glEnd();

	return base;
}

void sun(){

if(day < 250){
	
}


}

void mount(){

 int base = 0;
        int x,y;
        base = mount1(base,500,1);
        base = mount1(base,300,1);
        base = mount1(base,100,1);

        base = 1000;
        base = mount1(base,500,-1);
        base = mount1(base,300,-1);
        base = mount1(base,100,-1);

}

void display(){

	mount();
	sun();	
	

	day++;
	glutSwapBuffers();
}

int main(int argc,char** argv)
{
	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGB);
	glutInitWindowSize(1000,500);
	glutInitWindowPosition(10,10);
	glutCreateWindow("day and night");
	myinit();
	startscreen();
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	glEnable(GL_BLEND);
	glutDisplayFunc(display);
	glutMainLoop();
	return 0;
}
