/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import graph.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;

/**
 * A graph-based poetry generator.
 * 
 * <p>
 * GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph. Vertices in the graph are words. Words are defined as
 * non-empty case-insensitive strings of non-space non-newline characters. They
 * are delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     Hello, HELLO, hello, goodbye!
 * </pre>
 * <p>
 * the graph would contain two edges:
 * <ul>
 * <li>("hello,") -> ("hello,") with weight 2
 * <li>("hello,") -> ("goodbye!") with weight 1
 * </ul>
 * <p>
 * where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>
 * Given an input string, GraphPoet generates a poem by attempting to insert a
 * bridge word between every adjacent pair of words in the input. The bridge
 * word between input words "w1" and "w2" will be some "b" such that w1 -> b ->
 * w2 is a two-edge-long path with maximum-weight weight among all the
 * two-edge-long paths from w1 to w2 in the affinity graph. If there are no such
 * paths, no bridge word is inserted. In the output poem, input words retain
 * their original case, while bridge words are lower case. The whitespace
 * between every word in the poem is a single space.
 * 
 * <p>
 * For example, given this corpus:
 * 
 * <pre>
 *     This is a test of the Mugar Omni Theater sound system.
 * </pre>
 * <p>
 * on this input:
 * 
 * <pre>
 *     Test the system.
 * </pre>
 * <p>
 * the output poem would be:
 * 
 * <pre>
 *     Test of the system.
 * </pre>
 * 
 * <p>
 * PS2 instructions: this is a required ADT class, and you MUST NOT weaken the
 * required specifications. However, you MAY strengthen the specifications and
 * you MAY add additional methods. You MUST use Graph in your rep, but otherwise
 * the implementation of this class is up to you.
 */
public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    // Abstraction function:
    // each Vertice in Graph：都是出现在corpus File中的一个单词，且corpus中出现的每一个不同的单词都会成为一个vertice
    // each Edge in Graph: 表示起点vertice和终点vertice在corpus File中相邻出现的次数
    //
    // Representation invariant:
    // all Edges should be positive
    // no replicate vertices
    // 同一个起点和终点只会有一个Edge
    //
    // Safety from rep exposure:
    // Graph is private and final, cannot be modified outside package
    // 只有poem有output，并且output是String

    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        // 使用 try-with-resources自动关闭资源
        try (BufferedReader reader = new BufferedReader(new FileReader(corpus));
                Scanner scanner = new Scanner(reader)) {

            scanner.useDelimiter("\\s+|\\R");
            String previousVertice = null;
            while (scanner.hasNext()) {
                String currentVertice = scanner.next().trim().toLowerCase();
                if (currentVertice.isEmpty())
                    continue;

                graph.add(currentVertice);

                if (previousVertice != null) {
                    int previousWeight = graph.set(previousVertice, currentVertice, 1);
                    graph.set(previousVertice, currentVertice, previousWeight + 1);
                }
                previousVertice = currentVertice;
            }
        }
    }

    /**
     * Another creator that create the graph poet from a string for convenient
     * testing
     * 
     * @param corpus
     */
    public GraphPoet(String corpus) {
        // 使用 try-with-resources自动关闭资源
        try (Scanner scanner = new Scanner(corpus)) {

            scanner.useDelimiter("\\s+|\\R");
            String previousVertice = null;
            while (scanner.hasNext()) {
                String currentVertice = scanner.next().trim().toLowerCase();
                if (currentVertice.isEmpty())
                    continue;

                graph.add(currentVertice);

                if (previousVertice != null) {
                    int previousWeight = graph.set(previousVertice, currentVertice, 0);
                    graph.set(previousVertice, currentVertice, previousWeight + 1);
                }
                previousVertice = currentVertice;
            }
        }
    }

    // TODO checkRep
    private void checkRep() {
    }

    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        try (Scanner scanner = new Scanner(input)) {

            scanner.useDelimiter("\\s+|\\R");
            String previousWord = null;
            StringBuilder resultBuilder = new StringBuilder();
            
            while (scanner.hasNext()) {
                String currentWord = scanner.next().trim();
                if (currentWord.isEmpty())
                    continue;

                // 处理bridge word 逻辑
                if (previousWord != null) {
                    appendBridgeWord(previousWord, currentWord, resultBuilder);
                    resultBuilder.append(" ");
                }

                resultBuilder.append(currentWord);
                previousWord = currentWord;
            }

            return resultBuilder.toString().trim();
        }
    }

    
    private void appendBridgeWord(String previous, String current, StringBuilder builder) {
        String bridge = findOptimalBridge(
                previous.toLowerCase(),
                current.toLowerCase());
        
        if (bridge != null) {
            builder.append(' ').append(bridge);
        }
    }
    
    private String findOptimalBridge(String prevLower, String currLower) {
        if (!isVertexValid(currLower) || !isVertexValid(prevLower)) return null;
        
        Map<String, Integer> targets = graph.targets(prevLower);
        Map<String, Integer> sources = graph.sources(currLower);
        
        return getMaxWeightBridge(targets, sources);
    }
    
    
    /**
     * @param targets
     * @param sources
     * @return
     */
    private String getMaxWeightBridge(Map<String, Integer> targets, Map<String, Integer> sources) {
        Set<String> candidates = new HashSet<>(targets.keySet());
        candidates.retainAll(sources.keySet());     // 筛选出 targets 和 sources中相同的 key 的 Set
        
        return candidates.stream().                 // 从所有 bridge 中，比较出拥有最大 weight 的 bridge
                max(Comparator.comparingInt(bridge ->
                targets.getOrDefault(bridge, 0) +
                sources.getOrDefault(bridge, 0)
                ))
                .orElse(null);
    }
    
    
    /*
     * 我自己写的方法
     * private String lookForBridge(String current, String previous) { if
     * (!isVertexValid(current) || !isVertexValid(previous)) { return null; }
     * 
     * Map<String, Integer> targets = graph.targets(previous); 
     * Map<String, Integer> sources = graph.sources(current);
     * Set<String> targetSet = targets.keySet();
     * Set<String> sourceSet = sources.keySet();
     * 
     * Set<String> bridgeSet =
     *      targetSet.stream().filter(sourceSet::contains).collect(Collectors.toSet());
     * 
     * String finalBridge = null;
     * int maxWeight = 0;
     * for(String bridge : bridgeSet) {
     *      int bridgeWeight = targets.get(bridge) + sources.get(bridge);
     *      if (bridgeWeight > maxWeight) { 
     *          maxWeight = bridgeWeight; finalBridge = bridge;
     * }}
     * 
     *  return finalBridge;
     * }
     */
    
    /**
     * @param vertex
     * @return
     */
    private boolean isVertexValid(String vertex) {
        return graph.vertices().contains(vertex);
    }

    
    // TODO toString()
    @Override
    public String toString() {
        return graph.toString();
    }
}
