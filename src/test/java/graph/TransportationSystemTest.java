package graph;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;


public class TransportationSystemTest {
    private Graph graph;
    private Node cityA;
    private Node cityB;
    private Node cityC;

    @Before
    public void setUp() {
        cityA = new Node("A");
        cityB = new Node("B");
        cityC = new Node("C");

        ArrayList<Node> cities = new ArrayList<>();
        cities.add(cityA);
        cities.add(cityB);
        cities.add(cityC);

        graph = new Graph(cities);

        Edge.createEdge(cityA, cityB, false, 1); // Bidirectional edge
        Edge.createEdge(cityB, cityC, false, 2); // Bidirectional edge
    }

    @Test
    public void testSetOneWay() {
        TransportationSystem system = new TransportationSystem(graph);
        system.setOneWay();

        // After setting one-way, the reverse edges should be removed.
        graph.resetVisits();
        graph.bfs(cityA);
        assertEquals(1, cityB.getDistance());
        assertEquals(-1, cityC.getDistance());
    }

}

