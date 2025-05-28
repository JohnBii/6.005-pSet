/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Map;
import java.util.HashMap;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy
    // 1. 测试 Commands.differentiate() 方法
    //    - 测试数字表达式的导数
    //    - 测试变量表达式的导数
    //    - 测试加法表达式的导数
    //    - 测试乘法表达式的导数
    //    - 测试嵌套表达式的导数
    // 2. 测试 Commands.simplify() 方法 
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // TODO tests for Commands.simplify()
    @Test
    public void testDifferentiate() {
        //  测试数字表达式的导数
        assertEquals("0", Commands.differentiate("42", "x"));
        //  测试变量表达式的导数
        assertEquals("1", Commands.differentiate("x", "x"));
        assertEquals("0", Commands.differentiate("x", "y"));  
        //  测试加法表达式的导数
        assertEquals("(1 + (1 + 0))", Commands.differentiate("x + x + y", "x"));
        assertEquals("(0 + (0 + 1))", Commands.differentiate("x + x + y", "y"));
        //  测试乘法表达式的导数
        assertEquals("(1 * x * y + x * (1 * y + x * 0))", Commands.differentiate("x * x * y", "x"));
        assertEquals("(0 * x * y + x * (0 * y + x * 1))", Commands.differentiate("x * x * y", "y"));
        //  测试嵌套表达式的导数  
        assertEquals("((1 + 0) * (z + w) + (x + y) * (0 + 0))", Commands.differentiate("(x + y) * (z + w)", "x"));
        assertEquals("((0 + 1) * (z + w) + (x + y) * (0 + 0))", Commands.differentiate("(x + y) * (z + w)", "y"));
    }

    @Test
    public void testSimplify() {
        Map<String, Double> environmentMap = new HashMap<>();
        environmentMap.put("x", 39.5);
        environmentMap.put("y", 2.0);
        
        // 测试数字表达式的简化
        assertEquals("42", Commands.simplify("42", environmentMap));
        // 测试变量表达式的简化
        assertEquals("39.5", Commands.simplify("x", environmentMap));
        assertEquals("Va", Commands.simplify("Va", environmentMap));
        // 测试加法表达式的简化
        assertEquals("(39.5 + (39.5 + Va))", Commands.simplify("x + x + Va", environmentMap));
        assertEquals("81", Commands.simplify("x + x + y", environmentMap));
        // 测试乘法表达式的简化
        assertEquals("39.5 * 39.5 * Va", Commands.simplify("x * x * Va", environmentMap));
        assertEquals("3120.5", Commands.simplify("x * x * y", environmentMap));
        // 测试嵌套表达式的简化
        environmentMap.put("y", 2.0);
        environmentMap.put("x", 0.0);
        assertEquals("2 * (Va + 2)", Commands.simplify("(x + y) * (Va + y)", environmentMap));
        assertEquals("4", Commands.simplify("(y * y) + (x * y)", environmentMap));
    }
}
