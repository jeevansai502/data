%{
#include <stdio.h>
%}

KEYWORD  "\end"|"enumerate"|"justify"|"\item"|"\includegraphics"|"width"|"height"|"\begin"
DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*"."{DIGIT}+
TEXT [a-zA-Z ]+
TEXT_NUMBERS [a-zA-Z0-9.]
IDENTIFIER [a-zA-Z]{TEXT_NUMBERS}*|[a-zA-Z]{TEXT_NUMBERS}*[[{NUMBER}+]]
BLOCK_BEGINS "{"|"["|"("
BLOCK_ENDS "}"|"]"|")"
OPERATOR "+"|"-"|"*"|"/"|"="|","
OTHER {DIGIT}|{NUMBER}|{REAL}|{TEXT}|{TEXT_NUMBERS}|{IDENTIFIER}|{BLOCK_BEGINS}|{BLOCK_ENDS}|{OPERATOR}

%%

{KEYWORD} { printf("%s is a keyword\n", yytext); }
{OTHER} { printf("%s is an other\n", yytext); }

%%

int main(int argc, char *argv[]) {
yylex();
return 0;
}
