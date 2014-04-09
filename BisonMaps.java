/*************************************************************************
 *  Compilation:  javac BisonMaps.java
 *  Execution:    java BisonMaps mapfile < input.txt
 *  Dependencies: EuclideanGraph.java Dijkstra.java In.java StdIn.java
 *
 *  Reads in a map from a file, and repeatedly reads in two integers s
 *  and d from standard input, and prints the shortest path from s
 *  to d to standard output.
 *
 ****************************************************************************/


public class BisonMaps { //class to create map

    public static void main(String[] args) { //main line

        // read in the graph from a file
        In graphin = new In(args[0]); // Creates a new object from main parameter 
        EuclideanGraph G = new EuclideanGraph(graphin); // Creates a new graph object
        System.err.println("Done reading the graph " + args[0]); // Prints done reading graph and mapfile name
        System.err.println("Enter query pairs from stdin"); //Prints to ask user to input two points.

        // read in the s-d pairs from standard input
        Dijkstra dijkstra = new Dijkstra(G); //Creates new dijkstra algorithm object
        while(!StdIn.isEmpty()) { //while inputs are not empty 
            int s = StdIn.readInt(); //read in s
            int d = StdIn.readInt(); //read in d
            dijkstra.showPath(s, d); //show shortest path from s to d
            System.out.println(); //Like pressing enter
        }
    }
}
