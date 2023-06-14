// All Java Packages
import table.*;
import java.io.*;
import java.lang.String;


%%
// All Directives & Macros

%class LA
%standalone
%line
%column


errors = [.@]

whitespace = (\s)+

keyword = ([iI][fF])|([eE][lL][sS][eE])|([dD][oO])|([wW][hH][iI][lL][eE])|([fF][oO][rR])|([sS][wW][iI][tT][cC][hH])|([cC][aA][sS][eE])|([fF][uU][nN][cC][tT][iI][oO][nN])|([pP][rR][oO][cC][eE][dD][uU][rR][eE])|([rR][eE][aA][lL])|([vV][aA][rR])|([aA][rR][rR][aA][yY])|([wW][rR][iI][tT][eE])|([pP][rR][oO][gG][rR][aA][mM])|([nN][oO][tT])|([eE][nN][dD])|([bB][eE][gG][iI][nN])|([tT][hH][eE][nN])|([iI][nN][tT][eE][gG][eE][rR])|([cC][lL][aA][sS][sS])|([nN][eE][wW])|"String"|([rR][eE][tT][uU][rR][nN])|([jJ][aA][vV][aA])|([sS][tT][aA][tT][iI][c])|([iI][mM][pP][oO][rR][tT])|([pP][uU][bB][lL][iI][cC])|([pP][rR][iI][vV][aA][tT][eE])|([aA][bB][sS][tT][rR][aA][cC][tT])|([eE][xX][tT][eE][nN][dD][sS])|([iI][nN][tT])|([fF][lL][oO][aA][tT])|([cC][hH][aA][rR])|([bB][oO][oO][lL][eE][aA][nN])|([sS][yY][sS][tT][eE][mM])|([vV][oO][iI][dD])|([nN][uU][lL][lL])

variable = [_$a-zA-Z][_$a-zA-Z0-9]*

expo = [+-]?([0-9]*[.][0-9]+)[E][+-]?[0-9]+
integer = [+-]?[0-9]+
float = [+-]?([0-9]*[.])?[0-9]+

slc = [/][/].*

relOp = ([=][=])|([!][=])|([<])|([<][=])|([>][=])|([>])
mulOp = [*]|[/]|[%]
addOp = [+]|[-]

assignOp = ":="|"="

comma = ","
braces = [(]|[)]|"["|"]"|[{]|[}]
eosOp = [;]
terOp = [:]

quote = [\"].*[\"]


%%

// All Grammers & Respective functions


{errors} {/*System.out.println("Errors");*/}

{whitespace} {/*System.out.println("WhiteSpace detected at line" + yyline + " - Ignored");*/}


{keyword} {
    String temp = yytext();
    System.out.println(temp.toUpperCase() + " is an Identifier(keyword), detected at line " + (yyline + 1) + " - Only Print in capital letters");
}
{variable} {
    ProgrammeClass pc = new ProgrammeClass();

    if(pc.insert(yytext(), "Identifier(variable)")) {
        System.out.println("Identifier(variable) insertion successful");
    } else {
        System.out.println("Identifier(variable) Insertion unsuccessful");
    }

}
{expo} {
    ProgrammeClass pc = new ProgrammeClass();

    if(pc.insert(yytext(), "Number(exponenential)")) {
        System.out.println("Number(exponential) insertion successful");
    } else {
        System.out.println("Number(exponential) Insertion unsuccessful");
    }
}
{integer} {
    ProgrammeClass pc = new ProgrammeClass();

    if(pc.insert(yytext(), "Number(integer)")) {
        System.out.println("Number(integer) insertion successful");
    } else {
        System.out.println("Number(integer) insertion unsuccessful");
    }
}
{float} {
    ProgrammeClass pc = new ProgrammeClass();

    if(pc.insert(yytext(), "Number(float)")) {
        System.out.println("Number(float) insertion successful");
    } else {
        System.out.println("Number(float) insertion unsuccessful");
    }

}

{slc} {System.out.println(yytext() + " is a Single Line Comment, detected at line" + (yyline + 1) + " - Only print" );}

{relOp} {
    ProgrammeClass pc = new ProgrammeClass();

    System.out.println(yytext() + " is a Relational Operator, detected at line " + (yyline + 1) + " - print & insert in table" );

    if(pc.insert(yytext(), "RelOp")) {
        System.out.println("insertion successful");
    } else {
        System.out.println("Insertion unsuccessful");
    }

}
{mulOp} {
    System.out.println(yytext() + " is a Multiplication Operator, detected at line " + (yyline + 1) + " - print & insert in table" );
    ProgrammeClass pc = new ProgrammeClass();

    if(pc.insert(yytext(), "MulOp")) {
        System.out.println("insertion successful");
    } else {
        System.out.println("Insertion unsuccessful");
    }
}
{addOp} {
    System.out.println(yytext() + " is a Additional Operator, detected at line " + (yyline + 1) + " - print & insert in table" );

    ProgrammeClass pc = new ProgrammeClass();

    if(pc.insert(yytext(), "AddOp")) {
        System.out.println("insertion successful");
    } else {
        System.out.println("Insertion unsuccessful");
    }
}

{assignOp} {System.out.println(yytext() + " is an assignment Operator, detected at line " + (yyline + 1) + " - Only print" );}

{comma} {System.out.println(yytext() + " is a comma, detected at line " + (yyline + 1) + " - Only print" );}
{braces} {System.out.println(yytext() + " is a brac, detected at line " + (yyline + 1) + " - Only print" );}
{eosOp} {System.out.println(yytext() + " is an End of Statement Operator, detected at line " + (yyline + 1) + " - Only print" );}
{terOp} {System.out.println(yytext() + " is a Ternary Operator, detected at line " + (yyline + 1) + " - Only print" );}

{quote} {System.out.println(yytext() + " is a Quotation, detected at line " + (yyline + 1) + " - Only print" );}


/*
    ProgrammeClass pc = new ProgrammeClass();
    System.out.println();
    System.out.println("-------------------------------Table-------------------------------");
    pc.showSymbolTable();
    */












