#include<GL/glew.h>
#include<GL/glut.h>
#include<GL/gl.h>
#include<GL/glu.h>

int flag = 0,count=0;
float ball_x = 0.0,ball_y = 0.0,ball_z = 0.0;
float tx=0,ty=0,tz=0;

void resizedisplay(int w,int h){

	glViewport(0,0,w,h);

}
/*
void translate(tx, ty, tz) {
	
 float Matrixproduct[4][1]={ {1,0,0,ball_x},{0,1,0,ball_y},{0,0,1,ball_z},{0,0,0,1} };
    this.elements[3*4+0] += this.elements[0*4+0] * tx + this.elements[1*4+0] * ty + this.elements[2*4+0] * tz;
    this.elements[3*4+1] += this.elements[0*4+1] * tx + this.elements[1*4+1] * ty + this.elements[2*4+1] * tz;
    this.elements[3*4+2] += this.elements[0*4+2] * tx + this.elements[1*4+2] * ty + this.elements[2*4+2] * tz;
    this.elements[3*4+3] += this.elements[0*4+3] * tx + this.elements[1*4+3] * ty + this.elements[2*4+3] * tz;

    return this;
}

*/

/*
int[][1] translate(){

	float Matrixproduct[4][1];
	float tr[4][4] ={ {1,0,0,ball_x},{0,1,0,ball_y},{0,0,1,ball_z},{0,0,0,1} };
	float ve[4][1] = {{tx},{ty},{tz},{1}};

	for(int i=0 ; i<4 ; i++)
	{
		for(int j=0 ; j<1 ; j++)
		{
			Matrixproduct[i][j] = 0 ;
			for(int k=0 ; k<4 ; k++)
			{
				Matrixproduct[i][j] = Matrixproduct[i][j] + ( tr[i][k] * ve[k][j] );
			}
		}
	} 
	tx = Matrixproduct[0][0];
	ty = Matrixproduct[1][0];
	tz = Matrixproduct[2][0];
return Matrixproduct;
}
*/
void update(){
	if(!flag)
	{
		ball_y+=0.05;
		if(ball_y>1.0)
			flag=1;
	}
	if(flag)
	{
		ball_y-=0.05;
		if(ball_y<-1)
			flag=0;
	}

}

void display(){

	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	glClearColor(0,1,0,0);
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();

	glPushMatrix();

	glColor3f(1.0, 0.0, 0.0);
//	translate();
	glTranslatef(ball_x,ball_y,ball_z);
	glutSolidSphere (0.08, 20, 20);
	glPopMatrix();
	update();

	glutSwapBuffers();
}

int main(int argc,char *argv[]){

	glutInit(&argc,argv);
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
	glutInitWindowSize(500,500);
	glutCreateWindow("Bounce");
	glMatrixMode(GL_PROJECTION);
	glutReshapeFunc(resizedisplay);
	glutDisplayFunc(display);
	glutIdleFunc(display);	
	glutMainLoop();

}
