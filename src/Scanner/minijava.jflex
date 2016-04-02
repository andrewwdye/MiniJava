/*
 * JFlex specification for the lexical analyzer for a simple demo language.
 * Change this into the scanner for your implementation of MiniJava.
 * CSE 401/P501 Au11
 */


package Scanner;

import java_cup.runtime.*;
import Parser.sym;

%%

%public
%final
%class scanner
%unicode
%cup
%line
%column

/* Code copied into the generated scanner class.  */
/* Can be referenced in scanner action code. */
%{
  // Return new symbol objects with line and column numbers in the symbol
  // left and right fields. This abuses the original idea of having left
  // and right be character positions, but is   // is more useful and
  // follows an example in the JFlex documentation.
  private Symbol symbol(int type) {
    return new Symbol(type, yyline+1, yycolumn+1);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline+1, yycolumn+1, value);
  }

  // Return a readable representation of symbol s (aka token)
  public String symbolToString(Symbol s) {
    String rep;
    switch (s.sym) {
      case sym.IDENTIFIER: return "ID(" + (String)s.value + ")";
      case sym.EOF: return "<EOF>";
      case sym.error: return "<ERROR>";
      default:
        if (s.sym < sym.terminalNames.length) { return sym.terminalNames[s.sym]; }
        else return "<UNEXPECTED TOKEN " + s.toString() + ">";
    }
  }

  // Keep track of the scan errors
  private int numScanErrors = 0;

  public int num_scan_errors() { return numScanErrors; }
%}

/* Helper definitions */
letter = [a-zA-Z]
digit = [0-9]
eol = [\r\n]
white = {eol}|[ \t]
comment = "//"
anyCharacter = [^\r\n]

%%

/* Token definitions */

/* reserved words */
/* (put here so that reserved words take precedence over identifiers) */
"class" { return symbol(sym.CLASS); }
"public" { return symbol(sym.PUBLIC); }
"static" { return symbol(sym.STATIC); }
"void" { return symbol(sym.VOID); }
"main" { return symbol(sym.MAIN); }
"String" { return symbol(sym.STRING); }
"extends" { return symbol(sym.EXTENDS); }
"return" { return symbol(sym.RETURN); }
"int" { return symbol(sym.INT); }
"boolean" { return symbol(sym.BOOLEAN); }
"if" { return symbol(sym.IF); }
"else" { return symbol(sym.ELSE); }
"while" { return symbol(sym.WHILE); }
"System.out.println" { return symbol(sym.PRINTLN); }
"true" { return symbol(sym.TRUE); }
"false" { return symbol(sym.FALSE); }
"this" { return symbol(sym.THIS); }
"new" { return symbol(sym.NEW); }
"length" { return symbol(sym.LENGTH); }

/* operators */
"+" { return symbol(sym.PLUS); }
"-" { return symbol(sym.MINUS); }
"=" { return symbol(sym.BECOMES); }
"&&" { return symbol(sym.AND); }
"<" { return symbol(sym.LESSTHAN); }
"*" { return symbol(sym.TIMES); }
"!" { return symbol(sym.NOT); }

/* delimiters */
"(" { return symbol(sym.LPAREN); }
")" { return symbol(sym.RPAREN); }
";" { return symbol(sym.SEMICOLON); }
"{" { return symbol(sym.LBRACE); }
"}" { return symbol(sym.RBRACE); }
"[" { return symbol(sym.LBRACKET); }
"]" { return symbol(sym.RBRACKET); }
"." { return symbol(sym.DOT); }
"," { return symbol(sym.COMMA); }

/* identifiers */
{letter} ({letter}|{digit}|_)* { return symbol(sym.IDENTIFIER, yytext()); }
{digit}+ { return symbol(sym.LITERAL, Integer.parseInt(yytext())); }

/* whitespace */
{white}+ { /* ignore whitespace */ }

/* comments */
{comment} {anyCharacter}* {eol}? { /* ignore comments */ }

/* lexical errors (put last so other matches take precedence) */
. { System.err.println(
	"\nunexpected character in input: '" + yytext() + "' at line " +
	(yyline+1) + " column " + (yycolumn+1));
  numScanErrors++;
  }
