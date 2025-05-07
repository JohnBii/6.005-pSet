/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   1. 测试空graph的toString
    //   2. 测试只有vertices的toString
    //   3. 测试既有vertice也有edge的toString
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();
        
        // 1. 测试空graph的toString
        assertEquals("Expect empty ConcreteEdgesGraph", "ConcreteEdgesGraph{ \n}", graph.toString());
        
        // 2. 测试只有vertices的toString
        graph.add("vertice1");
        assertEquals("Expect one vertice graph", "ConcreteEdgesGraph{ \n{Source: vertice1, Target: }\n}", graph.toString());
        
        //3. 测试既有vertice也有edge的toString
        graph.set("vertice2", "vertice3", 100);
        assertEquals("Expect complete graph", 
                "ConcreteEdgesGraph{ \n{Source: vertice1, Target: }\n"
                + "{Source: vertice2, Target: (vertice3, 100), }\n"
                + "{Source: vertice3, Target: }\n}", graph.toString());
        
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // create()
    //      2. source or target or weight is null cannot be created
    //      3. weight less than 1 cannot be created
    // getTarget()
    //      get the right target
    // getSource()
    //      get the right source
    // getWeight()
    //      get the right weight
    // toString()
    
    // TODO tests for operations of Edge
    @Test
    public void testEdgeConstructor() {
        
        // test weight = 0
        assertThrows(IllegalArgumentException.class, () ->
        new Edge<String>("vertice1", "vertice2", 0));
        
        // test weight is less than 0
        assertThrows(IllegalArgumentException.class, () ->
        new Edge<String>("vertice1", "vertice2", -43));
        
        // test source is null
        assertThrows(IllegalArgumentException.class, () ->
        new Edge<String>(null, "vertice1", 98));
        
        // test target is null
        assertThrows(IllegalArgumentException.class, () ->
        new Edge<String>("vertice1", null, 98));
        
        // test weight is null
        assertThrows(IllegalArgumentException.class, () ->
        new Edge<String>("vertice1", "vertice2", null));
        
    }
    
    @Test
    public void testEdgeGetTarget() {
        Edge<String> edge = new Edge<>("vertice1", "vertice2", 101);
        assertEquals("Expect target to be vertice2", "vertice2", edge.getTarget());
    }
    
    @Test
    public void testEdgeGetSource() {
        Edge<String> edge = new Edge<>("vertice1", "vertice2", 101);
        assertEquals("Expect source to be vertice1", "vertice1", edge.getSource());
    }
    
    @Test
    public void testEdgeGetWeight() {
        Edge<String> edge = new Edge<>("vertice1", "vertice2", 101);
        assertEquals("Expect weight to be 101", 101, edge.getWeight().intValue());
    }
    
    @Test
    public void testEdgeToString() {
        Edge<String> edge = new Edge<>("vertice1", "vertice2", 101);
        assertEquals("Not get the expected string from the edge", "Edge{source='vertice1', target='vertice2', weight=101}", edge.toString());
    }
    
}
