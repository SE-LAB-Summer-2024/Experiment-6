package graph.strategy;

import graph.Graph;
import graph.Node;

public class BusStrategy implements TransportationStrategy {

    @Override
    public int calculateTime(Graph graph, Node start, Node end, int trainTime) {
        graph.resetVisits();
        graph.dijkstra(start);
        return end.getDistance();
    }
}
