// Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.intellij.sdk.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.intellij.sdk.language.psi.MetalTypes;  // contain token we'll use

%%

%class MetalLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]

END_OF_LINE_COMMENT="//"[^\r\n]*
TO_LINE_END=[^\r\n]*[\r\n]
SPACE_SEPARATOR=[\ ]+
INTEGER_CONSTANT=\d+(u|U)?
FLOAT_CONSTANT=\d*\.\d+(lf|f|F|LF)?
IDENTIFIER=[:jletter:][:jletterdigit:]*


%state WAITING_VALUE
%xstate TO_LINE_END

%%

// Basic types --------------------------------------------
void                                { return MetalTypes.VOID; }
int                                 { return MetalTypes.INT; }
uint                                { return MetalTypes.UINT; }
float                               { return MetalTypes.FLOAT; }
double                              { return MetalTypes.DOUBLE; }
bool                                { return MetalTypes.BOOL; }

true                                 { return MetalTypes.TRUE; }
false                                { return MetalTypes.FALSE; }

// Control flow keyword ------------------------------------------------
if                                { return MetalTypes.IF; }
else                                { return MetalTypes.ELSE; }
for                                { return MetalTypes.FOR; }
// exit control keyword
break                                { return MetalTypes.BREAK; }
continue                                { return MetalTypes.CONTINUE; }
return                                { return MetalTypes.RETURN; }


// Storage qualifier ------------------------------------------------
constant                   {return MetalTypes.CONSTANT; }
thread                     {return MetalTypes.THREAD; }
device                     {return MetalTypes.DEVICE; }



<TO_LINE_END> {
    [^]                 { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }
}


// Other symbols ------------------------------------------------
";"                                { return MetalTypes.SEMICOLON; }
"{"                                { return MetalTypes.C_BRACKET_L; }
"}"                                { return MetalTypes.C_BRACKET_R; }
"["                                { return MetalTypes.S_BRACKET_L; }
"]"                                { return MetalTypes.S_BRACKET_R; }
"("                                { return MetalTypes.PAREN_L; }
")"                                { return MetalTypes.PAREN_R; }
","                                { return MetalTypes.COMMA; }
"."                                { return MetalTypes.DOT; }

// Assignment symbols --------------------------------------------
"="                     {return MetalTypes.EQUAL; }
"*="                    {return MetalTypes.MUL_ASSIGN; }
"/="                    {return MetalTypes.DIV_ASSIGN; }
"+="                    {return MetalTypes.ADD_ASSIGN; }
"-="                    {return MetalTypes.SUB_ASSIGN; }
"%="                    {return MetalTypes.MOD_ASSIGN; }
"<<="                   {return MetalTypes.LEFT_ASSIGN; }
">>="                   {return MetalTypes.RIGHT_ASSIGN; }
"&="                    {return MetalTypes.AND_ASSIGN; }
"^="                    {return MetalTypes.XOR_ASSIGN; }
"|="                    {return MetalTypes.OR_ASSIGN; }

// Non-assignment symbols --------------------------------------------
"+"                     {return MetalTypes.PLUS; }
"-"                     {return MetalTypes.DASH; }
"*"                     {return MetalTypes.STAR; }
"/"                     {return MetalTypes.SLASH; }
"%"                     {return MetalTypes.PERCENT; }
"<<"                    {return MetalTypes.LEFT_OP; }
">>"                    {return MetalTypes.RIGHT_OP; }
"&"                     {return MetalTypes.AMPERSAND; }
"^"                     {return MetalTypes.CARET; }
"|"                     {return MetalTypes.VERTICAL_BAR; }

// Unary like symbols --------------------------------------------
"!"                     {return MetalTypes.EXCLAMATION; }
"++"                     {return MetalTypes.INCREMENT; }
"--"                     {return MetalTypes.DECREMENT; }

// Relational symbols ------------------------------------------------
"=="                    {return MetalTypes.EQ_OP; }
"<"                     {return MetalTypes.ANGLE_L; }
">"                     {return MetalTypes.ANGLE_R; }
">="                    {return MetalTypes.GE_OP; }
"<="                    {return MetalTypes.LE_OP; }
"!="                    {return MetalTypes.NE_OP; }
"&&"                    {return MetalTypes.AND_OP; }
"||"                    {return MetalTypes.OR_OP; }
"^^"                    {return MetalTypes.XOR_OP; }

<YYINITIAL> {END_OF_LINE_COMMENT}                           { return MetalTypes.COMMENT; }
<YYINITIAL> {INTEGER_CONSTANT}                                { return MetalTypes.INTEGER_CONSTANT; }
<YYINITIAL> {FLOAT_CONSTANT}                                { return MetalTypes.FLOAT_CONSTANT; }
<YYINITIAL> {IDENTIFIER}                                { return MetalTypes.IDENTIFIER; }

<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }

({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }


[^]                                                         { return TokenType.BAD_CHARACTER; }