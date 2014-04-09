/*************************************************************************
 *  Dijkstra's algorithm.
 *
 *************************************************************************/


public class Dijkstra {
    private static double INFINITY = Double.MAX_VALUE; //Max value 
    private static double EPSILON  = 0.000001; //used for error checking

    private EuclideanGraph G; //Private object
    private double[] dist; // private variable for distance
    private int[] pred; //private variable for predecessor

    public Dijkstra(EuclideanGraph G) { //constructor for dijkstra
        this.G = G; //initializes G
    }

    // return shortest path distance from s to d
    public double distance(int s, int d) {
        dijkstra(s, d); //inputs s and d values
        return dist[d]; //returns distance to d
    }

    // print shortest path from s to d  (interchange s and d to print in right order)
    public void showPath(int d, int s) {
        dijkstra(s, d); //inputs s and d values
        if (pred[d] == -1) { //if the predecessor to y is -1
            System.out.println(d + " is unreachable from " + s); //print that y is not reachable from x 
            return;
        }
        for (int v = d; v != s; v = pred[v]) //for loop that iterates from point to point
            System.out.print(v + "-"); //prints out point with a dash then goes to next point
        System.out.println(s); //prints out point trying to get to
    }

    // Dijkstra's algorithm to find shortest path from s to d
    private void dijkstra(int s, int d) { 
        int V = G.V(); //creates variable V equal to the graph.V

        // initialize
        dist = new double[V]; //initializes distance
        pred = new int[V]; //initializes predecessor
        for (int v = 0; v < V; v++) dist[v] = INFINITY; //sets max distance = max value
        for (int v = 0; v < V; v++) pred[v] = -1; //sets predecessor = to -1

        // priority queue
        IndexPQ pq = new IndexPQ(V); //new Object of IndexPQ
        for (int v = 0; v < V; v++) pq.insert(v, dist[v]); //loops through priority queue and inserts v and distance to v 

        // set distance of source
        dist[s] = 0.0; //set distance of s = to 0.0
        pred[s] = s; //set predecessor of s = to s
        pq.change(s, dist[s]); //change priority of s to distance of s

        // run Dijkstra's algorithm
        while (!pq.isEmpty()) { //while priority queue isnt empty
            int v = pq.delMin(); // v = delete min element and return 
            //// System.out.println("process " + v + " " + dist[v]);

            // v not reachable from s so stop
            if (pred[v] == -1) break;

            // scan through all nodes w adjacent to v
            IntIterator i = G.neighbors(v);
            while (i.hasNext()) { //while object i has a next value
                int w = i.next(); //w is equal to i.next
                if (dist[v] + G.distance(v, w) < dist[w] - EPSILON) { //if distance of v + graph distance from v to w is less than distance to w - accuracy counter
                    dist[w] = dist[v] + G.distance(v, w); //distance of w = to distance of v + graph distance from v to w
                    pq.change(w, dist[w]); //change priority of w to distance of w
                    pred[w] = v; //predecessor of w is equal to v 
                    //// System.out.println("    lower " + w + " to " + dist[w]);
                }
            }
        }
    }


}

