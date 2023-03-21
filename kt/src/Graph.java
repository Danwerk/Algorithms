import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static final int INFINITY = Integer.MAX_VALUE / 4;
    public String id;
    public Vertex firstVertex;


    Graph(String graphId, Vertex firstVertex) {
        id = graphId;
        this.firstVertex = firstVertex;
    }

    Graph(String name) {
        this(name, null);
    }

    public List<Vertex> getVertices() {
        List<Vertex> vertexList = new ArrayList<>();
        Vertex v = firstVertex;
        while (v != null) {
            vertexList.add(v);
            v = v.nextVertex;
        }
        return vertexList;
    }


    @Override
    public String toString() {
        String s = "";
        String ls = System.getProperty("line.separator");  // new line characters
        s += (id + ls);
        Vertex v = firstVertex;
        while (v != null) {
            s += (v + " ==> ");
            Edge e = v.firstEdge;
            while (e != null) {
                s += (e);
                s += ("(" + v + "->" + e.destVertex + ") ");

                e = e.nextEdge;
            }
            s += (ls);
            v = v.nextVertex;
        }
        return s;
    }
}