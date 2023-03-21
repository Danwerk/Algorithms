class Edge {

    String id;
    Vertex destVertex;
    Edge nextEdge;
    int edgeLength;

    Edge(String id, Vertex destVertex, Edge nextEdge, int edgeLength) {
        this.id = id;
        this.destVertex = destVertex;
        this.nextEdge = nextEdge;
        this.edgeLength = edgeLength;
    }

    Edge(String s, int edgeLength) {
        this(s, null, null, edgeLength);

    }

    @Override
    public String toString() {
        return id + "[" + edgeLength + "]";
    }
}