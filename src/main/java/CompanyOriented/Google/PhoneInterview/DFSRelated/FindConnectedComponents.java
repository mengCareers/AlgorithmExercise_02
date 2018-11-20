package CompanyOriented.Google.PhoneInterview.DFSRelated;

import CompanyOriented.Google.PhoneInterview.MazeRelated.StdOut;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
Compute connected components using depth first search.
 */
public class FindConnectedComponents {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public FindConnectedComponents(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) { // For any unvisited node.
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int adjVertex : G.adj(v)) {
            if (!marked[adjVertex]) {
                dfs(G, adjVertex);
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    public boolean isConnected(int x, int y) {
        return id[x] == id[y];
    }

    public static void main(String[] args) {
        In in = new In("TinyG.txt");
        Graph G = new Graph(in);
        FindConnectedComponents cc = new FindConnectedComponents(G);

        // number of connected components
        int m = cc.count();
        StdOut.println(m + " components");

        // compute list of vertices in each connected component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new LinkedList<>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].add(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
    }
}
