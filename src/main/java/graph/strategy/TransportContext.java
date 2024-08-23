package graph.strategy;

import graph.Node;
import graph.state.TransportationSystem;
import lombok.Setter;

public class TransportContext {
    @Setter
    private TransportationStrategy strategy;

    public TransportContext(TransportationStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateTime(TransportationSystem system, Node start, Node end) {
        return this.strategy.calculateTime(system, start, end);
    }
}
