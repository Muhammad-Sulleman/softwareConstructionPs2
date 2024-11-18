package graph;


public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    @Override
    protected Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }
}

