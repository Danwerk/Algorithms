import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Runner a = new Runner();
        a.run();
    }

    public void run() {
        Graph g = new Graph("TestGraph");
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        g.firstVertex = a;
        a.nextVertex = b;
        b.nextVertex = c;
        c.nextVertex = d;
        d.nextVertex = e;
        e.nextVertex = f;
        Edge ab = new Edge("AB", (int) (Math.random() * 20 + 1));
        Edge ba = new Edge("BA", (int) (Math.random() * 20 + 1));
        Edge bc = new Edge("BC", (int) (Math.random() * 20 + 1));
        Edge cb = new Edge("CB", (int) (Math.random() * 20 + 1));
        Edge ac = new Edge("AC", (int) (Math.random() * 20 + 1));
        Edge ca = new Edge("CA", (int) (Math.random() * 20 + 1));
        Edge bd = new Edge("BD", (int) (Math.random() * 20 + 1));
        Edge db = new Edge("DB", (int) (Math.random() * 20 + 1));
        Edge de = new Edge("DE", (int) (Math.random() * 20 + 1));
        Edge ed = new Edge("ED", (int) (Math.random() * 20 + 1));
        Edge ef = new Edge("EF", (int) (Math.random() * 20 + 1));
        Edge fe = new Edge("FE", (int) (Math.random() * 20 + 1));
        Edge fa = new Edge("FA", (int) (Math.random() * 20 + 1));
        Edge af = new Edge("AF", (int) (Math.random() * 20 + 1));
        a.firstEdge = ab;
        ab.targetVertex = b;
        ab.nextEdge = ac;
        ac.targetVertex = c;
        ac.nextEdge = af;
        af.targetVertex = f;
        b.firstEdge = ba;
        ba.targetVertex = a;
        ba.nextEdge = bc;
        bc.targetVertex = c;
        bc.nextEdge = bd;
        bd.targetVertex = d;


        c.firstEdge = ca;
        ca.targetVertex = a;
        ca.nextEdge = cb;
        cb.targetVertex = b;
        d.firstEdge = db;
        db.targetVertex = b;
        db.nextEdge = de;
        de.targetVertex = e;
        e.firstEdge = ed;
        ed.targetVertex = d;
        ed.nextEdge = ef;
        ef.targetVertex = f;
        f.firstEdge = fe;
        fe.targetVertex = e;
        fe.nextEdge = fa;
        fa.targetVertex = a;
//        System.out.println(g);

        System.out.println(Dijkstra.outputPaths(g, a));
// go over all possible paths
//        Vertex v = g.firstVertex;
//        while (v != null) {
//            System.out.println(Dijkstra.outputPaths(g, v));
//            v = v.nextVertex;
//        }
    }

    class Vertex {
        String id;
        Vertex nextVertex;
        Edge firstEdge;
        // calculated best distance from some starting vertex (0 on starting
// vertex)
        int distanceFromSource;
        // previous vertex, used in shortest pathfinding
        Vertex previousVertex;

        // constructor
        Vertex(String vertexID, Vertex nextVertex, Edge firstEdge) {
            id = vertexID;
            this.nextVertex = nextVertex;
            this.firstEdge = firstEdge;
// Initialize all distances from source to infinity
            distanceFromSource = Graph.INFINITY;
        }

        Vertex(String s) {
            this(s, null, null);
        }

        @Override
        public String toString() {
            return id;
        }
    } // Vertex

    class Edge {


        String id;
        Vertex targetVertex;
        Edge nextEdge;
        // length or weight of edge, used to find shortest path
        int length;

        Edge(String edgeID, Vertex targetVertex, Edge nextEdge, int length) {
            id = edgeID;
            this.targetVertex = targetVertex;
            this.nextEdge = nextEdge;
            this.length = length;
        }

        Edge(String edgeID, int length) {
            this(edgeID, null, null, length);
        }

        @Override
        public String toString() {
            return id + "." + length;
        }
// TODO!!! Your Edge methods here!
    } // Edge

    class Graph {
        static final int INFINITY = Integer.MAX_VALUE / 4;
        String id;
        Vertex firstVertex;

        Graph(String graphID, Vertex graphFirstVertex) {
            id = graphID;
            firstVertex = graphFirstVertex;
        }

        Graph(String s) {
            this(s, null);
        }

        public void resetPathInfo() {
            Vertex v = firstVertex;
            while (v != null) {
// distance is equal to infinity
                v.distanceFromSource = INFINITY;
// previous vertex is null
                v.previousVertex = null;
                v = v.nextVertex;
            }
        }

        @Override
        public String toString() {
            String nl = System.getProperty("line.separator");
            StringBuffer sb = new StringBuffer(nl);
            sb.append(id + nl);
            Vertex v = firstVertex;
            while (v != null) {
                sb.append(v.toString() + " --> ");
                Edge e = v.firstEdge;
                while (e != null) {


                    sb.append(e.toString());
                    sb.append("(" + v.toString() + "->" + e.targetVertex.toString() + ")");
                    e = e.nextEdge;
                }
                sb.append(nl);
                v = v.nextVertex;
            }
            return sb.toString();
        }

        public ArrayList<Vertex> getVertexList() {
            ArrayList<Vertex> res = new ArrayList<Vertex>();
            Vertex v = firstVertex;
            while (v != null) {
                res.add(v);
                v = v.nextVertex;
            }
            return res;
        }

        public PriorityQueue<Vertex> getVertexPriorityQueue() {
            PriorityQueue<Vertex> res = new PriorityQueue<Vertex>(1,

                    shortestDistanceComparator);

            Vertex v = firstVertex;
            while (v != null) {
                res.add(v);
                v = v.nextVertex;
            }
            return res;
        }

        // comparator for priority queue sorting
        private final Comparator<Vertex> shortestDistanceComparator = new

                Comparator<Vertex>() {

                    public int compare(Vertex arg0, Vertex arg1) {
                        if (arg0.distanceFromSource > arg1.distanceFromSource) {
                            return 1;
                        }
                        if (arg0.distanceFromSource < arg1.distanceFromSource) {
                            return -1;
                        }
                        return 0;
                    }
                };
    } // Graph

    static class Dijkstra {
        /**
         * Update given graph with shortest path info, starting from given
         * <p>
         * vertex
         * <p>
         * using Dijkstras algorithm
         *
         * @param graph
         * @param startingVertex
         */
        private static void generateShortestPaths(Graph graph, Vertex startingVertex) {
// get rid of simple errors
            if (graph == null) {

                throw new RuntimeException("Dijkstra: graph is null !!!");

            }
// is graph empty?
            if (graph.firstVertex == null) {
                throw new RuntimeException("Dijkstra: graph is empty !!!");

            }
// is startingVertex in this graph?
            if (!graph.getVertexList().contains(startingVertex)) {
                throw new RuntimeException(

                        "Dijkstra: starting vertex not found in given graph !!!");
            }
// reset path info (distance and previous)
            graph.resetPathInfo();
// distance from start to start is zero
            startingVertex.distanceFromSource = 0;
// previous from start is start
            startingVertex.previousVertex = startingVertex;
// priorityqueue is always sorted, with min element at topposition

            PriorityQueue<Vertex> verticesQ =

                    graph.getVertexPriorityQueue();

// go through all the vertices in set Q
            while (!verticesQ.isEmpty()) {
// get and remove the shortest distance vertex
                Vertex shortestDistanceVertex = verticesQ.poll();
                if (shortestDistanceVertex.distanceFromSource ==

                        Graph.INFINITY) {

                    throw new RuntimeException("Dijkstra: min vertex(" + shortestDistanceVertex + ") length can not be infinty !!!");

                }
                if (shortestDistanceVertex.distanceFromSource < 0) {
                    throw new RuntimeException("Dijkstra: min vertex(" + shortestDistanceVertex + ") length can not negative !!!");

                }
// lets iterate over all the outgoing edges from thisvertex

// and we have some distance already from target (maybe0 if

// this is starting vertex)
                Edge edge = shortestDistanceVertex.firstEdge;
                while (edge != null) {
// new distance from this vertex to next vertex
                    int distance =

                            shortestDistanceVertex.distanceFromSource

                                    + edge.length;

// update info on target vertices, if new distance is

// shorter
                    if (edge.targetVertex.distanceFromSource >

                            distance) {

                        edge.targetVertex.distanceFromSource = distance;

// remove it from queue and insert back(Priority Queue

// does not sort itself on component update !!!)

                        verticesQ.remove(edge.targetVertex);
                        verticesQ.add(edge.targetVertex);
// save the path for backtracking
                        edge.targetVertex.previousVertex =

                                shortestDistanceVertex;

                    }
// get next outgoing edge
                    edge = edge.nextEdge;
                }
            }
            return;
        }

        /**
         * Construct string output with Dijkstra path information on given
         * <p>
         * graph,
         * <p>
         * starting from specified vertex
         *
         * @param graph
         * @param startingVertex
         * @return
         */
        private static String output(Graph graph, Vertex startingVertex) {
            String nl = System.getProperty("line.separator");
            StringBuffer sb = new StringBuffer(nl);
            sb.append("Dijkstra" + nl);
            sb.append(graph.id + nl);
            Vertex v = graph.firstVertex;
// go over all the vertices in this graph
            while (v != null) {
// from -> to
                sb.append(startingVertex.toString() + " ==> " +

                        v.toString()

                        + " (Length: " + v.distanceFromSource +

                        ") --> ");

// sbPath for holding the pathinfo
                StringBuffer sbPath = new StringBuffer();
// step backwards from this vertex
                Vertex pathTrack = v;
                do {
//insert into beginning of sb current pathinfo (from, to, distance)

                    sbPath.insert(
                            0,

                            pathTrack.previousVertex.toString()

                                    + "-"
                                    +
                                    (pathTrack.distanceFromSource - pathTrack.previousVertex.distanceFromSource)
                                    + "->" +

                                    pathTrack.toString() + " ");

                    pathTrack = pathTrack.previousVertex;
                } while (pathTrack != startingVertex);
                sb.append(sbPath);
                sb.append(nl);
                v = v.nextVertex;
            }
            return sb.toString();


        }

        public static String outputPaths(Graph graph, Vertex startingVertex) {

            generateShortestPaths(graph, startingVertex);
            return output(graph, startingVertex);
        }
    }
}