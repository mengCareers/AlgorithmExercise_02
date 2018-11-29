package CompanyOriented.Google.Interview.DFSRelated;

import java.util.Stack;

/*
 *  Given a graph, find either (i) a bipartition or (ii) an odd-length cycle.
 *
 *  A bipartite picture can be a circle.
 *
 *  We do not split the original graph - it doesn't need to be only one line
 *
 *  A -- B
 *  |    |
 *  B -- A
 *
 *  is also bipartitle
 *  */
public class Bipartition {

    private boolean[] marked;
    private boolean[] colored; // We only color one side.
    private int[] edgeTo; // edgeTo[v] last element on path to v.
    private boolean isBipartitie;
    private Stack<Integer> cycle;

    public Bipartition(Graph G) {
        marked = new boolean[G.V()];
        colored = new boolean[G.V()];
        edgeTo = new int[G.V()];
        isBipartitie = true;
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public boolean isBipartitie() {
        return isBipartitie;
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int adjVertex : G.adj(v)) {
            if (cycle != null)
                return;
            if (!marked[adjVertex]) {
                edgeTo[adjVertex] = v;
                colored[adjVertex] = !colored[v];
                dfs(G, adjVertex);
            } else if (colored[adjVertex] == colored[v]) { // Cycle and with the same color
                isBipartitie = false;
                cycle = new Stack<>();
                cycle.push(adjVertex);
                int from = v;
                while (from != adjVertex) {
                    cycle.push(from);
                    from = edgeTo[v];
                }
                cycle.push(adjVertex);
            }
        }
    }

}
