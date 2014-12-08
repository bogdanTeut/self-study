package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by bogdan.teut on 05/12/2014.
 */
public class Graph {

    private List<Integer>[] vertices;

    public Graph(List<Integer>[] vertices) {
        this.vertices = vertices;
    }

    public void addEdge(Integer u, Integer v){
        vertices[u].add(v);
    }

    public void removeEdge(Integer u, Integer v){
        vertices[u].remove(v);
    }

    public List<Integer> getSuccessors(Integer u){
        return vertices[u];
    }

    public void traverseDFS(Integer u, boolean[] visited) {
        if (!visited[u]){
            System.out.println(u);
            visited[u] = true;
            for (Integer predecessor : vertices[u]) {
                traverseDFS(predecessor, visited);
            }
        }
    }

    private int size() {
        return vertices.length;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new List[] {
                Arrays.asList(4),     // successors of vertice 0
                Arrays.asList(1, 2, 6), // successors of vertice 1
                Arrays.asList(1, 6),    // successors of vertice 2
                Arrays.asList(6),     // successors of vertice 3
                Arrays.asList(0),     // successors of vertice 4
                Arrays.<Integer>asList(),    // successors of vertice 5
                Arrays.asList(1, 2, 3)  // successors of vertice 6
        });

        System.out.println("Connected graph components: ");
        boolean[] visited = new boolean[graph.size()];
        for (int v = 0; v < graph.size(); v++){
            if (!visited[v])
            {
                graph.traverseDFS(v, visited);
                System.out.println();
            }
        }

    }

}
