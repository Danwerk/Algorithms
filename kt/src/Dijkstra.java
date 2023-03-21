import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {


    public static String dijkstraShortestPathGenerator(Graph graph, Vertex firstVertex) {
        firstVertex.distanceFromSource = 0;


        System.out.println(graph);

        return "";
    }

}





//    procedure dijkstra(G, S)
//     G-> graph; S->starting vertex
//             begin
//             for each vertex V in G          //initialization; initial path set to infinite
//             path[V] <- infinite
//        previous[V] <- NULL
//        If V != S, add V to Priority Queue PQueue
//        path [S] <- 0
//
//        while PQueue IS NOT EMPTY
//        U <- Extract MIN from PQueue
//        for each unvisited adjacent_node  V of U
//        tempDistance <- path [U] + edge_weight(U, V)
//        if tempDistance < path [V]
//        path [V] <- tempDistance
//        previous[V] <- U
//        return path[], previous[]
//        end
