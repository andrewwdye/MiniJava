package AST.Visitor;

import AST.*;

public class ASTVisitor implements Visitor {

  private int currentIndent = 0;

  private void increaseIndent() { currentIndent += 2; }

  private void decreaseIndent() { currentIndent -= 2; }

  private void printIndentedLine(String s) {
      String line = "";
      for (int i = 0; i < currentIndent; i++) { line += " "; }
      System.out.println(line + s);
  }

  private void printNode(ASTNode a) {
      printIndentedLine(a.getClass().getSimpleName() + "'" + a.line_number);
  }

  private void printNodeWithValue(ASTNode a, String s) {
      printIndentedLine(a.getClass().getSimpleName() + ":" + s + "'" + a.line_number);
  }

  // Display added for toy example language.  Not used in regular MiniJava
  public void visit(Display n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);

    decreaseIndent();
  }

  // MainClass m;
  // ClassDeclList cl;
  public void visit(Program n) {
    printNode(n);
    increaseIndent();

    n.m.accept(this);

    for ( int i = 0; i < n.cl.size(); i++ ) {
        n.cl.get(i).accept(this);
    }

    decreaseIndent();
  }

  // Identifier i1,i2;
  // Statement s;
  public void visit(MainClass n) {
    printNode(n);
    increaseIndent();

    n.i1.accept(this);
    n.i2.accept(this);
    n.s.accept(this);

    decreaseIndent();
  }

  // Identifier i;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclSimple n) {
    printNode(n);
    increaseIndent();

    n.i.accept(this);

    for ( int i = 0; i < n.vl.size(); i++ ) {
        n.vl.get(i).accept(this);
    }

    for ( int i = 0; i < n.ml.size(); i++ ) {
        n.ml.get(i).accept(this);
    }

    decreaseIndent();
  }

  // Identifier i;
  // Identifier j;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclExtends n) {
    printNode(n);
    increaseIndent();

    n.i.accept(this);
    n.j.accept(this);

    for ( int i = 0; i < n.vl.size(); i++ ) {
        n.vl.get(i).accept(this);
    }

    for ( int i = 0; i < n.ml.size(); i++ ) {
        n.ml.get(i).accept(this);
    }

    decreaseIndent();
  }

  // Type t;
  // Identifier i;
  public void visit(VarDecl n) {
    printNode(n);
    increaseIndent();

    n.t.accept(this);
    n.i.accept(this);

    decreaseIndent();
  }

  // Type t;
  // Identifier i;
  // FormalList fl;
  // VarDeclList vl;
  // StatementList sl;
  // Exp e;
  public void visit(MethodDecl n) {
    printNode(n);
    increaseIndent();

    n.t.accept(this);
    n.i.accept(this);

    for ( int i = 0; i < n.fl.size(); i++ ) {
        n.fl.get(i).accept(this);
    }

    for ( int i = 0; i < n.vl.size(); i++ ) {
        n.vl.get(i).accept(this);
    }

    for ( int i = 0; i < n.sl.size(); i++ ) {
        n.sl.get(i).accept(this);
    }

    n.e.accept(this);

    decreaseIndent();
  }

  // Type t;
  // Identifier i;
  public void visit(Formal n) {
    printNode(n);
    increaseIndent();

    n.t.accept(this);
    n.i.accept(this);

    decreaseIndent();
  }

  public void visit(IntArrayType n) {
    printNode(n);
  }

  public void visit(BooleanType n) {
    printNode(n);
  }

  public void visit(IntegerType n) {
    printNode(n);
  }

  // String s;
  public void visit(IdentifierType n) {
    printNodeWithValue(n, n.s);
  }

  // StatementList sl;
  public void visit(Block n) {
    printNode(n);
    increaseIndent();

    for ( int i = 0; i < n.sl.size(); i++ ) {
        n.sl.get(i).accept(this);
    }

    decreaseIndent();
  }

  // Exp e;
  // Statement s1,s2;
  public void visit(If n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);
    n.s1.accept(this);
    n.s2.accept(this);

    decreaseIndent();
  }

  // Exp e;
  // Statement s;
  public void visit(While n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);
    n.s.accept(this);

    decreaseIndent();
  }

  // Exp e;
  public void visit(Print n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);

    decreaseIndent();
  }

  // Identifier i;
  // Exp e;
  public void visit(Assign n) {
    printNode(n);
    increaseIndent();

    n.i.accept(this);
    n.e.accept(this);

    decreaseIndent();
  }

  // Identifier i;
  // Exp e1,e2;
  public void visit(ArrayAssign n) {
    printNode(n);
    increaseIndent();

    n.i.accept(this);
    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e1,e2;
  public void visit(And n) {
    printNode(n);
    increaseIndent();

    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e1,e2;
  public void visit(LessThan n) {
    printNode(n);
    increaseIndent();

    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e1,e2;
  public void visit(Plus n) {
    printNode(n);
    increaseIndent();

    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e1,e2;
  public void visit(Minus n) {
    printNode(n);
    increaseIndent();

    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e1,e2;
  public void visit(Times n) {
    printNode(n);
    increaseIndent();

    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e1,e2;
  public void visit(ArrayLookup n) {
    printNode(n);
    increaseIndent();

    n.e1.accept(this);
    n.e2.accept(this);

    decreaseIndent();
  }

  // Exp e;
  public void visit(ArrayLength n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);

    decreaseIndent();
  }

  // Exp e;
  // Identifier i;
  // ExpList el;
  public void visit(Call n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);
    n.i.accept(this);

    for ( int i = 0; i < n.el.size(); i++ ) {
        n.el.get(i).accept(this);
    }

    decreaseIndent();
  }

  // int i;
  public void visit(IntegerLiteral n) {
    printNodeWithValue(n, new Integer(n.i).toString());
  }

  public void visit(True n) {
    printNode(n);
  }

  public void visit(False n) {
    printNode(n);
  }

  // String s;
  public void visit(IdentifierExp n) {
    printNodeWithValue(n, n.s);
  }

  public void visit(This n) {
    printNode(n);
  }

  // Exp e;
  public void visit(NewArray n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);

    decreaseIndent();
  }

  // Identifier i;
  public void visit(NewObject n) {
    printNodeWithValue(n, n.i.s);
  }

  // Exp e;
  public void visit(Not n) {
    printNode(n);
    increaseIndent();

    n.e.accept(this);

    decreaseIndent();
  }

  // String s;
  public void visit(Identifier n) {
    printNodeWithValue(n, n.s);
  }
}
