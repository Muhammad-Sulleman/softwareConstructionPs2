package graph;

import java.util.*;

public class ConcreteEdgesGraph<T> implements Graph<T> {
    private final Set<T> vertices = new HashSet<>();
    private final Map<T, Map<T, Integer>> adjacencyList = new HashMap<>();

    @Override
    public int set(T source, T target, int weight) {
        if (source == null || target == null || weight < 0) {
            throw new IllegalArgumentException("Invalid edge parameters.");
        }
        

        // Ensure both vertices exist
        addVertex(source);
        addVertex(target);

        Map<T, Integer> edges = adjacencyList.get(source);
        int previousWeight = edges.getOrDefault(target, 0);

        if (weight == 0) {
            edges.remove(target); // Remove the edge if weight is 0
        } else {
            edges.put(target, weight); // Add or update the edge
        }

        return previousWeight; // Return the previous weight
    }

    @Override
    public boolean addVertex(T vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Vertex cannot be null.");
        }
        if (vertices.add(vertex)) {
            adjacencyList.put(vertex, new HashMap<>()); // Initialize adjacency list for the new vertex
            return true;
        }
        return false; // Vertex already exists
    }

    @Override
    public boolean removeVertex(T vertex) {
        if (!vertices.contains(vertex)) {
            return false; // Vertex does not exist
        }
        vertices.remove(vertex);
        adjacencyList.remove(vertex); // Remove all edges associated with this vertex
        for (Map<T, Integer> edges : adjacencyList.values()) {
            edges.remove(vertex); // Remove all incoming edges to this vertex
        }
        return true;
    }

    @Override
    public boolean addEdge(T source, T destination, int weight) {
        if (source == null || destination == null || weight < 0) {
            throw new IllegalArgumentException("Invalid edge parameters.");
        }

        // Ensure both vertices exist
        addVertex(source);
        addVertex(destination);

        Map<T, Integer> edges = adjacencyList.get(source);
        if (edges.containsKey(destination) && edges.get(destination) == weight) {
            return false; // No change if weight is the same
        }

        edges.put(destination, weight);
        return true; // Edge added or updated
    }

    @Override
    public boolean removeEdge(T source, T destination) {
        if (!adjacencyList.containsKey(source) || !adjacencyList.get(source).containsKey(destination)) {
            return false; // Edge does not exist
        }
        adjacencyList.get(source).remove(destination);
        return true;
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public Set<T> getVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public Map<T, Integer> getEdges(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return Collections.emptyMap(); // Return empty map if vertex doesn't exist
        }
        return new HashMap<>(adjacencyList.get(vertex)); // Return a copy to avoid mutation
    }

    @Override
    public boolean setEdge(T source, T destination, int weight) {
        if (source == null || destination == null || weight < 0) {
            throw new IllegalArgumentException("Invalid edge parameters.");
        }

        // Ensure both vertices exist
        addVertex(source);
        addVertex(destination);

        Map<T, Integer> edges = adjacencyList.get(source);
        if (weight == 0) {
            // Weight 0 means remove the edge
            return edges.remove(destination) != null;
        }

        // Update or create the edge
        edges.put(destination, weight);
        return true;
    }
    @Override
    public Map<T, Integer> targets(T source) {
        if (!adjacencyList.containsKey(source)) {
            return Collections.emptyMap(); // Return an empty map if the source doesn't exist
        }
        return new HashMap<>(adjacencyList.get(source)); // Return a copy to avoid mutation
    }
    @Override
    public Map<T, Integer> sources(T target) {
        Map<T, Integer> sources = new HashMap<>();
        for (Map.Entry<T, Map<T, Integer>> entry : adjacencyList.entrySet()) {
            T source = entry.getKey();
            Map<T, Integer> edges = entry.getValue();
            if (edges.containsKey(target)) {
                sources.put(source, edges.get(target));
            }
        }
        return sources;
    }

}
