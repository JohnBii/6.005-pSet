/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.List;
import java.util.Stack;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.ErrorNode;

import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionBaseListener;
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
       MakeExpresion listener = new MakeExpresion();
       walker.walk(listener, tree);
       return listener.getExpression();


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
    /**
     * 一个用于构建 Expression 对象的监听器类。
     * 
     * 抽象函数：
     * AF(stack) = 一个表达式树，其中：
     * - 如果 stack 为空，则 AF(stack) = 未定义
     * - 如果 stack 非空，则 AF(stack) = stack 中所有表达式的组合，其中：
     *   - stack[0] 是根表达式
     *   - stack[i] 是第 i 个子表达式
     * 
     * 表示不变量：
     * - stack 不为 null
     * - stack 中的每个元素都是非 null 的 Expression 对象
     * - stack 中的表达式按照解析树的遍历顺序排列
     * - 在遍历过程中，stack 的大小始终大于等于当前正在处理的子表达式数量
     * 
     * 表示暴露安全性：
     * - stack 是私有的，且没有公共方法可以修改它
     * - getExpression() 方法返回的是不可变的 Expression 对象
     * - 所有方法都是包私有的，只能被 Expression 类使用
     */
    class MakeExpresion extends ExpressionBaseListener {
        private final Stack<Expression> stack = new Stack<>();
        
        // Invariant: stack contains the Expression value of each parse
        // subtree that has been fully-walked so far, but whose parent has not yet
        // been exited by the walk. The stack is ordered by recency of visit, so that
        // the top of the stack is the Expression for the most recently walked
        // subtree.
        //
        // At the start of the walk, the stack is empty, because no subtrees have
        // been fully walked.
        //
        // Whenever a node is exited by the walk, the Expression values of its
        // children are on top of the stack, in order with the last child on top. To
        // preserve the invariant, we must pop those child Expression values
        // from the stack, combine them with the appropriate Expression
        // producer, and push back an Expression value representing the entire
        // subtree under the node.
        //
        // At the end of the walk, after all subtrees have been walked and the the
        // root has been exited, only the entire tree satisfies the invariant's
        // "fully walked but parent not yet exited" property, so the top of the stack
        // is the Expression of the entire parse tree.

        /**
         * 检查表示不变量是否满足。
         * @throws AssertionError 如果表示不变量被违反
         */
        private void checkRep() {
            assert stack != null : "stack 不能为 null";
            for (Expression expr : stack) {
                assert expr != null : "stack 中的表达式不能为 null";
            }
        }

        /**
         * 返回由该监听器对象构建的表达式。
         * 要求该监听器已经使用 ParseTreeWalker 完全遍历了一个 Expression 解析树。
         * 
         * @return 被遍历的解析树对应的 Expression
         * @throws IllegalArgumentException 如果输入的表达式不合法
         */
        public Expression getExpression() {
            checkRep();
            if (stack.isEmpty()) {
                throw new IllegalArgumentException("输入的表达式不合法");
            }
            Expression result = stack.get(0);
            checkRep();
            return result;
        }

        /**
         * 当退出根节点时调用。
         * 由于根节点只有一个子节点，其值已经在栈顶，所以不需要额外操作。
         * 
         * @param ctx 解析树中对应 'root' 规则的节点
         */
        @Override public void exitRoot(ExpressionParser.RootContext ctx) { 
            // do nothing, root has only one child so its value is already on top of the stack
            checkRep();
        }
        
        /**
         * 当退出乘法表达式节点时调用。
         * 处理形如 primitive ('*' primitive)* 的规则。
         * 将栈顶的表达式组合成乘法表达式。
         * 
         * @param ctx 解析树中对应 'multiply' 规则的节点
         */
        @Override public void exitMultiply(ExpressionParser.MultiplyContext ctx) { 
            checkRep();
            // matched the primitive ('*' primitive)* rule
            List<ExpressionParser.PrimitiveContext> multiplyends = ctx.primitive();
            assert stack.size() >= multiplyends.size();

            assert multiplyends.size() >= 1;
            Expression multiply = stack.pop();

            for(int i = multiplyends.size() - 1; i > 0; i--) {
                multiply = new Multiply(stack.pop(), multiply);
            }
            stack.push(multiply);
            checkRep();
        }
        
        /**
         * 当退出加法表达式节点时调用。
         * 处理形如 multiply ('+' multiply)* 的规则。
         * 将栈顶的表达式组合成加法表达式。
         * 
         * @param ctx 解析树中对应 'sum' 规则的节点
         */
        @Override public void exitSum(ExpressionParser.SumContext ctx) { 
            checkRep();
            // matched the multiply ('+' multiply)* rule
            List<ExpressionParser.MultiplyContext> sumends = ctx.multiply();
            assert stack.size() >= sumends.size();

            assert sumends.size() >= 1;
            Expression sum = stack.pop();

            for(int i = sumends.size() - 1; i > 0; i--) {
                sum = new Plus(stack.pop(), sum);
            }
            stack.push(sum);
            checkRep();
        }
        
        /**
         * 当退出基本表达式节点时调用。
         * 处理形如 NUMBER | VARIABLE | '(' sum ')' | '(' primitive ')' 的规则。
         * 根据匹配的规则类型，将相应的表达式压入栈中。
         * 
         * @param ctx 解析树中对应 'primitive' 规则的节点
         */
        @Override public void exitPrimitive(ExpressionParser.PrimitiveContext ctx) { 
            checkRep();
            // matched the NUMBER | VARIABLE | '(' sum ')' | '(' primitive ')' rule
            if (ctx.NUMBER() != null) {
                // matched NUMBER rule
                stack.push(new Number(Double.parseDouble(ctx.NUMBER().getText())));
            } else if (ctx.VARIABLE() != null) {
                // matched VARIABLE rule
                stack.push(new Variable(ctx.VARIABLE().getText()));
            } else {
                // matched '(' sum ')' | '(' primitive ')' rule
                // do nothing, the value of the child expression is already on top of the stack
            }
            checkRep();
        }
      

    }

}
