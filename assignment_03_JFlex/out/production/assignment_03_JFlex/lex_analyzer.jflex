// All Java Packages
import java.io.*;
import java.lang.String;


%%
// All Directives & Macros

%class analyzer
%standalone
%line
%column


errors = [.@]
whitespace = (\s)+


expo = [+-]?((0?|([1-9]+0*)+)[.])?[0-9]+[E][+-]?(0?|([1-9]+0*)+)
float = [+-]?((0?|([1-9]+0*)+)[.])?[0-9]+
int = [+-]?[1-9][0-9]*

nFirst =[n][^0-9].*
variable = [_$a-zA-Z][_$a-zA-Z0-9]*


%%

// All Grammers & Respective functions

{int} {
    String temp = yytext();
    System.out.println(temp + " - The symbol is an integer");
}

{float} {
    String temp = yytext();
    System.out.println(temp + " - The symbol is a floating point");
}

{expo} {
    String temp = yytext();
    System.out.println(temp + " - The symbol is an exponential number");
}

{nFirst} {
    String temp = yytext();
    System.out.println(temp + " - The symbol starts with n and the second character is not any number");
}

{variable} {
    String temp = yytext();
    System.out.println(temp + " - The symbol is a variable");
}















