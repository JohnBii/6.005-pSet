/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }

    /*
     * Testing ConcreteVerticesGraph...
     */

    // Testing strategy for ConcreteVerticesGraph.toString()
    // 1. 测试空graph的toString
    // 2. 测试只有vertices的toString
    // 3. 测试既有vertice也有edge的toString

    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();

        // 1. 测试空graph的toString
        assertEquals("Expect empty concreteVerticesGraph", graph.toString(), "ConcreteVerticesGraph{ \n}");

        // 2. 测试只有vertices的toString
        graph.add("vertex1");
        graph.add("vertex2");
        graph.add("vertex3");
        assertEquals("Expect only vertices", graph.toString(),
                "ConcreteVerticesGraph{ \n" + "{Source: vertex1, Target: }\n" + "{Source: vertex2, Target: }\n"
                        + "{Source: vertex3, Target: }\n" + "}");

        // 3. 测试既有vertices也有edge的toString
        graph.set("vertex1", "vertex2", 20);
        graph.set("vertex2", "vertex3", 10);
        assertEquals("Expect vertices and edges", graph.toString(),
                "ConcreteVerticesGraph{ \n" + "{Source: vertex1, Target: (vertex2, 20), }\n"
                        + "{Source: vertex2, Target: (vertex3, 10), }\n" + "{Source: vertex3, Target: }\n" + "}");

    }
    /*
     * Testing Vertex...
     */

    // Testing strategy for Vertex:
    // create()
    // value = null
    // normal value
    // getValue()
    // normal value
    // setTarget()
    // 2. try to set the weight less than or equals 0
    // 4. target already exists
    // 5. target not exists
    // setSource()
    // 2. try to set the weight less than or equals 0
    // 4. source already exists
    // 5. source not exists
    // containTarget()
    // 1. get true target
    // 2. get false target
    // containSource()
    // 1. get true source
    // 2. get false source
    // targetWeight()
    // 1. get true target weight
    // 2. get false target weight
    // sourceWeight()
    // 1. get true source weight
    // 2. get false source weight
    // removeTarget()
    // 1. remove the target not exists
    // 2. remove the target already exists
    // removeSource()
    // 1. remove the source not exists
    // 2. remove the source already exists
    // getTargets()
    // 1. no targets
    // 2. with targets
    // getSources()
    // 1. no sources
    // 2. with sources
    // toString()
    // 1. no source, no target
    // 2. with source, with target

    // TODO tests for operations of Vertex
    @Test
    public void testVertexConstructor() {
        // test value == null
        assertThrows(IllegalArgumentException.class, () -> new Vertex<>(null));
    }

    @Test
    public void testGetValue() {
        Vertex<String> vertex = new Vertex<>("vertex");
        assertEquals("Expect correct string of vertex", "vertex", vertex.getValue());
    }

    @Test
    public void testSetTarget() {
        Vertex<String> tiger = new Vertex<>("tiger");


        // set weight less than or equals 0
        assertThrows(IllegalArgumentException.class, () -> tiger.setTarget("leopard", -6));
        assertThrows(IllegalArgumentException.class, () -> tiger.setTarget("leopard", 0));

        // when setting target that not exists, set the target and weight
        tiger.setTarget("rabbit", 99);
        assertEquals("expect to get the correct target's weight", Integer.valueOf(99), tiger.targetWeight("rabbit"));

        // when setting target that already exists, change the weight
        tiger.setTarget("rabbit", 88);
        assertEquals("expect to get the correct target's weight", Integer.valueOf(88), tiger.targetWeight("rabbit"));

    }

    @Test
    public void testSetSource() {
        Vertex<String> tiger = new Vertex<>("tiger");

        // set weight less than or equals 0
        assertThrows(IllegalArgumentException.class, () -> tiger.setSource("leopard", -6));
        assertThrows(IllegalArgumentException.class, () -> tiger.setSource("leopard", 0));

        // when setting source that not exists, set the source and weight
        tiger.setSource("rabbit", 99);
        assertEquals("expect to get the correct source's weight", Integer.valueOf(99), tiger.sourceWeight("rabbit"));

        // when setting source that already exists, change the weight
        tiger.setSource("rabbit", 88);
        assertEquals("expect to get the correct source's weight", Integer.valueOf(88), tiger.sourceWeight("rabbit"));
    }

    @Test
    public void testContainTarget() {
        Vertex<String> lion = new Vertex<>("lion");

        lion.setTarget("deer", 20);
        assertFalse("Expect target not exists", lion.containTarget("bear"));
        assertTrue("Expect target exists", lion.containTarget("deer"));
    }

    @Test
    public void testContainSource() {
        Vertex<String> lion = new Vertex<>("lion");

        lion.setSource("deer", 20);
        assertFalse("Expect source not exists", lion.containSource("bear"));
        assertTrue("Expect source exists", lion.containSource("deer"));
    }

    @Test
    public void testTargetWeight() {
        Vertex<String> lion = new Vertex<>("lion");

        lion.setTarget("deer", 20);
        assertEquals("Expect null when target not exists", null, lion.targetWeight("bear"));
        assertEquals("Expect weight is 20 when target exists", Integer.valueOf(20), lion.targetWeight("deer"));
    }

    @Test
    public void testSourceWeight() {
        Vertex<String> lion = new Vertex<>("lion");

        lion.setSource("deer", 20);
        assertEquals("Expect null when source not exists", null, lion.sourceWeight("bear"));
        assertEquals("Expect weight is 20 when source exists", Integer.valueOf(20), lion.sourceWeight("deer"));
    }

    @Test
    public void testRemoveTarget() {
        Vertex<String> lion = new Vertex<>("lion");

        lion.setSource("elephant", 100);
        lion.setTarget("tiger1", 91);
        lion.setTarget("tiger2", 92);
        lion.setTarget("tiger3", 93);

        lion.removeTarget("dog");
        assertEquals("Expect the source set be the same as before after removing an non-exist target",
                new HashSet<>(Arrays.asList("elephant")), lion.getSources());
        assertEquals("Expect the target set be the same as before after removing an non-exist target",
                new HashSet<>(Arrays.asList("tiger1", "tiger2", "tiger3")), lion.getTargets());

        lion.removeTarget("tiger2");
        assertEquals("Expect the source set be the same as before after removing an existing target",
                new HashSet<>(Arrays.asList("elephant")), lion.getSources());
        assertEquals("Expect the target set changed after removing an existing target",
                new HashSet<>(Arrays.asList("tiger1", "tiger3")), lion.getTargets());
    }

    @Test
    public void testRemoveSource() {
        Vertex<String> lion = new Vertex<>("lion");

        lion.setTarget("tiger", 100);
        lion.setSource("elephant1", 91);
        lion.setSource("elephant2", 92);
        lion.setSource("elephant3", 93);

        lion.removeSource("dog");
        assertEquals("Expect the target set be the same as before after removing an non-exist source",
                new HashSet<>(Arrays.asList("tiger")), lion.getTargets());
        assertEquals("Expect the source set be the same as before after removing an non-exist source",
                new HashSet<>(Arrays.asList("elephant1", "elephant2", "elephant3")), lion.getSources());

        lion.removeSource("elephant2");
        assertEquals("Expect the target set be the same as before after removing an existing source",
                new HashSet<>(Arrays.asList("tiger")), lion.getTargets());
        assertEquals("Expect the source set changed after removing an existing source",
                new HashSet<>(Arrays.asList("elephant1", "elephant3")), lion.getSources());
    }

    @Test
    public void testGetTargets() {
        Vertex<String> lion = new Vertex<>("lion");

        assertEquals("Expect empty target set", new HashSet<>(Arrays.asList()), lion.getTargets());

        lion.setSource("elephant", 100);
        lion.setTarget("tiger1", 91);
        lion.setTarget("tiger2", 92);
        lion.setTarget("tiger3", 93);
        assertEquals("Expect correct target set", new HashSet<>(Arrays.asList("tiger1", "tiger2", "tiger3")),
                lion.getTargets());
    }

    @Test
    public void testGetSources() {
        Vertex<String> lion = new Vertex<>("lion");
        assertEquals("Expect empty source set", new HashSet<>(Arrays.asList()), lion.getSources());

        lion.setTarget("tiger", 100);
        lion.setSource("elephant1", 91);
        lion.setSource("elephant2", 92);
        lion.setSource("elephant3", 93);
        assertEquals("Expect correct source set", new HashSet<>(Arrays.asList("elephant1", "elephant2", "elephant3")),
                lion.getSources());
    }

    @Test
    public void testVertexToString() {
        Vertex<String> lion = new Vertex<>("lion");

        assertEquals("expect empty source and target string of vertex",
                "Vertex{ \n{value: 'lion'}\n{Target: }\n{Source: }\n}", lion.toString());
        lion.setSource("elephant1", 91);
        lion.setSource("elephant2", 92);
        lion.setSource("elephant3", 93);
        lion.setTarget("tiger1", 91);
        lion.setTarget("tiger2", 92);
        lion.setTarget("tiger3", 93);
        assertEquals("expect right source and target string of vertex",
                "Vertex{ \n{value: 'lion'}\n" + "{Target: (tiger1, 91), (tiger2, 92), (tiger3, 93), }\n"
                        + "{Source: (elephant1, 91), (elephant2, 92), (elephant3, 93), }\n}",
                lion.toString());
    }
}
