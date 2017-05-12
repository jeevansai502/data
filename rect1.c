#include <GLTools.h>
// OpenGL toolkit
#include <GLShaderManager.h> // Shader Manager Class
#ifdef __APPLE__
#include <glut/glut.h>
#else
#define FREEGLUT_STATIC
#include <GL/glut.h>
#endif
// OS X version of GLUT
// Windows FreeGlut equivalent
GLBatch
triangleBatch;
GLShaderManager shaderManager;

void ChangeSize(int w, int h)
{
glViewport(0, 0, w, h);
}

void SetupRC()
{
// Blue background
glClearColor(0.0f, 0.0f, 1.0f, 1.0f );
shaderManager.InitializeStockShaders();
// Load up a triangle
GLfloat vVerts[] = { -0.5f, 0.0f, 0.0f,
0.5f, 0.0f, 0.0f,
0.0f, 0.5f, 0.0f };
triangleBatch.Begin(GL_TRIANGLES, 3);
triangleBatch.CopyVertexData3f(vVerts);
triangleBatch.End();
}

void RenderScene(void)
{
// Clear the window with current clearing color
glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
GLfloat vRed[] = { 1.0f, 0.0f, 0.0f, 1.0f };
shaderManager.UseStockShader(GLT_SHADER_IDENTITY, vRed);
triangleBatch.Draw();
// Perform the buffer swap to display the back buffer
glutSwapBuffers();
}


int main(int argc, char* argv[])
{
gltSetWorkingDirectory(argv[0]);
glutInit(&argc, argv);
glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA | GLUT_DEPTH | GLUT_STENCIL);
glutInitWindowSize(800, 600);
glutCreateWindow(“Triangle”);
glutReshapeFunc(ChangeSize);
glutDisplayFunc(RenderScene);
GLenum err = glewInit();
if (GLEW_OK != err) {
fprintf(stderr, “GLEW Error: %s\n”, glewGetErrorString(err));
return 1;
}
SetupRC();
glutMainLoop();
return 0;
}
