package graph.strategy;

import graph.Graph;
import graph.Node;

public class TrainStrategy implements TransportationStrategy {

    @Override
    public int calculateTime(Graph graph, Node start, Node end, int trainTime) {
        graph.resetVisits();
        graph.bfs(start);
        int distance = end.getDistance();
        return distance * trainTime;
    }
}
