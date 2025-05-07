/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph. Graph是一个加权有向图
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //  vertices是graph中所有vertice的集合
    //  edges是graph中所有edge的列表，其中，每个Edge都会有起点和终点，分别对应vertices中两个不同的vertice
    //  
    //      
    // Representation invariant:
    //  对于每个Edge, Edge.source 和 Edge.target 都在 vertices 中
    //      
    // Safety from rep exposure:
    //  vertices和edges都是private变量，外部无法访问；即使使用接口的method，也只能向vertices添加不重复的String，向edges中添加Edge
    //  
    
    /*
     * constructor 创建一个空的加权有向Graph
     */
    public ConcreteEdgesGraph() {}
    
    /*
     * checkRep
     * 检查每个edge的source和target是否都在vertices中，检查每个edge的source和target是否不同；
    */
    private void checkRep() {
        for (Edge<L> edge : edges) {
            assert vertices.contains(edge.getSource()) : 
                "Edge's source [" + edge.getSource() + "] not found in vertices";
            assert vertices.contains(edge.getTarget()) : 
                "Edge's target [" + edge.getTarget() + "] not found in vertices";
        }   
    }
    
    @Override public boolean add(L vertex) {
        if (vertex == null) throw new IllegalArgumentException("Vertix must not be null");
        return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
        
        if (source == null || target == null ||  weight <0) {
            throw new IllegalArgumentException("Invalid arguments for Edge");
        }
        
        int previousWeight = 0;
        Iterator<Edge<L>> iterator = edges.iterator();
        
        while(iterator.hasNext()) {
            Edge<L> edge = iterator.next();
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                previousWeight = edge.getWeight();
                iterator.remove();
                break;
            }
        }
        
        if (weight > 0) {
            vertices.add(source);
            vertices.add(target);
            edges.add(new Edge<L>(source, target, weight));
        }
        
        checkRep();
        return previousWeight;
    }
    
    @Override public boolean remove(L vertex) {
        if (vertex == null) throw new IllegalArgumentException("Vertex cannot be null");
        
        Iterator<Edge<L>> iterator = edges.iterator();
        while(iterator.hasNext()) {
            Edge<L> edge = iterator.next();
            if (edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)) {
                iterator.remove();
            }
        }
        
        boolean result = vertices.remove(vertex);
        checkRep();
        return result;
    }
    
    @Override public Set<L> vertices() {
        return new HashSet<>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        if (target == null) throw new IllegalArgumentException("Target cannot be null");
        Map<L, Integer> result = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.getTarget().equals(target)) {
                result.put(edge.getSource(), edge.getWeight());
            }
        }
        return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        if (source == null) throw new IllegalArgumentException("Source cannot be null");
        Map<L, Integer> result = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.getSource().equals(source)) {
                result.put(edge.getTarget(), edge.getWeight());
            }
        }
        return result;
    }
    
    // TODO toString()
    // 按vertice展示每个vertice的edge
    @Override public String toString() {
        StringBuilder result = new StringBuilder("ConcreteEdgesGraph{ \n");
        
        List<L> verticesList = new ArrayList<> (vertices);
        verticesList.sort(null);
        Iterator<L> verticeIterator = verticesList.iterator();
        while(verticeIterator.hasNext()) {
            L vertice = verticeIterator.next();
            result.append("{Source: " + vertice.toString() + ", Target: ");
            for (Map.Entry<L, Integer> entry : targets(vertice).entrySet()) {
                result.append("(" + entry.getKey().toString() + ", " + entry.getValue() + "), ");
            }
            result.append("}\n");
            
        }
        
        return result.append("}").toString();
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
    private final L source;
    private final L target;
    private final Integer weight;
    
    // Abstraction function:
    //   表示加权有向图中的一条线，source代表这条线的起点，target代表这条线的终点，weiget是这条线的权重
    //   Edge = {source, target, weight}
    //
    // Representation invariant:
    //   weight > 0
    //
    // Safety from rep exposure:
    //   fields 都是private变量，外部无法访问，且都是final，一旦定义就不能变更
    
    // TODO constructor
    public Edge(L source, L target, Integer weight) {
        if (source == null || target == null || weight == null || weight <=0) {
            throw new IllegalArgumentException("Invalid arguments for Edge");
        }
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }
    // TODO checkRep
    private void checkRep() {
        assert source != null : "Source cannot be null";
        assert target != null : "Target cannot be null";
        assert weight != null : "Weight cannot be null";
        assert weight > 0 : "Weight must be greater than 0";
    }
    // TODO methods
    public L getSource() {
        return source;
    }
    
    public L getTarget() {
        return target;
    }
    
    public Integer getWeight() {
        return weight;
    }
    
    // TODO toString()
    @Override
    public String toString() {
        return "Edge{" + 
                "source='" + source.toString() + '\'' +
                ", target='" + target.toString() + '\'' +
                ", weight=" + weight.toString() +
                "}";
    }
}
