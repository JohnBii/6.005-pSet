/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    /** Testing strategy
     *   add()
     *     1. vertex already existed: return false and graph is not modified
     *      observe with vertices(), set() and sources()
     *     2. vertex not exists: return true and add vertex in graph
     *     observe with vertices()
     */
    
    // TODO other tests for instance methods of Graph
    
    @Test
    public void testAdd() {

        final Graph<String> testGraph = emptyInstance();
        final String testString1 = "test1";
        final String testString2 = "test2";
        
        // 2. vertex not exists: return true and add vertex in graph observe 
        // with vertices()
        assertTrue("expected add vertex successfully to empty graph", 
                testGraph.add(testString1));
        assertTrue("expected testString added into graph", 
                testGraph.vertices().contains(testString1));
        
        testGraph.set(testString1, testString2, 5);
        // 1. vertex already existed: return false and graph is not modified 
        // observe with vertices()
        assertFalse("expected fail to add vertex to graph that already "
                + "include the vertex", testGraph.add(testString2));
        assertEquals("expected size of graph not modified", 
                2, testGraph.vertices().size());
        assertEquals("expected edges not modified", Integer.valueOf(5), 
                testGraph.sources(testString2).get(testString1));
        
    }
    
    /** Testing strategy
     *   set()
     *     1. weight is zero
     *          1.1 edge exists
     *              remove the edge, observe with sources()
     *          1.2 edge not exists
     *              graph is not modified, observe with vertices() and sources()
     *     2. weight is not zero, all observe with vertices() and sources()
     *          2.1 edge exists
     *              update edge weight
     *          2.2 edge not exists but vertexes exist
     *              add edge
     *          2.3 edge not exists and both vertexes not exist
     *              add vertexes and edge
     *          2.4 edge not exists and source not exist
     *              add source vertex and edge
     *          2.5 edge not exists and target not extst
     *              add target vertex and edge
     *     test sequence: 2.3->2.4->2.5->2.1->2.2->1.1->1.2  
     */

    @Test
    public void testSet() {
        final Graph<String> testGraph = emptyInstance();
        final String testString1 = "test1";
        final String testString2 = "test2";
        final String testString3 = "test3";
        final String testString4 = "test4";
        
        // 2. weight is not zero
        // 2.3 edge not exists and both vertexes not exist
        assertEquals("expect 0 as edge not exists before", 0, testGraph.set(testString1, testString2, 42));
        assertEquals("expect graph has 2 vertexes", 2, testGraph.vertices().size());
        assertEquals("expect edge(42) set successfully", Integer.valueOf(42), testGraph.sources(testString2).get(testString1));
        
        // 2.4 edge not exists and source not exist
        assertEquals("expect 0 as edge not exists before", 0, testGraph.set(testString3, testString2, 43));
        assertEquals("expect graph has 3 vertexes", 3, testGraph.vertices().size());
        assertEquals("expect edge(43) set successfully", Integer.valueOf(43), testGraph.sources(testString2).get(testString3));
        
        // 2.5 edge not exists and target not exist
        assertEquals("expect 0 as edge not exists before", 0, testGraph.set(testString2, testString4, 44));
        assertEquals("expect graph has 4 vertexes", 4, testGraph.vertices().size());
        assertEquals("expect edge(44) set successfully", Integer.valueOf(44), testGraph.sources(testString4).get(testString2));
        
        // 2.1 edge exists
        assertEquals("expect 42 as edge already existed", 42, testGraph.set(testString1, testString2, 45));
        assertEquals("expect graph has 4 vertexes", 4, testGraph.vertices().size());
        assertEquals("expect edge(45) set successfully", Integer.valueOf(45), testGraph.sources(testString2).get(testString1));
        
        // 2.2 edge not exists but vertexes exist
        assertEquals("expect 0 as edge not exists before", 0, testGraph.set(testString3, testString4, 46));
        assertEquals("expect graph has 4 vertexes", 4, testGraph.vertices().size());
        assertEquals("expect edge(46) set successfully", Integer.valueOf(46), testGraph.sources(testString4).get(testString3));
        
        // weight is zero
        // 1.1 edge exists
        assertEquals("expect 44 as edge already existed", 44, testGraph.set(testString2, testString4, 0));
        assertEquals("expect graph has 4 vertexes", 4, testGraph.vertices().size());
        assertFalse("expect False as there are no edges here", testGraph.sources(testString4).containsKey(testString2));
        
        // 1.2 edge not exists
        assertEquals("expect 0 as edge not exists before", 0, testGraph.set(testString1, testString3, 0));
        assertEquals("expect graph has 4 vertexes", 4, testGraph.vertices().size());
        assertFalse("expect False as there are no edges here", testGraph.sources(testString3).containsKey(testString1));
    }
    
    
    /** Testing strategy
     *   remove()
     *      1. vertex not exists
     *          return false, graph not change;
     *      2. vertex exists
     *          remove all source edges and target edges; remove vertiex; other part of gragh not change
     */
    @Test
    public void testRemove() {
        final Graph<String> testGraph = emptyInstance();
        final String testString1 = "test1";
        final String testString2 = "test2";
        final String testString3 = "test3";
        final String testString4 = "test4";
        
        // test 1. vertex not exists
        assertFalse("Expect False as graph is empty and nothing can be removed", testGraph.remove(testString1));
        
        // test 2. vertex exists
        testGraph.set(testString1, testString2, 1);
        testGraph.set(testString2, testString3, 2);
        testGraph.set(testString3, testString4, 3);
        
        assertTrue("Expect True as remove vertex from graph success", testGraph.remove(testString3));
        assertEquals("Expect graph has 3 vertex after removing", 3, testGraph.vertices().size());
        assertEquals("Expect source edge has also been removed", 0, testGraph.targets(testString2).size());
        assertEquals("Expect target edge has also been removed", 0, testGraph.sources(testString4).size());
        
    }
    
    /** Testing strategy
     *   vertices()
     *      1. graph is empty
     *          return empty set
     *      2. graph has vertices
     *          return set of labels of vertices
     */
    @Test
    public void testVertices() {
        final Graph<String> testGraph = emptyInstance();
        final String testString1 = "test1";
        final String testString2 = "test2";
        final String testString3 = "test3";
        final String testString4 = "test4";
        
        // 1. graph is empty
        assertEquals("Expect empty set since graph is empty", 0, testGraph.vertices().size());
        
        // 2. graph is not empty
        testGraph.set(testString1, testString2, 1);
        testGraph.set(testString2, testString3, 2);
        testGraph.set(testString3, testString4, 3);
        
        assertEquals("Expect set size equals 4", 4, testGraph.vertices().size());
        assertTrue("Expect set of vertices contains vertice 1", testGraph.vertices().contains(testString1));
        assertTrue("Expect set of vertices contains vertice 2", testGraph.vertices().contains(testString2));
        assertTrue("Expect set of vertices contains vertice 3", testGraph.vertices().contains(testString3));
        assertTrue("Expect set of vertices contains vertice 4", testGraph.vertices().contains(testString4));
        
    }
        
    
    /** Testing strategy
     *   sources()
     *      1. no souces
     *          return empty map
     *      2. with souces
     *          return map of souces and correspond weights of those edges
     */
    @Test
    public void testSources() {
        final Graph<String> testGraph = emptyInstance();
        final String testString1 = "test1";
        final String testString2 = "test2";
        final String testString3 = "test3";
        final String testString4 = "test4";
        
        testGraph.set(testString1, testString2, 1);
        testGraph.set(testString2, testString3, 2);
        testGraph.set(testString4, testString3, 4);
        
        // 1. no sources
        assertEquals("Expect empty map of source", 0, testGraph.sources(testString1).size());
        
        // 2. with sources
        assertEquals("Expect size of map of source is 2", 2, testGraph.sources(testString3).size());
        assertEquals("Expect weights of source edge is 2", 2, testGraph.sources(testString3).get(testString2).intValue());
        assertEquals("Expect weights of source edge is 4", 4, testGraph.sources(testString3).get(testString4).intValue());
        
    }
    
    /**
     *  Testing strategy
     *  targets()
     *      1. no targets
     *          return empty map
     *      2. with targets
     *          return map of targets and correspond weights of those edges
     */
    @Test
    public void testTargets() {
        final Graph<String> testGraph = emptyInstance();
        final String testString1 = "test1";
        final String testString2 = "test2";
        final String testString3 = "test3";
        final String testString4 = "test4";
        
        testGraph.set(testString1, testString2, 1);
        testGraph.set(testString2, testString3, 3);
        testGraph.set(testString2, testString4, 4);
        
        // 1. no targets
        assertEquals("Expect empty map of target", 0, testGraph.targets(testString3).size());
        
        // 2. with targets
        assertEquals("Expect size of map of source is 2", 2, testGraph.targets(testString2).size());
        assertEquals("Expect weights of target edge is 3", 3, testGraph.targets(testString2).get(testString3).intValue());
        assertEquals("Expect weights of target edge is 4", 4, testGraph.targets(testString2).get(testString4).intValue());
        
    }
    
}
