package graph;

import java.util.Map;
import java.util.Set;

public interface Graph<T> {
	boolean setEdge(T source, T destination, int weight);
    int set(T source, T target, int weight);

    
    boolean addVertex(T vertex);
    
    boolean removeVertex(T vertex);


    
    boolean addEdge(T source, T destination, int weight);
    
    boolean removeEdge(T source, T destination);
    
    boolean containsVertex(T vertex);
    
    Set<T> getVertices();
    Map<T, Integer> sources(T target);
    Map<T, Integer> targets(T source);
    
    
    Map<T, Integer> getEdges(T vertex);
    
    static <T> Graph<T> empty() {
        return new ConcreteEdgesGraph<>();
    }
}
