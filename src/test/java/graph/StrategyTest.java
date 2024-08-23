package graph;

import graph.state.TransportationSystem;
import graph.strategy.BusStrategy;
import graph.strategy.TrainStrategy;
import graph.strategy.TransportationStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class StrategyTest {

    private Node cityA;
    private Node cityB;
    private Node cityC;
    private TransportationSystem system;

    @Before
    public void setUp() {
        cityA = new Node("A");
        cityB = new Node("B");
        cityC = new Node("C");

        ArrayList<Node> cities = new ArrayList<>();
        cities.add(cityA);
        cities.add(cityB);
        cities.add(cityC);

        Graph graph = new Graph(cities);

        system = new TransportationSystem(graph);

        Edge.createEdge(cityA, cityB, false, 1);
        Edge.createEdge(cityB, cityC, false, 2);
    }

    @Test
    public void testTrainStrategy() {

        TransportationStrategy trainStrategy = new TrainStrategy();

        int time = trainStrategy.calculateTime(system, cityA, cityC);
        Assert.assertEquals(2, time);
    }

    @Test
    public void testBusStrategy() {
        TransportationStrategy busStrategy = new BusStrategy();
        int time = busStrategy.calculateTime(system, cityA, cityC);
        Assert.assertEquals(3, time);
    }

    @Test
    public void testAdjustTrainTime() {
        system.setTrainTime(3);
        TransportationStrategy trainStrategy = new TrainStrategy();
        int time = trainStrategy.calculateTime(system, cityA, cityC);
        Assert.assertEquals(6, time);
    }


}
