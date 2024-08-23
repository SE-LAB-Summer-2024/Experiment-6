package graph.state;

public class OneWayState implements TransportationState {

    @Override
    public void changeRoads(TransportationSystem system) {
        system.getGraph().makeAllRoutesOneWay();
        System.out.println("Switched to one-way mode.");
    }
}
