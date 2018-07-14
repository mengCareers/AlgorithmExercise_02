package Google;

/**
 * input : a tree with N nodes with one additional edge added
 * output: the additional edge
 Initially, each node can be regarded as a disjoint set.
 When we count an edge in, we UNION two nodes.
 If two nodes have already been in the same disjoint set (we detect that with the help of FIND), the current edge is redundant.
 * # UNION FIND, REMOVE ALREDY CONNECTED
 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;
        DisjointSet ds = new DisjointSet(n);

        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            if (ds.find(u) == ds.find(v)) {
                return edge;
            } else {
                ds.union(u, v);
            }
        }
        return null;

    }

    class DisjointSet {

        int[] parent;

        public DisjointSet(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            parent[rootA] = rootB;
        }

        public int find(int x) {
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

    }
}
