package com.fouad23;

import java.util.ArrayList;

/** Vertices class to create vertex objects for the graph which has a single
 * Character as the cityName.
 *
 * vertexToDestinantion to store the distance between the vertex and destinantion Z
 * an ArrayList that will of Edges that will show all the Cities it is connected to
 * vertexVisited to check if the vertex was connected
 * isItTheShortest to ckeck if there was a back track. If not, then it is currently
 * for sure the shortest path.
 *
 * verticesConnected as an array list to store all the connected vertices to the vertex
 * */
public class City {

    private Character cityName;   // Stores the cityName as a single Character
    private int cityToDestination;   // Stores the direct distance between this city and destination Z
    private boolean citiesVisited;   // Has this vertex already been visited yet?
    private boolean itIsTheShortest;   // Is this the shortest path or not?
    private ArrayList<Edge> cityEdges;   // Array list to store all edges of this vertex

    /** Constructor */
    public City(Character c) {
        cityName = c;
        cityEdges = new ArrayList<>();
        citiesVisited = false;
        itIsTheShortest = false;
    }

    /** Getters */
    public Character getName() {
        return cityName;
    }

    public int getCityToDestination() {
        return cityToDestination;
    }

    public boolean getCitiesVisited() {
        return citiesVisited;
    }

    public boolean getItIsTheShortest() {
        return itIsTheShortest;
    }

    public ArrayList<Edge> getCityEdges() {
        return cityEdges;
    }

    /** Setters */
    public void setCityToDestination(int distanceToZ) {
        cityToDestination = distanceToZ;
    }

    public void setItIsTheShortest(boolean inShortestPath) {
        this.itIsTheShortest = inShortestPath;
    }

    public void setCitiesVisited(boolean cityVisited) {
        this.citiesVisited = cityVisited;
    }

    public void resetVertex() {
        citiesVisited = itIsTheShortest = false;
    }

    // Returns the weight of the edge if found based on the input City object vertex, otherwise -1
    public int findWeight(City vertex) {
        // loops over all the vertices stored in the array list verticesConnected
        for (Edge edge : getCityEdges()) {
            if (edge.getAdjacentCity() == vertex) {   // If the vertex is found, it returns its weight
                return edge.getWeight();
            }
        }
        return 0;  // returns 0 if the vertex was not found as a way to know if was not found
    }

    // Creates an edge with the input vertex and its weight,
    // then adds it to array list of verticesConnected
    public void addEdge(City vertex, int weight) {
        Edge edge = new Edge(vertex, weight);
        cityEdges.add(edge);
    }
}
