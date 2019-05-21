package com.fouad23;

import java.io.IOException;
import java.util.*;

/**
 * Class Project that builds the Graph with the input data, and the two heuristic algorithms
 * to navigate and find the shortest distance from any given city in the graph to the destination Z.
 */
public class project {

    /** The main method to run the program and outputs the result of both algorithms */
    public static void main(String[] args) throws IOException {

        // NOTE: Please enter your own file path of graph_input.txt
        String graphInput = "/Users/me/Desktop/MSSD/cs526/week11/graph_input.txt";

        // NOTE: Please enter your own file path of direct_distance.txt
        String directDistance = "/Users/me/Desktop/MSSD/cs526/week11/direct_distance.txt";

        // Creates a Scanner object for user input
        Scanner in = new Scanner(System.in);

        // Prompt the user for the input
        while (true) {
            try {
                // Informs the user to enter only one characters as the accepted input
                System.out.println("Please enter one Character as the initial of the city name: ");
                System.out.println("NOTE: to quit the program, just enter 'quit'.");

                // Reads the next line
                String userInput = in.nextLine();

                // Checks if the user input is "quit", the program finishes
                if (userInput.toLowerCase().equals("quit")) {
                    break;   // Program finishes
                }
                // Checks if user input length equals 1. If so, it executes and returns the prints out
                // the shortest path
                if (userInput.length() == 1) {
                    // Creates a new graph with the above graphInput and directDistance data
                    Graph graphOfCities = new Graph(graphInput, directDistance);

                    // Converts first Character of user input String currentCity to upper case Character
                    Character currentCity = userInput.toUpperCase().charAt(0);

                    // Calls shortestPathFinder() method of 1st heuristic algorithm to find the shortest path
                    // and prints out the result.
                    shortestPathFinder(graphOfCities, currentCity, 1);

                    // Creates a new graph for the 2nd heuristic algorithm
                    graphOfCities = new Graph(graphInput, directDistance);

                    // Calls shortestPathFinder() method of 2nd heuristic algorithm to find the shortest path
                    shortestPathFinder(graphOfCities, currentCity, 2);
                    System.out.println("New session:");
                }
                // Catches exceptions
            } catch (InputMismatchException | NullPointerException | EmptyStackException e) {
                // Catches both exceptions: InputMismatchException and NullPointerException
                System.out.println("The input entered is not a city in the graph");
            }
        }
    }

