package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class concreteGraph implements Graph<String> {
    private final Map<String, Map<String, Integer>> adjList = new HashMap<>();

    @Override
    public boolean addVertex(String vertex) {
        if (adjList.containsKey(vertex)) return false;
        adjList.put(vertex, new HashMap<>());
        return true;
    }

    @Override
    public boolean removeVertex(String vertex) {
        if (!adjList.containsKey(vertex)) return false;
        adjList.remove(vertex);
        for (Map<String, Integer> edges : adjList.values()) {
            edges.remove(vertex);
        }
        return true;
    }

    @Override
    public boolean addEdge(String source, String destination, int weight) {
        if (!adjList.containsKey(source) || !adjList.containsKey(destination)) return false;
        adjList.get(source).put(destination, weight);
        return true;
    }

    @Override
    public boolean removeEdge(String source, String destination) {
        if (!adjList.containsKey(source) || !adjList.get(source).containsKey(destination)) return false;
        adjList.get(source).remove(destination);
        return true;
    }

    @Override
    public boolean containsVertex(String vertex) {
        return adjList.containsKey(vertex);
    }

    @Override
    public Set<String> getVertices() {
        return new HashSet<>(adjList.keySet());
    }

    @Override
    public Map<String, Integer> getEdges(String vertex) {
        return adjList.getOrDefault(vertex, new HashMap<>());
    }
}
