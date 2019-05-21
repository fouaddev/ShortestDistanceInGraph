package com.fouad23;

/**
 * Edge is a class to store information of an edge, including the connected city,
 * and the distance between them as the weight of the edge.
 */
public class Edge {

    private City adjacentCity;   // Represents the connected city of the current city
    private int edgeWeight;   // Represents the weight (the distance between the above two cities)

    public Edge(City connectedCity, int weight) {
        adjacentCity = connectedCity;
        edgeWeight = weight;
    }

    /** Getters */
    public City getAdjacentCity() {
        return adjacentCity;
    }

    public int getWeight() {
        return edgeWeight;
    }
}
