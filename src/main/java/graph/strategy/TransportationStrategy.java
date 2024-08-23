package graph.strategy;

import graph.Graph;
import graph.Node;

public interface TransportationStrategy {
    int calculateTime(Graph graph, Node start, Node end, int trainTime);
}
