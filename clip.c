#include<GL/glut.h>
#include<GL/gl.h>

float p[12][2];

void clip2(float x1,float y1,float x2,float y2,int l){

float m;

m = (y2-y1)/(x2-x1);

float x = (0.5 + m*x1 - y1)/m;
float y =0.5;
p[][]


}

void clip1(float v[][2]){

for(int i=0;i<8;i++){
if(i<2)
clip2(v[i][0],v[i][1],v[i+1][0],v[i+1][1],1);
else if(i<4)
clip2(v[i][0],v[i][1],v[i+1][0],v[i+1][1],2);
else if(i<6)
clip2(v[i][0],v[i][1],v[i+1][0],v[i+1][1],3);
else if(i<8)
clip2(v[i][0],v[i][1],v[i+1][0],v[i+1][1],4);
}

}

void display(){

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glMatrixMode(GL_MODELVIEW);
        glColor3f(1.0,0.0,0.0);
        glBegin(GL_LINE_LOOP);

        glVertex3f(-1.0,0.0,0.0);
        glVertex3f(-0.4,0.4,0.0);
        glVertex3f(0.0,1.0,0.0);
        glVertex3f(0.4,0.4,0.0);
        glVertex3f(1.0,0.0,0.0);
        glVertex3f(0.4,-0.4,0.0);
        glVertex3f(0.0,-1.0,0.0);
        glVertex3f(-0.4,-0.4,0.0);

        glEnd();

float v[8][2];
v[0][0]=-1,v[0][1]=0;
v[1][0]=-0.4,v[1][1]=0.4;
v[2][0]=0,v[2][1]=1;
v[3][0]=0.4,v[3][1]=0.4;
v[4][0]=1,v[4][1]=0;
v[5][0]=0.4,v[5][1]=-0.4;
v[6][0]=0,v[6][1]=-1;
v[7][0]=-0.4,v[7][1]=-0.4;


clip1(v);

        glutSwapBuffers();

}

int main(int argc, char *argv[]){

        glutInit(&argc, argv);
        glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
        glutInitWindowSize(500,500);
        glutCreateWindow("clip");
        glutDisplayFunc(display);
        glutMainLoop();

}
