package graph.state;

public class TwoWayState implements TransportationState {

    @Override
    public void changeRoads(TransportationSystem system) {
        system.getGraph().makeAllRoutesTwoWay();
        System.out.println("Switched to two-way mode.");
    }
}
