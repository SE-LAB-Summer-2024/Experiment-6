package graph.strategy;

import graph.Graph;
import graph.Node;
import graph.state.TransportationSystem;

public class TrainStrategy implements TransportationStrategy {

    @Override
    public int calculateTime(TransportationSystem system, Node start, Node end) {
        Graph graph = system.getGraph();
        graph.bfsWithReset(start);
        int distance = end.getDistance();
        return distance * system.getTrainTime();
    }
}
