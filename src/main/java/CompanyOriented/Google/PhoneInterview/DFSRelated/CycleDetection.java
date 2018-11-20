package CompanyOriented.Google.PhoneInterview.DFSRelated;

import CompanyOriented.Google.PhoneInterview.MazeRelated.StdOut;

import java.util.Stack;

public class CycleDetection {

    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public CycleDetection(Graph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, -1, v);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Stack<Integer> cycle() {
        return cycle;
    }


    private void dfs(Graph G, int u, int v) {
        marked[v] = true;
        for (int adjVertex : G.adj(v)) {
            if (cycle != null) return;
            if (!marked[adjVertex]) {
                edgeTo[adjVertex] = v;
                dfs(G, v, adjVertex);
            } else if (adjVertex != u) { // Cycle
                cycle = new Stack<>();
                int from = v;
                while (from != adjVertex) {
                    cycle.push(from);
                    from = edgeTo[from];
                }
                cycle.push(adjVertex);
                cycle.push(v);
            }
        }
    }

    public static void main(String[] args) {
        In in = new In("tinyG.txt");
        Graph G = new Graph(in);
        CycleDetection finder = new CycleDetection(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        } else {
            StdOut.println("Graph is acyclic");
        }
    }
}
