package graph;

import graph.state.TransportationSystem;
import graph.strategy.TrainStrategy;
import graph.strategy.TransportationStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StrategyTest {

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

        Edge.createEdge(cityA, cityB, false, 1);
        Edge.createEdge(cityB, cityC, false, 2);
    }

    @Test
    public void testTrainStrategy() {
        TransportationSystem system = new TransportationSystem(graph);
        TransportationStrategy trainStrategy = new TrainStrategy();

        int time = trainStrategy.calculateTime(graph, cityA, cityC, system.getTrainTime());
        Assert.assertEquals(2, time); //
    }
}
