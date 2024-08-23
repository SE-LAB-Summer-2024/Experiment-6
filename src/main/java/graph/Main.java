package graph;

import java.util.ArrayList;
import java.util.Scanner;

import graph.state.OneWayState;
import graph.state.TransportationSystem;
import graph.state.TwoWayState;
import graph.strategy.BusStrategy;
import graph.strategy.TrainStrategy;
import graph.strategy.TransportContext;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Graph graph = initializeGraph(scanner);

        TransportationSystem system = new TransportationSystem(graph);

        boolean exit = false;

        while (!exit) {
            System.out.println("Please choose an option:");
            System.out.println("1. Make all routes one-way");
            System.out.println("2. Make all routes two-way");
            System.out.println("3. Change the train time unit");
            System.out.println("4. Calculate travel time by train from one city to another");
            System.out.println("5. Calculate travel time by bus from one city to another");
            System.out.println("6. Find the faster travel method from one city to another");
            System.out.println("7. Check if travel from one city to another is possible without passing through certain disliked cities");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    system.setState(new OneWayState());
                    break;
                case 2:
                    system.setState(new TwoWayState());
                    break;
                case 3:
                    System.out.println("Enter the new train time unit:");
                    int newTime = scanner.nextInt();
                    scanner.nextLine();
                    system.setTrainTime(newTime);
                    break;
                case 4:
                    Transport trainTransport = getTransport(scanner, graph);
                    TransportContext trainContext = new TransportContext(new TrainStrategy());
                    int trainTime = trainContext.calculateTime(system, trainTransport.source, trainTransport.destination);
                    System.out.println("Train travel time: " + trainTime);
                    break;
                case 5:
                    Transport busTransport = getTransport(scanner, graph);
                    TransportContext busContext = new TransportContext(new BusStrategy());
                    int busTime = busContext.calculateTime(system, busTransport.source, busTransport.destination);
                    System.out.println("Bus travel time: " + busTime);
                    break;
                case 6:
                    Transport transport = getTransport(scanner, graph);
                    TransportContext context = new TransportContext(new TrainStrategy());
                    int timeByTrain = context.calculateTime(system, transport.source, transport.destination);
                    context.setStrategy(new BusStrategy());
                    int timeByBus = context.calculateTime(system, transport.source, transport.destination);
                    if (timeByTrain < timeByBus) {
                        System.out.println("Train is faster: " + timeByTrain);
                    } else {
                        System.out.println("Bus is faster: " + timeByBus);
                    }
                    break;
                case 7:
                    Transport transportWithoutSomeCities = getTransport(scanner, graph);
                    System.out.println("Enter the number of disliked cities:");
                    int dislikedCityCount = scanner.nextInt();
                    scanner.nextLine();
                    graph.resetVisits();
                    for (int i = 0; i < dislikedCityCount; i++) {
                        System.out.println("Enter the name of disliked city " + (i + 1) + ":");
                        Node dislikedCity = findCity(scanner.nextLine(), graph);
                        if (dislikedCity != null) {
                            dislikedCity.setVisited(true);
                        }
                    }

                    system.getGraph().bfs(transportWithoutSomeCities.source);

                    if (! transportWithoutSomeCities.destination.isVisited()) {
                        System.out.println("It is NOT possible to travel from " + transportWithoutSomeCities.source.getName() +
                                " to " + transportWithoutSomeCities.destination.getName() + " without passing through the disliked cities.");
                    } else {
                        System.out.println("It is possible to travel from " + transportWithoutSomeCities.source.getName() +
                                " to " + transportWithoutSomeCities.destination.getName() + " without passing through the disliked cities.");
                    }
                    graph.resetVisits();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static Transport getTransport(Scanner scanner, Graph graph) {
        System.out.println("Enter the source city:");
        Node sourceTrain = findCity(scanner.nextLine(), graph);
        System.out.println("Enter the destination city:");
        Node destinationTrain = findCity(scanner.nextLine(), graph);
        return new Transport(sourceTrain, destinationTrain);
    }

    private static class Transport {
        public  Node source;
        public  Node destination;

        public Transport(Node source, Node destination) {
            this.source = source;
            this.destination = destination;
        }
    }

    private static Graph initializeGraph(Scanner scanner) {
        ArrayList<Node> nodes = new ArrayList<>();

        System.out.println("Enter the number of cities:");
        int cityCount = scanner.nextInt();
        scanner.nextLine();

        // Create nodes for each city
        for (int i = 0; i < cityCount; i++) {
            System.out.println("Enter the name of city " + (i + 1) + ":");
            String cityName = scanner.nextLine();
            Node city = new Node(cityName);
            nodes.add(city);
        }

        Graph graph = new Graph(nodes);

        System.out.println("Enter the number of routes:");
        int routeCount = scanner.nextInt();
        scanner.nextLine();

        // Create edges for each route
        for (int i = 0; i < routeCount; i++) {
            System.out.println("Enter the route in the format 'City1 City2 time bidirectional(true/false)':");
            String city1Name = scanner.next();
            String city2Name = scanner.next();
            int time = scanner.nextInt();
            boolean bidirectional = scanner.nextBoolean();
            scanner.nextLine(); // consume newline

            Node city1 = findCity(city1Name, graph);
            Node city2 = findCity(city2Name, graph);

            if (city1 != null && city2 != null) {
                Edge.createEdge(city1, city2, !bidirectional, time);
            } else {
                System.out.println("Error: One or both cities not found.");
            }
        }

        return graph;
    }

    private static Node findCity(String cityName, Graph graph) {
        for (Node node : graph.getGraph()) {
            if (node.getName().equals(cityName)) {
                return node;
            }
        }
        return null;
    }
}
