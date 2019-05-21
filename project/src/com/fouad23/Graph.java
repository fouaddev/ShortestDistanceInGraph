package com.fouad23;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Implements Hashtable data structure to represent the graph data of the input file.
 *
 * Hashtable object permits any non-null object that can be used as a key or as a value,
 * which allows to use the put() method to insert new Character to represents the cityName,
 * and a different object type which is City object as the value of the Hashtable pair(key, value).
 *
 * The Hashtable object represents all the data of the graph stored as the only data field
 * of the Graph object.
 *
 * To access any pair(cityName, City), we define a cityOf() method that returns City
 * object based on the key cityName input in the cityOf() method.
 */
public class Graph {

    private Hashtable<Character, City> graph;  // Declare graph object with Hashtable data type

    /**
     * Constructor of the Graph class that creates and builds the entire graph when the Graph object
     * is instantiated by the Constructor
     */
    public Graph(String graphInput, String directDistance) throws IOException {
        // Instantiates graph with a new empty Hashtable object
        graph = new Hashtable<>();  // Instantiate graph with new Hashtable object

        // Keeps track of number of lines that have been read.
        int linesRead = 0;

        // Useful array list to store all cityNames as Characters
        ArrayList<Character> citiesArray = new ArrayList<>();

        // Parses data in graphInput.
        // Checks if the input file exists. If so, it uses a while loop to iterate over all lines
        // of graphInput and stores the appropriate City data in the graph object
        if (new File(graphInput).isFile()) {

            // Creates a Scanner object for graphInput file
            Scanner in = new Scanner(new File(graphInput));

            // loop over each line in the file of graphInput and fills up the graph object with City data
            while (in.hasNextLine()) {
                String line = in.nextLine();

                // Deletes all spaces between data using the Java regex and stores data in a String array
                String[] stringNums = line.trim().split("\\s+");

                // Checks
                if (linesRead == 0) {

                    for (int i = 0; i < stringNums.length; i++) {

                        // Creates a key based on the character
                        Character newCity = stringNums[i].charAt(0);

                        // Adds the newCity to the array list
                        citiesArray.add(newCity);

                        // add the character to the graph
                        graph.put(newCity, new City(newCity));
                    }

                } else {

                    // Sets cityName to be the first index Character
                    Character cityName = stringNums[0].charAt(0);

                    // Gets the City based on what character we are at
                    City currentCity = graph.get(cityName);

                    // Loops over the array
                    for (int i = 1; i < stringNums.length; i++) {

                        // Converts to an int
                        int weight = Integer.parseInt(stringNums[i]);

                        // Creates City by getting
                        City destination = graph.get(citiesArray.get(i - 1));

                        // connect the destination City and weight to current City that we are at
                        if (weight != 0) {
                            currentCity.addEdge(destination, weight);
                        }
                    }
                }
                linesRead++;   // Increments linesRead to go and read the next line
            }
        }

        // Parses the data in directDistance.
        // Checks if the directDistance file exists. If it does, then it uses a while loop to iterate
        // over all lines of directDistance and stores the appropriate City data in the graph object
        if (new File(directDistance).isFile()) {
            Scanner scanner = new Scanner(new File(directDistance));
            // loop over each line in the file and stores its Character as the city name,
            // and the Integer as the direct distance of the corresponding City
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] rawData = line.split(" ");
                // Stores city name (as a character)
                Character cityName = rawData[0].charAt(0);
                // Stores direct distance (as an integer)
                int cityDirectDistance = Integer.parseInt(rawData[1]);
                // Adds the direct distance to the corresponding City object, based on the city name
                graph.get(cityName).setCityToDestination(cityDirectDistance);
            }
        }
    }

    // Returns the value City based on the key cityName.
    public City cityOf(Character c) {
        return graph.get(c);   // Using the available get() method of the Java Hashtable
    }
}
