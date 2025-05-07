/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    // 1. 测试传入不存在文件时，Creator会不会抛出IO异常
    //      1.1 传入正常的文件，能正常返回新增bridge后的poem
    // 2. poem能否生成正确的poem：
    //      2.1 Corpus为空，poem维持原样
    //      2.2 Corpus只有一个单词时，poem维持原样
    //      2.3 Corpus 中存在单词a和单词d之间，有两个单词b和单词c，两边的线路有不一样的weight，取weight较高的中间词
    //      2.4 Corpus 中，能让原String在不同的位置（2个或以上位置）分别增加中间词
    //      2.5 input string中，有大写的单词，也需要在中间放上连接词，且大小写维持原状
    //      2.6 在corpus中，有大小写不同的同一个单词，应该被视为同一个vertice
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    // 1. 测试传入不存在文件时，Creator会不会抛出IO异常
    @Test
    public void testIncorrectCorpusFile() {
        assertThrows(IOException.class, () -> new GraphPoet(new File("test/poet/notExistFile.txt")));
    }
    
    // 1.1
    @Test
    public void testCorrectFile() throws IOException {
        GraphPoet testGraph = new GraphPoet(new File("test/poet/working hard.txt"));
        assertEquals("Expect output the right poem",
                "There is no development between then and now",
                testGraph.poem("There is development between then and now"));
    }
    
    // 2.1
    @Test
    public void testEmptyCorpus() {
        GraphPoet testGraph = new GraphPoet("");
        assertEquals("Expect poem does not change", 
                "To be or not to be, that is a question.",
                testGraph.poem("To be or not to be, that is a question."));
    }
    
    // 2.2
    @Test
    public void testOneWordCorpus() {
        GraphPoet testGraph = new GraphPoet("you");
        assertEquals("Expect poem does not change",
                "I have asked you about this question.",
                testGraph.poem("I have asked you about this question."));
    }
    
    // 2.3
    @Test
    public void testHigherWeightBridge() {
        GraphPoet testGraph = new GraphPoet(
                "an apple a day, keep doctors a face. an apple a month, keep father a punch. an orange a day, keep father a pay");
        assertEquals("Expect poem added a new bridge with the highest way",
                "Keep father a secret for me.",
                testGraph.poem("Keep a secret for me."));
    }
    
    // 2.4
    @Test
    public void testBridgeInDifferentPlace() {
        GraphPoet testGraph = new GraphPoet(
                "Simple collaborative editing without the formatting complexity of markdown");
        assertEquals("Expect poem added 2 new bridge words",
                "Simple collaborative editing with the formatting complexity of markdown",
                testGraph.poem("Simple editing with the formatting of markdown"));
    }
    
    // 2.5
    public void testCaseInsensitive() {
        GraphPoet testGraph = new GraphPoet(
                "Simple. And let me help you.");
        assertEquals("Expect poem bridge correctly no matter what case of words in input string",
                "Simple. And let me do that",
                testGraph.poem("Simple. let me do that"));
    }
    
    // 2.6
    public void testCorpusCaseInsensitive() {
        GraphPoet testGraph = new GraphPoet(
                "Apple on hand. apple in hand. apple in hand. Apple on hand. apple on hand.");
        assertEquals("Expect poem added bridge with the highest weight",
                "Apple on hand.",
                testGraph.poem("Apple hand."));
    }
}
