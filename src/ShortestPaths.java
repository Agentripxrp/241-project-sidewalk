import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;

/** Provides an implementation of Dijkstra's single-source shortest paths
 * algorithm.
 * Sample usage:
 *   Graph g = // create your graph
 *   ShortestPaths sp = new ShortestPaths();
 *   Node a = g.getNode("A");
 *   sp.compute(a);
 *   Node b = g.getNode("B");
 *   LinkedList<Node> abPath = sp.getShortestPath(b);
 *   double abPathLength = sp.getShortestPathLength(b);
 *   */
public class ShortestPaths {
    // stores auxiliary data associated with each node for the shortest
    // paths computation:
    private HashMap<Node,PathData> paths;

    /** Compute the shortest path to all nodes from origin using Dijkstra's
     * algorithm. Fill in the paths field, which associates each Node with its
     * PathData record, storing total distance from the source, and the
     * back pointer to the previous node on the shortest path.
     * Precondition: origin is a node in the Graph.*/
    public void compute(Node origin) {
        paths = new HashMap<>();
        paths.put(origin, new PathData(0.0, null));
        HashMap<Node, Boolean> visited = new HashMap<>();
        while (true) {
            Node current = null;
            double minDistance = Double.POSITIVE_INFINITY;
            for (Node n : paths.keySet()) {
                if (!visited.containsKey(n)) {
                    double dist = paths.get(n).distance;
                    if (dist < minDistance) {
                        minDistance = dist;
                        current = n;
                    }
                }
            }
            if (current == null) {
                break;
            }
            visited.put(current, true);
            for (Map.Entry<Node, Double> entry : current.getNeighbors().entrySet()) {
                Node neighbor = entry.getKey();
                double weight = entry.getValue();
                if (visited.containsKey(neighbor)) {
                    continue;
                }
                double newDist = paths.get(current).distance + weight;
                if (!paths.containsKey(neighbor)
                        || newDist < paths.get(neighbor).distance) {
                    paths.put(neighbor, new PathData(newDist, current));
                }
            }
        }
    }
    /**
     * the entire function above is written with AI, this function finds the shortest path from 1 specific node to all
     * nodes in the graph; the function creates a hash table storing A: the destination node and B: the path distance
     * from origin to said destination. It accounts for any node with a path and if there is no path then the value of
     * distance will just be infinity meaning that you would never be able to reach node destination from origin. The
     * compute function also cannot physically put itself in a never ending loop as there is a local hashmap called
     * visited which if compute is traversing through the graph and finds out that the next node is already visited it
     * will not go through that node. The function also does a good job of resetting the visited hashmap everytime it
     * tries to create a new path to another node. Guaranteeing that it will not fail to properly create the shortest
     * path.
     */

    /** Returns the length of the shortest path from the origin to destination.
     * If no path exists, return Double.POSITIVE_INFINITY.
     * Precondition: destination is a node in the graph, and compute(origin)
     * has been called. */
    public double shortestPathLength(Node destination) {
        // TODO 2 - implement this method to fetch the shortest path length
        // from the paths data computed by Dijkstra's algorithm.
        PathData data = paths.get(destination);
        if (data == null) {
            return Double.POSITIVE_INFINITY;
        }
        return data.distance;
    }

    /** Returns a LinkedList of the nodes along the shortest path from origin
     * to destination. This path includes the origin and destination. If origin
     * and destination are the same node, it is included only once.
     * If no path to it exists, return null.
     * Precondition: destination is a node in the graph, and compute(origin)
     * has been called. */
    public LinkedList<Node> shortestPath(Node destination) {
        // TODO 3 - implement this method to reconstruct sequence of Nodes
        // along the shortest path from the origin to destination using the
        // paths data computed by Dijkstra's algorithm.
        if (!paths.containsKey(destination)) {
            return null;
        }
        LinkedList<Node> path = new LinkedList<>();
        Node current = destination;
        while (current != null) {
            path.addFirst(current);
            current = paths.get(current).previous;
        }
        return path;
    }


    /** Inner class representing data used by Dijkstra's algorithm in the
     * process of computing shortest paths from a given source node. */
    class PathData {
        double distance;
        Node previous;

        /** constructor: initialize distance and previous node */
        public PathData(double dist, Node prev) {
            distance = dist;
            previous = prev;
        }
    }


    /** Static helper method to open and parse a file containing graph
     * information. Can parse either a basic file or a CSV file with
     * sidewalk data. See GraphParser, BasicParser, and DBParser for more.*/
    protected static Graph parseGraph(String fileType, String fileName) throws
            FileNotFoundException {
        // create an appropriate parser for the given file type
        GraphParser parser;
        if (fileType.equals("basic")) {
            parser = new BasicParser();
        } else if (fileType.equals("db")) {
            parser = new DBParser();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported file type: " + fileType);
        }

        // open the given file
        parser.open(new File(fileName));

        // parse the file and return the graph
        return parser.parse();
    }

    public static void main(String[] args) {
        // read command line args
        String fileType = args[0];
        String fileName = args[1];
        String SidewalkOrigCode = args[2];
        String SidewalkDestCode = null;
        if (args.length == 4) {
            SidewalkDestCode = args[3];
        }
        Graph graph;
        try {
            graph = parseGraph(fileType, fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Could not open file " + fileName);
            return;
        }
        graph.report();
        // All of the below was written by AI and was handchecked and supervised by me.
        Node origin = graph.getNode(SidewalkOrigCode);
        if (origin == null) {
            System.out.println("Origin node not found: " + SidewalkOrigCode);
            return;
        }
        ShortestPaths sp = new ShortestPaths();
        sp.compute(origin);
        if (SidewalkDestCode == null) {
            for (Node n : sp.paths.keySet()) {
                System.out.println(n + " : " + sp.shortestPathLength(n)); //Prints origin to Node n (destination) length
            }
        }
        else {
            Node dest = graph.getNode(SidewalkDestCode);
            if (dest == null) {
                System.out.println("Destination node not found: " + SidewalkDestCode);
                return;
            }

            LinkedList<Node> path = sp.shortestPath(dest);
            if (path == null) {
                System.out.println("No path exists from "
                        + SidewalkOrigCode + " to " + SidewalkDestCode);
            } else {
                for (Node n : path) {
                    System.out.println(n); //Print every node in path of origin to destination
                }
                System.out.println("Total path length: " //Get shortest path length
                        + sp.shortestPathLength(dest));
            }
        }
    }
}
