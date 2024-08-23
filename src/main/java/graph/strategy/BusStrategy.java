package graph.strategy;

import graph.Graph;
import graph.Node;
import graph.state.TransportationSystem;

public class BusStrategy implements TransportationStrategy {

    @Override
    public int calculateTime(TransportationSystem system, Node start, Node end) {
        Graph graph = system.getGraph();
        graph.resetVisits();
        graph.dijkstra(start);
        return end.getDistance();
    }
}
