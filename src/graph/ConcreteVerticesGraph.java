package graph;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConcreteVerticesGraph<T> implements Graph<T> {
    private final Map<T, Map<T, Integer>> vertices = new HashMap<>();

    @Override
    public boolean addVertex(T vertex) {
        if (vertices.containsKey(vertex)) return false;
        vertices.put(vertex, new HashMap<>());
        return true;
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (!vertices.containsKey(vertex)) return false;
        vertices.remove(vertex);
        for (Map<T, Integer> edges : vertices.values()) {
            edges.remove(vertex);
        }
        return true;
    }

    @Override
    public boolean addEdge(T source, T destination, int weight) {
        if (!vertices.containsKey(source) || !vertices.containsKey(destination)) return false;
        vertices.get(source).put(destination, weight);
        return true;
    }

    @Override
    public boolean removeEdge(T source, T destination) {
        if (!vertices.containsKey(source)) return false;
        vertices.get(source).remove(destination);
        return true;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertices.containsKey(vertex);
    }

    @Override
    public Set<T> getVertices() {
        return new HashSet<>(vertices.keySet());
    }

    @Override
    public Map<T, Integer> getEdges(T vertex) {
        return vertices.getOrDefault(vertex, new HashMap<>());
    }
}
