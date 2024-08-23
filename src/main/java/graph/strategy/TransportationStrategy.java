package graph.strategy;

import graph.Graph;
import graph.Node;
import graph.state.TransportationSystem;

public interface TransportationStrategy {
    int calculateTime(TransportationSystem system, Node start, Node end);
}
