# ShortestDistanceInGraph
Simple Java application that builds a Graph with user input data. This is a class project. The graph nodes are cities that are connected to each others, and each city name is represented with its first letter. It uses two heuristic algorithms to navigate and find the shortest distance from any given city in the graph to the final destination city Z.

# The program has four classes:
  1. Project class has the algorithm method that navigates the Graph to find the shortest path to final destination Z.
  2. Graph class that creates the graph.
  3. City class to store information about a City.
  4. Edge class to store information about an edge.

I used HashTable to store the graph data after parsing it in the Grapth constructor of the Graph class. So when creating an instance of the Graph class, a graph object is created with the data of the input file already parsed, and the graph is already built and ready to be used.

When a graph object is created, just call the shortestPathFinder() method defined in the Main class. This method navigates the shortest path and prints out the path found.

There is one method named shortestPathFinder() that executes both heuristic algorithms based on the algorithm input number (int 1 for 1st algorithm, and any other int number for 2nd algorithm). There is only one small difference in deciding which next City to go to. This small difference will be explained in the pseudocode bellow.

# How to run the application
Just run project.java file which will prompt you to enter the first letter of your chosen city, and the application then will calculate the shortest distance to the final destination Z using two different heuristic algorithms. 
it will display the Sequence of all Cities, and the shortest path to the final destination Z, as well as the path length.

# Pseudocode: 
When running the program, the main method bellow is executed 
	•	Main() throws IOException 
o graphInput file as a String 
o directDistance as a string 
o While (true) 
	 	Try { 
	•	Print out “Enter city name …etc” 
	•	Print out “To quit the program, just enter ‘quit’.” 
	•	If (user enters ‘quit’) 
o Program terminates 
	•	If (length of user input == 1) 
o Create a new Graph object with input (graphInput, directDistance); 
	•	Creates currentCity user input as an upper case character. 
	•	Calls shortestPathFinder() with graph and user, input, and algorithm number city name as parameters 
	•	Which finds the shortest path from user input city to the destination city Z. 
	•	After it is done, “New Session:” message is printed out to user 
	 	Catch (InputMismatchException | NullPointerException | EmptyStackException e) 
	•	If one of the above exceptions is caught, it prints out “the input entered is not a city in graph” 
 
	•	shortestPathFinder() 
* Input: the Graph that was built in the main() method, and a Character userCity, and either heuristic algorithm 1 (based on shortest direct distance only, or algorithm 2 (based on the shortest: direct distance + weight. 
* Output: Prints out the shortest distance from the input city and final destination Z, and also prints out the shortest path to get to Z, as well as all cities that were considered in the path. 
 
	•	and algorithm number. 
	•	City CurrentCity = city (of type class City) that corresponds to user input city name 
o 	ArrayList to store cities visited in citiesVisited variable 
o 	Add user input to citiesVisited as the first City Object of the array list citiesVisited 
o 	Creates a stack of visiting Cities to store all cities that have been visited by current City 
o 	While current city is not final destination Z: 
	•	Add current City to the stack of cities Visited 
	•	Flag current City to be visited (true) 
	•	Set current City to be have the shortest distance to Z (true) 
	•	Create ArrayList to store all edges of the current City 
	•	Create a temporary object nextCity of type City, and set it to null value 
	•	For loop over current City edges and chose the shortest distance one (using either algo1 or 2): 
	•	If adjacent city of current Edge was visited: 
o Continue to check the next Edge (go to next iteration)

	•	If temporary variable next City null: 
o Update next City to be current adjacent City 
	•	Else 
o If algorithm input number was 1: 
	•	Execute algorithm 1 by checking if direct distance to Z of current City is bigger than direct distance to Z of adjacent City: if so, current City is updated to be adjacent City 
o Else: 
	•	Execute algorithm 2 by doing the same thing as algorithm 1, except for when comparing the two distances, the weight is also added to both values before comparing them (using a helper class to tie up the winning City and its total of weight + direct distance to Z. 
	•	If next City is still null: 
o Pop() the last city in the stack 
o Flag the popped City to be false as the shortest distant City 
o Pop() the last City in the stack and assign it to next City 
	•	Add name of next City to the Array List of cities visited
	•	Update current City to be next City 

	•	If name of current City is Z: 
	•	Set it to be the shortest distant City (setItTheShortest(true).)
	•	Build the output String to be printed out 
	•	Prints out the String results. 


