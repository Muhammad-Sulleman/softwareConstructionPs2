package graph;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphStaticTest {

    @Test
    public void testEmptyGraph() {
        Graph<String> graph = Graph.empty();
        assertTrue("Newly created empty graph should have no vertices", graph.getVertices().isEmpty());
        assertTrue("Newly created empty graph should have no edges", graph.getVertices().isEmpty());
    }
}
