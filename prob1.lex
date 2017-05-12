%{
#include <stdio.h>
%}

DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*"."{DIGIT}+
TEXT [a-zA-Z ]+
TEXT_NUMBERS [a-zA-Z0-9]
DELIMITER [; :\t\n()"]
IDENTIFIER [a-zA-Z]{TEXT_NUMBERS}*|[a-zA-Z]{TEXT_NUMBERS}*[[{NUMBER}+]]
NON_IDENTIFIER {NUMBER}+[A-Za-z]+
FORMAT_SPECIFIER "%"{TEXT_NUMBERS}+
FILE "<"{IDENTIFIER}.h">"
COMMENT "/*"[a-zA-Z0-9 \t\n;.~!@#$%^&*()_+=<>?:"{}]*"*/"
AOPERATOR "+"|"-"|"*"|"/"|"="
BLOCK_BEGINS "{" | "[" 
BLOCK_ENDS "}" | "]" 
KEYWORD  "\\begin"|"\\end"|"enumerate"|"justify"|"\\item"|"\\includegraphics"|"width"|"height"
OTHER {DIGIT}|{NUMBER}|{REAL}|{TEXT}|{TEXT_NUMBERS}|{DELIMITER}|{IDENTIFIER}|{NON_IDENTIFIER}|{FORMAT_SPECIFIER}|{COMMENT}

%%

{KEYWORD} { printf("%s is a keyword\n", yytext); }
{OTHER} {printf("%s is other\n", yytext); }
%%

int main(int argc, char *argv[]) {
yylex();
return 0;
}
