package graph.state;

import graph.Graph;
import lombok.Getter;
import lombok.Setter;

public class TransportationSystem {

    private TransportationState state;
    @Getter
    private Graph graph;
    @Getter
    private int trainTime;

    public TransportationSystem(Graph graph) {
        this.graph = graph;
        this.state = new TwoWayState();
        this.trainTime = 1;
    }

    public void setState(TransportationState state) {
        this.state = state;
        this.state.changeRoads(this);
    }

    public void setTrainTime(int trainTime) {
        this.trainTime = trainTime;
        System.out.println("Train time set to " + trainTime + " units.");
    }



}
