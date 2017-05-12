%{
#include <stdio.h>
%}

DIGIT [0-9]
INTEGER -?{DIGIT}+
FLOAT {DIGIT}*"."{DIGIT}+

%%

{INTEGER} { printf("Integer"); }
{FLOAT} { printf("Float"); }

%%


int main(int argc, char *argv[]) {
yylex();
return 0;
}
