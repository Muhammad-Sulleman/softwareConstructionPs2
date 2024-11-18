package graph;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public abstract class GraphInstanceTest {

    // Abstract method to be implemented in subclasses to provide an empty graph instance
    protected abstract Graph<String> emptyInstance();

    @Before
    public void setUp() {
        // Any setup code for tests, if needed
    }

    @Test
    public void testInitialVerticesEmpty() {
        Graph<String> graph = emptyInstance();
        assertTrue("New graph should be empty", graph.getVertices().isEmpty());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.containsVertex("A"));
        assertFalse("Adding the same vertex twice should return false", graph.addVertex("A"));
    }

    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.addVertex("A");
        assertTrue(graph.removeVertex("A"));
        assertFalse(graph.containsVertex("A"));
    }

    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();
        graph.addVertex("A");
        graph.addVertex("B");
        assertTrue(graph.addEdge("A", "B", 10));
        assertEquals(Integer.valueOf(10), graph.getEdges("A").get("B"));
    }

    @Test
    public void testRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 10);
        assertTrue(graph.removeEdge("A", "B"));
        assertFalse(graph.getEdges("A").containsKey("B"));
    }

    @Test
    public void testGetVertices() {
        Graph<String> graph = emptyInstance();
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(2, graph.getVertices().size());
        assertTrue(graph.getVertices().contains("A"));
        assertTrue(graph.getVertices().contains("B"));
    }
}
