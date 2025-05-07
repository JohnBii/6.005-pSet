/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

    private final List<Vertex<L>> vertices = new ArrayList<>();

    // Abstraction function:
    /**
     * vertiex List 是整个Graph中所有vertex的集合，vertex之间的关系保存在每个vertex中： vertex1 = vertex
     * value + sources List（包括source的string和source到vertex的weight） + targets
     * List（包括target的string和vertex到target的weight）
     * 
     */
    // Representation invariant:
    // Verttices 中不能有重复的vertex
    // Safety from rep exposure:
    // verteces是final，无法被变更；
    // 返回整个vertex列表时，复制一份再返回，而不会返回原本的vertices list

    // TODO constructor
    public ConcreteVerticesGraph() {}

    // TODO checkRep
    private void checkRep() {
        HashSet<L> setOfVertexValue = new HashSet<>();
        for (Vertex<L> vertex : vertices) {
            assert setOfVertexValue.add(vertex.getValue()) : "List of Vertices contains duplicate vertex";
            break;
        }
        
    }

    @Override
    public boolean add(L vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("vertex value cannot be null");
        }
        
        boolean returnValue = true;
        if (vertices.stream().anyMatch(v -> v.getValue().equals(vertex))) {
            returnValue = false;
        } else {
            vertices.add(new Vertex<L>(vertex));
            returnValue = true;
        }
        checkRep();
        return returnValue;
    }

    @Override
    public int set(L source, L target, int weight) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Invalid input value");
        }
        Integer returnValue = 0;
        boolean findSource = false; boolean findTarget = false;
        for (Vertex<L> vertex : vertices) {
            if (vertex.getValue().equals(source)) {
                findSource = true;
                if (weight == 0) {
                    returnValue = vertex.removeTarget(target);
                } else {
                    returnValue = vertex.setTarget(target, weight);
                }
            } else if (vertex.getValue().equals(target)) {
                findTarget = true;
                if (weight == 0) {
                    returnValue = vertex.removeSource(source);
                } else {
                    returnValue = vertex.setSource(source, weight);
                }
            }
        }
        
        if (!findSource) {
            Vertex<L> vertex = new Vertex<L>(source);
            vertex.setTarget(target, weight);
            vertices.add(vertex);
        }
        if (!findTarget) {
            Vertex<L> vertex = new Vertex<L>(target);
            vertex.setSource(source, weight);
            vertices.add(vertex);
        }
        
        checkRep();
        return Optional.ofNullable(returnValue).orElse(0);
    }

    @Override
    public boolean remove(L vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Invalid input value");
        }
        boolean returnValue = false;
        Vertex<L> removing = findVertex(vertex);
        if (removing != null) {
            returnValue = true;
            Set<L> sources = removing.getSources();
            Set<L> targets = removing.getTargets();
            for (Vertex<L> currentVertex : vertices) {
                if (sources.contains(currentVertex.getValue())) {
                    currentVertex.removeTarget(vertex);
                }
                if (targets.contains(currentVertex.getValue())) {
                    currentVertex.removeSource(vertex);
                }
            }
            vertices.remove(removing);
        }
        
        checkRep();
        return returnValue;
    }

    @Override
    public Set<L> vertices() {
        Iterator<Vertex<L>> iterator = vertices.iterator();
        Set<L> returnValue = new HashSet<>();
        while(iterator.hasNext()) {
            returnValue.add(iterator.next().getValue());
        }
        
        checkRep();
        return returnValue;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        if (target == null) {
            throw new IllegalArgumentException("Invalid input value");
        }
        Vertex<L> vertex = findVertex(target);
        Map<L, Integer> returnValue = new HashMap<>();
        for (L source : vertex.getSources()) {
            returnValue.put(source, vertex.sourceWeight(source));
        }
        
        checkRep();
        return returnValue;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        if (source == null) {
            throw new IllegalArgumentException("Invalid input value");
        }
        Vertex<L> vertex = findVertex(source);
        Map<L, Integer> returnValue = new HashMap<>();
        for (L target : vertex.getTargets()) {
            returnValue.put(target, vertex.targetWeight(target));
        }
        
        checkRep();
        return returnValue;
    }

    // TODO toString()
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("ConcreteVerticesGraph{ \n");
        for(Vertex<L> vertex : vertices) {
            result.append("{Source: " + vertex.getValue() + ", Target: ");
            for(L target : vertex.getTargets()) {
                result.append("(" + target + ", " + vertex.targetWeight(target).toString() + "), ");
            }
            result.append("}\n");
        }
        result.append("}");
        return result.toString();
    }
    
    /**
     * 根据value找到graph中对应的vertex，如果找不到则返回null
     * 
     * @param value 查询的vertex的value值
     * @return 返回找到的vertex，如果没找到则返回null
     */
    private Vertex<L> findVertex(L value) {
        Vertex<L> returnValue = null;
        for (Vertex<L> currentVertex : vertices) {
            if (currentVertex.getValue().equals(value) ) {
                returnValue = currentVertex;
            }
        }
        
        checkRep();
        return returnValue;
    }
    
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 表示有向图中的节点（Vertice），每个Vertice中包含它的所有targets和sources，并且包含它到其他节点的edge的weight
 * 
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

    // TODO fields
    private L value;
    private final Map<L, Integer> targets;
    private final Map<L, Integer> sources;

    // Abstraction function:
    // Vertex 表示有向图中的节点, value是vertex的节点内容
    // self.targets = {target1:weight1, target2:weight2, ...},
    // 表示该节点的下游target组合，其中，该节点到targetX的edge的权重为weightX；
    // self.souces = {source1:weight1, source2:weight2, ...},
    // 表示该节点的上游source组合，其中，sourceX到该节点的edge的权重为weightX;
    //
    // Representation invariant:
    // targets 和 sources 中的 weight 须>0;
    //
    // Safety from rep exposure:
    // 所有 field in this class is private；
    // value 是 String，不能被改变
    // 返回的 targets 和 sources 都应转换为其他集合或者复制一份再返回；

    // TODO constructor

    /**
     * 创建Vertex
     * 
     * @param value Vertex本身的值
     * @throws IllegalArgumentException 当输入的value为null
     */
    Vertex(L value) {
        if (value == null) {
            throw new IllegalArgumentException("Invalid arguments for Vertex");
        }
        this.value = value;
        targets = new HashMap<>();
        sources = new HashMap<>();
        checkRep();
    }

    // TODO checkRep
    private void checkRep() {

        // targets 和 sources 中的 weight 须>0;
        assert targets.values().stream().allMatch(value -> value != null && value > 0)
                : "One of the weights in targets equals null or not positive";
        assert sources.values().stream().allMatch(value -> value != null && value > 0)
                : "One of the weights in sources equals null or not positive";

    }

    // TODO methods

    /**
     * 获取Vertex的值
     * 
     * @return value Vertex的值
     */
    L getValue() {
        return value;
    }

    /**
     * 为Vertex设置target，如果该target已经存在，则用新weight值覆盖原来的weight
     * 
     * @param target 新的target
     * @param weight vertex到新target的weight
     * @return 如果target已经存在，则返回原来的weight；如果target原本不存在，则返回null
     * @throws IllegalArgumentException 当输入的target或weight不符合要求时，将产生错误
     */
    Integer setTarget(L target, Integer weight) {
        if (target == null || weight == null || weight <= 0) {
            throw new IllegalArgumentException("Invalid arguments for Vertex.setTarget()");
        }

        Integer returnValue = targets.put(target, weight);
        checkRep();
        return returnValue;
    }

    /**
     * 为Vertex设置source，如果该source已经存在，则用新weight值覆盖原来的weight
     * 
     * @param source 新的source
     * @param weight 新source到vertex的weight
     * @return 如果source已经存在，则返回原来的weight；如果source原本不存在，则返回null
     * @throws IllegalArgumentException 当输入的source或weight不符合要求时，将产生错误
     */
    Integer setSource(L source, Integer weight) {
        if (source == null || weight == null || weight <= 0) {
            throw new IllegalArgumentException("Invalid arguments for Vertex.setSource()");
        }
        Integer returnValue = sources.put(source, weight);
        checkRep();
        return returnValue;
    }

    /**
     * 检查target是否在vertex的targets集合中
     * 
     * @param target 待检查的vertex值
     * @return true如果target在vertex的targets集合中；否则为false
     */
    boolean containTarget(L target) {
        return targets.containsKey(target);
    }

    /**
     * 检查source是否在vertex的sources集合中
     * 
     * @param source 待检查的vertex值
     * @return true如果source在vertex的sources集合中；否则为false
     */
    boolean containSource(L source) {
        return sources.containsKey(source);
    }

    /**
     * 返回当前vertex到这个target的weight
     * 
     * @param target 需要查询weight的target
     * @return 当前vertex到这个target的值；如果当前vertex与这个target没有联系，则返回null；
     */
    Integer targetWeight(L target) {
        return targets.get(target);
    }

    /**
     * 返回source到当前vertex的weight
     * 
     * @param source 需要查询weight的source
     * @return 如果source到当前vertex有联系，则返回source到当前vertex的weight；否则返回null；
     */
    Integer sourceWeight(L source) {
        return sources.get(source);
    }

    /**
     * 从当前vertex的targets集合中，去除target
     * 
     * @param target 待去除的target
     * @return 如果vertex的targets集合中确实有target，则返回target被删除前，vertex到target的weight；如果vertex的targets集合中没有target，则返回null；
     */
    Integer removeTarget(L target) {
        Integer returnValue = targets.remove(target);
        checkRep();
        return returnValue;
    }

    /**
     * 从当前vertex的sources集合中，去除source
     * 
     * @param source 待去除的source
     * @return 如果vertex的sources集合中确实有source，则返回source被删除前，source到vertex的weight；如果vertex的sources集合中没有source，则返回null；
     */
    Integer removeSource(L source) {
        Integer returnValue = sources.remove(source);
        checkRep();
        return returnValue;
    }

    /**
     * 获取当前vertex的所有targets的集合，采取Set<String>的形式
     * 
     * @return 以Set<String> 的形式，返回vertex的所有target的集合
     */
    Set<L> getTargets() {
        return new HashSet<>(targets.keySet());
    }

    /**
     * 获取当前vertex的所有sources的集合，采取Set<String>的形式
     * 
     * @return 以Set<String>的形式，返回vertex的所有source的集合
     */
    Set<L> getSources() {
        return new HashSet<>(sources.keySet());
    }

    // TODO toString()
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Vertex{ \n");
        result.append("{value: '" + value + "'}\n");
        result.append("{Target: ");
        
        List<L> targetsList = new ArrayList<>(targets.keySet());
        targetsList.sort(null);
        for(L target : targetsList) {
            result.append("(" + target + ", " + targetWeight(target).toString() + "), ");
        }
        result.append("}\n");
        
        result.append("{Source: ");
        List<L> sourcesList = new ArrayList<>(sources.keySet());
        sourcesList.sort(null);
        for(L source : sourcesList) {
            result.append("(" + source + ", " + sourceWeight(source).toString() + "), ");
        }
        result.append("}\n");
        
        result.append("}");
        return result.toString();
    }
    
}
