package graph;
import org.junit.Test;
import static org.junit.Assert.*;


public class ConcreteEdgesGraphTest {

    @Test
    public void testAddVertex() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.containsVertex("A"));
        assertFalse(graph.addVertex("A")); // Adding again should return false
    }

    @Test
    public void testAddEdge() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.addEdge("A", "B", 5));
        assertEquals((Integer) 5, graph.getEdges("A").get("B"));
    }

    @Test
    public void testRemoveEdge() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.addEdge("A", "B", 5);
        assertTrue(graph.removeEdge("A", "B"));
        assertFalse(graph.getEdges("A").containsKey("B"));
    }

    @Test
    public void testRemoveVertex() {
        ConcreteEdgesGraph<String> graph = new ConcreteEdgesGraph<>();
        graph.addVertex("A");
        graph.addEdge("A", "B", 5);
        assertTrue(graph.removeVertex("A"));
        assertFalse(graph.containsVertex("A"));
        assertFalse(graph.getEdges("A").containsKey("B")); // Edges should also be removed
    }
}
