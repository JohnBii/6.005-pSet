/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionParser;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    // Expression = Number(n:double) + Variable(name:String) + Plus(left:Expression, right:Expression) + Times(left:Expression, right:Expression)
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        CharStream inputStream = new ANTLRInputStream(input);
        ExpressionLexer lexer = new ExpressionLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExpressionParser parser = new ExpressionParser(tokens);
        ParseTree tree = parser.root();

        // *** Debugging option #1: print the tree to the console
       System.err.println(tree.toStringTree(parser));

       // *** Debugging option #2: show the tree in a window
       // Trees.inspect(tree, parser);

       ParseTreeWalker walker = new ParseTreeWalker();



    }
    
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // TODO more instance methods
    class MakeExpresion implements ExpressionListener {
        @Override
        public void enterRoot(ExpressionParser.RootContext ctx) {
            System.out.println("enterRoot");
        }   

        @Override
        public void enterMultiply(ExpressionParser.MultiplyContext ctx) {
            System.out.println("enterMultiply");
        }   

        @Override
        public void enterSum(ExpressionParser.SumContext ctx) {
            System.out.println("enterSum");
        }      

        @Override
        public void enterPrimitive(ExpressionParser.PrimitiveContext ctx) {
            System.out.println("enterPrimitive");
        }      

        @Override
        public void visitTerminal(TerminalNode node) {
            System.out.println("visitTerminal");
        }

        @Override
        public void visitErrorNode(ErrorNode node) {
            System.out.println("visitErrorNode");
        }

        @Override
        public void enterEveryRule(ParserRuleContext ctx) {
        }

        @Override
        public void exitEveryRule(ParserRuleContext ctx) {
        }
            
        
        
        
        
        
    }

}
