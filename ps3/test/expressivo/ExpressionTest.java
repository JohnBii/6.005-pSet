/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Map;
import java.util.HashMap;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    // 
    // 测试 Expression 的测试策略:
    // 
    // 1. parse() 方法测试:
    //    - 测试有效输入: 数字、变量、加法表达式、乘法表达式
    //    - 测试无效输入: 语法错误、非法字符
    //    - 测试边界情况: 空字符串、单个字符
    //
    // 2. toString() 方法测试:
    //    - 测试各种表达式的字符串表示
    //    - 验证 toString() 和 parse() 的互逆性
    //
    // 3. equals() 方法测试:
    //    - 测试相同表达式的相等性
    //    - 测试不同表达式的非相等性
    //    - 测试与 null 和非 Expression 对象的比较
    //
    // 4. hashCode() 方法测试:
    //    - 测试相等对象具有相同的哈希码
    //    - 测试不同对象可能具有不同的哈希码
    //
    // 5. 表达式结构测试:
    //    - 测试数字表达式
    //    - 测试变量表达式
    //    - 测试加法表达式
    //    - 测试乘法表达式
    //    - 测试嵌套表达式
    //
    // 6. 边界情况测试:
    //    - 测试大数字
    //    - 测试特殊变量名
    //    - 测试复杂嵌套表达式
    //
    // 7. differentiate() 方法测试:
    //    - 测试数字表达式的导数
    //    - 测试变量表达式的导数
    //    - 测试加法表达式的导数
    //    - 测试乘法表达式的导数
    //    - 测试嵌套表达式的导数 
    //
    // 8. simplify() 方法测试：
    //    - 测试数字表达式的简化
    //    - 测试变量表达式的简化
    //      - matched or unmatched
    //    - 测试加法表达式的简化
    //    - 测试乘法表达式的简化
    //    - 测试嵌套表达式的简化   
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Expression
    // 1. parse() 方法测试
    @Test
    public void testParseValidInput() {
        // 测试数字
        Expression numExpr = Expression.parse("42");
        assertEquals("42", numExpr.toString());
        
        // 测试变量
        Expression varExpr = Expression.parse("x");
        assertEquals("x", varExpr.toString());
        
        // 测试加法表达式
        Expression plusExpr = Expression.parse("x + y");
        assertEquals("(x + y)", plusExpr.toString());
        
        // 测试乘法表达式
        Expression timesExpr = Expression.parse("x * y");
        assertEquals("x * y", timesExpr.toString());
    }
    
    @Test(expected=AssertionError.class)
    public void testParseInvalidInput() {
        Expression.parse("x +"); // 语法错误
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testParseIllegalCharacters() {
        Expression.parse("x @ y"); // 非法字符
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testParseEmptyString() {
        Expression.parse(""); // 空字符串
    }
    
    // 2. toString() 方法测试
    @Test
    public void testToString() {
        // 测试各种表达式的字符串表示
        assertEquals("42", Expression.parse("42").toString());
        assertEquals("x", Expression.parse("x").toString());
        assertEquals("(x + y)", Expression.parse("x + y").toString());
        assertEquals("x * y", Expression.parse("x * y").toString());
        assertEquals("(x + y) * z", Expression.parse("(x + y) * z").toString());
    }
    
    @Test
    public void testToStringParseRoundTrip() {
        String[] expressions = {
            "42", "3.14", "x", "abc",
            "(x + y)", "(2 + 3)",
            "x * y", "2 * 3",
            "(x + y * z)", "(x + y) * z"
        };
        
        for (String expr : expressions) {
            assertEquals(expr, Expression.parse(expr).toString());
        }
    }
    
    // 3. equals() 方法测试
    @Test
    public void testEquals() {
        Expression expr1 = Expression.parse("x + y");
        Expression expr2 = Expression.parse("x + y");
        Expression expr3 = Expression.parse("y + x");
        
        // 测试相同表达式的相等性
        assertTrue(expr1.equals(expr2));
        
        // 测试不同表达式的非相等性
        assertFalse(expr1.equals(expr3));
        
        // 测试与 null 比较
        assertFalse(expr1.equals(null));
        
        // 测试与非 Expression 对象比较
        assertFalse(expr1.equals("x + y"));
    }
    
    // 4. hashCode() 方法测试
    @Test
    public void testHashCode() {
        Expression expr1 = Expression.parse("x + y");
        Expression expr2 = Expression.parse("x + y");
        Expression expr3 = Expression.parse("y + x");
        
        // 测试相等对象具有相同的哈希码
        assertEquals(expr1.hashCode(), expr2.hashCode());
        
        // 测试不同对象可能具有不同的哈希码
        assertNotEquals(expr1.hashCode(), expr3.hashCode());
    }
    
    // 5. 表达式结构测试
    @Test
    public void testExpressionStructures() {
        // 测试数字表达式
        Expression numExpr = Expression.parse("42");
        assertEquals("42", numExpr.toString());
        
        // 测试变量表达式
        Expression varExpr = Expression.parse("x");
        assertEquals("x", varExpr.toString());
        
        // 测试加法表达式
        Expression plusExpr = Expression.parse("x + y");
        assertEquals("(x + y)", plusExpr.toString());
        
        // 测试乘法表达式
        Expression timesExpr = Expression.parse("x * y");
        assertEquals("x * y", timesExpr.toString());
        
        // 测试嵌套表达式
        Expression nestedExpr = Expression.parse("(x + y) * (z + w)");
        assertEquals("(x + y) * (z + w)", nestedExpr.toString());
    }
    
    // 6. 边界情况测试
    @Test
    public void testEdgeCases() {
        // 测试大数字
        Expression bigNum = Expression.parse("999999999");
        assertEquals("999999999", bigNum.toString());
        
        // 测试特殊变量名
        Expression specialVar = Expression.parse("xA");
        assertEquals("xA", specialVar.toString());
        
        // 测试复杂嵌套表达式
        Expression complexExpr = Expression.parse("((a + b) * (c + d)) + (e * f)");
        assertEquals("((a + b) * (c + d) + e * f)", complexExpr.toString());
    }

    // 7. differentiate() 方法测试
    @Test
    public void testDifferentiate() {
        //  测试数字表达式的导数
        Expression number = Expression.parse("42");
        assertEquals("0", number.differentiate("x").toString());
        //  测试变量表达式的导数
        Expression variable = Expression.parse("x");
        assertEquals("1", variable.differentiate("x").toString());
        assertEquals("0", variable.differentiate("y").toString());  
        //  测试加法表达式的导数
        Expression plus = Expression.parse("x + x + y");
        assertEquals("(1 + (1 + 0))", plus.differentiate("x").toString());
        assertEquals("(0 + (0 + 1))", plus.differentiate("y").toString());
        //  测试乘法表达式的导数
        Expression times = Expression.parse("x * x * y");   
        assertEquals("(1 * x * y + x * (1 * y + x * 0))", times.differentiate("x").toString());
        assertEquals("(0 * x * y + x * (0 * y + x * 1))", times.differentiate("y").toString());
        //  测试嵌套表达式的导数  
        Expression nested = Expression.parse("(x + y) * (z + w)");
        assertEquals("((1 + 0) * (z + w) + (x + y) * (0 + 0))", nested.differentiate("x").toString());
        assertEquals("((0 + 1) * (z + w) + (x + y) * (0 + 0))", nested.differentiate("y").toString());
    }

    // 8. simplify() 方法测试
    @Test
    public void testSimplify() {
        Map<String, Double> environmentMap = new HashMap<>();
        environmentMap.put("x", 39.5);
        environmentMap.put("y", 2.0);
        
        // 测试数字表达式的简化
        Expression number = Expression.parse("42");
        assertEquals("42", number.simplify(environmentMap).toString());
        // 测试变量表达式的简化
        Expression variable1 = Expression.parse("x");
        Expression variable2 = Expression.parse("Va");
        assertEquals("39.5", variable1.simplify(environmentMap).toString());
        assertEquals("Va", variable2.simplify(environmentMap).toString());
        // 测试加法表达式的简化
        Expression plus1 = Expression.parse("x + x + Va");
        Expression plus2 = Expression.parse("x + x + y");
        assertEquals("(39.5 + (39.5 + Va))", plus1.simplify(environmentMap).toString());
        assertEquals("81", plus2.simplify(environmentMap).toString());
        // 测试乘法表达式的简化
        Expression times1 = Expression.parse("x * x * Va");
        Expression times2 = Expression.parse("x * x * y");
        assertEquals("39.5 * 39.5 * Va", times1.simplify(environmentMap).toString());
        assertEquals("3120.5", times2.simplify(environmentMap).toString());
        // 测试嵌套表达式的简化
        Expression complex1 = Expression.parse("(x + y) * (Va + y)");
        Expression complex2 = Expression.parse("(y * y) + (x * y)");
        environmentMap.put("y", 2.0);
        environmentMap.put("x", 0.0);
        assertEquals("2 * (Va + 2)", complex1.simplify(environmentMap).toString());
        assertEquals("4", complex2.simplify(environmentMap).toString());
    }
}