    /** The primary method that calculates the shortest path using two different heuristic algorithms
     * recursively.
     */
    private static void shortestPathFinder(Graph graph, Character userCity, int algoNum) {

        // Finds and stores the City that corresponds to the cityName input
        City currentCity = graph.cityOf(userCity);

        // Array List to store every City that was visited by the current one
        ArrayList<Character> citiesVisited = new ArrayList<>();

        // Adds the cityName of the input userCity
        citiesVisited.add(userCity);

        // Creates a Stack to store and manage all Cities visited
        Stack<City> visitingCities = new Stack<>();

        // Loops over each City object in the graph until currentCity becomes the final destinantion Z.
        while (!(currentCity.equals(graph.cityOf('Z')))) {
            // As long as we startCity did not reach the final destination, it means it has visited the
            // previous City object, therefore:

            // Adds currentCity to the stack
            visitingCities.add(currentCity);

            // Sets true to setCitiesVisited() as it has been visited
            currentCity.setCitiesVisited(true);

            // It will also be included to the shortest path
            currentCity.setItIsTheShortest(true);

            // Gets all the edges that are connected to the current City we are on
            ArrayList<Edge> cityEdges = currentCity.getCityEdges();

            // Temporary City object to update currentCity
            City nextCity = null;

            // Integer to track the shortest distance (weight + direct distance) for heuristic algorithm 2
            int weightDistanceTotal = Integer.MAX_VALUE;

            // Loop to keep track of currentCity as it advances to the final destination
            // It iterates over edges of currentCity and chooses among its edges Cities that have not been
            // visited yet, using either algorithm 1 or algorithm 2 based on the method input above.
            // the loop terminates if the final currentCity becomes the final destination Z
            // It also terminates after iterating over all edges and choosing the one with the City that
            // has the shortest direct distance to final destination Z
            for (Edge edge: cityEdges) {

                // Checks if City of this edge was visited. If so, it continues to check the next edge
                if (edge.getAdjacentCity().getCitiesVisited()) {

                    // Continues to the next edge
                    continue;
                }

                // 2. assign the next City to the destination
                if (nextCity == null) {

                    // Updates nextCity to be adjacent City
                    nextCity = edge.getAdjacentCity();

                } else {

                    // Checks which algorithm is being called, and executes the right one
                    if (algoNum == 1) {
                        if (nextCity.getCityToDestination() > edge.getAdjacentCity().getCityToDestination()) {
                            nextCity = edge.getAdjacentCity();
                        }

                        // if algorithm 1 was not chosen, it executes algorithm 2 in the else block bellow
                    } else {
                        weightDistanceTotalCity cityWithWeightDistanceTotal =
                                weightPlusDistanceAlgorithm(nextCity, edge, weightDistanceTotal);
                        nextCity = cityWithWeightDistanceTotal.city;
                        weightDistanceTotal = cityWithWeightDistanceTotal.weightDistanceTotal;
                    }
                }
            }

            // If nextCity is still null, it means that its edge was skipped in the for loop above,
            // because it has already been visited by checking if getCitiesVisited() returns true
            if (nextCity == null) {

                // Pops off the City we just visited, and flag it to be not the shortest distant City
                visitingCities.pop().setItIsTheShortest(false);

                // Sets the next City to the previous one
                nextCity = visitingCities.pop();
            }

            // Adds the City name that was visited to keep track of the path
            citiesVisited.add(nextCity.getName());

            // CurrentCity is updated to be the next City
            currentCity = nextCity;

            // When visiting the last City mark z in the shortest path found
            if (currentCity.getName() == 'Z') {
                currentCity.setItIsTheShortest(true);
            }
        }

        // Tracks the path visited and adds up all the weights of the path found
        int pathCounter = 0;

        // Constructs the shortest path found
        ArrayList<City> shortestPath = new ArrayList<>();

        for (Character tempCity: citiesVisited) {
            // Gets the City of cityVisitor
            City city = graph.cityOf(tempCity);

            // Adds to the shortest path found
            if (city.getItIsTheShortest() && !shortestPath.contains(city)) {
                shortestPath.add(city);
            }
        }

        // Prints out the shortest path found
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            currentCity = shortestPath.get(i);
            City nextCity = shortestPath.get(i + 1);

            // find the weight of that City
            int weight = currentCity.findWeight(nextCity);
            pathCounter += weight;
        }

        // Constructs the final output
        String fullPathSequence = "";
        String citiesVisitedAsString = "";

        for (int i = 0; i < citiesVisited.size(); i++) {
            if (i != citiesVisited.size() - 1) {
                fullPathSequence += citiesVisited.get(i) + " -> ";
            }
        }

        // Constructs a string to store all cityNames
        fullPathSequence += citiesVisited.get(citiesVisited.size() - 1);

        for (int i = 0; i < shortestPath.size(); i++) {
            if (i != shortestPath.size() - 1) {
                citiesVisitedAsString += shortestPath.get(i).getName() + " -> ";
            }
        }

        if (currentCity.getName() == 'Z') {
            citiesVisitedAsString += "Z";
        } else {
            citiesVisitedAsString += shortestPath.get(shortestPath.size() - 1).getName();
        }

        // Prints out the String results.
        System.out.println("Algorithm " + algoNum + ":\nSequence of all Cities: " + fullPathSequence
                + "\nShortest path: " + citiesVisitedAsString + "\nShortest path length: " + pathCounter + "\n");
    }

    /** Heuristic algorithm 2 to find the path using a helper Class weightDistanceTotalCity. */
    private static weightDistanceTotalCity weightPlusDistanceAlgorithm(City nextCity, Edge edge,
                                                                       Integer weightDistanceTotal) {
        if (edge.getWeight() + edge.getAdjacentCity().getCityToDestination() < weightDistanceTotal) {
            weightDistanceTotal = (edge.getWeight() + edge.getAdjacentCity().getCityToDestination());
            return new weightDistanceTotalCity(edge.getAdjacentCity(), weightDistanceTotal);
        }
        return new weightDistanceTotalCity(nextCity, weightDistanceTotal);
    }

    /** Helper class that ties the City and its total (distance + weight) to the chosen next City. */
    private static class weightDistanceTotalCity {
        City city;
        int weightDistanceTotal;

        public weightDistanceTotalCity(City city, int weightDistanceTotal) {
            this.city = city;
            this.weightDistanceTotal = weightDistanceTotal;
        }
    }
}
