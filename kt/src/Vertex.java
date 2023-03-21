class Vertex {
    int distanceFromSource;
    String id;
    Vertex nextVertex;
    Vertex previousVertex;
    Edge firstEdge;



    Vertex(String id, Vertex nextVertex, Edge firstEdge) {
        this.id = id;
        this.nextVertex = nextVertex;
        this.firstEdge = firstEdge;
        distanceFromSource = Integer.MAX_VALUE;
    }

    Vertex(String s) {
        this(s, null, null);
    }


    public void setDistanceFromSource(int distance) {
        distanceFromSource = distance;
    }

    @Override
    public String toString() {
        return id;
    }
}