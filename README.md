# ShortestDistanceInGraph
Simple Java application that builds a Graph with user input data. This is a class project. The graph nodes are cities that are connected to each others, and each city name is represented with its first letter. It uses two heuristic algorithms to navigate and find the shortest distance from any given city in the graph to the final destination city Z.

# How to run the application
Just run project.java file which will prompt you to enter the first letter of your chosen city, and the application then will calculate the shortest distance to the final destination Z using two different heuristic algorithms. 
it will display the Sequence of all Cities, and the shortest path to the final destination Z, as well as the path length.

# The program has four classes:
  1. Project class has the algorithm method that navigates the Graph to find the shortest path to final destination City Z.
  2. Graph class that creates the graph.
  3. City class to store information about a City.
  4. Edge class to store information about an edge.

I used HashTable to store the graph data after parsing it in the Grapth constructor of the Graph class. So when creating an instance of the Graph class, a graph object is created with the data of the input file already parsed, and the graph is already built and ready to be used.

When a graph object is created, just call the shortestPathFinder() method defined in the Main class. This method navigates the shortest path and prints out the path found.

There is one method named shortestPathFinder() that executes both heuristic algorithms based on the algorithm input number (int 1 for 1st algorithm, and any other int number for 2nd algorithm). There is only one small difference in deciding which next City to go to. This small difference will be explained in the pseudocode bellow.
