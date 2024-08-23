package graph;

import graph.state.OneWayState;
import graph.state.TransportationSystem;
import graph.state.TwoWayState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
        system.setState(new OneWayState());

        // After setting one-way, the reverse edges should be removed.
        graph.resetVisits();
        graph.bfs(cityA);
        Assert.assertEquals(1, cityB.getDistance());
        graph.bfs(cityB);
        Assert.assertEquals(0, cityA.getDistance());
    }

    @Test
    public void testSetTwoWay() {
        TransportationSystem system = new TransportationSystem(graph);
        system.setState(new OneWayState());
        system.setState(new TwoWayState());

        graph.bfs(cityA);

        Assert.assertEquals(1, cityB.getDistance());
        Assert.assertEquals(2, cityC.getDistance());
    }

}

