package Google.Extension;

import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0)
            return 0;

        DisjointSets disjointSets = new DisjointSets(grid);
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int land = i * n + j;
                    for (int[] dir : directions) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == '1') {
                            int neighbour = ni * n + nj;
                            disjointSets.union(land, neighbour);
                        }
                    }
                }
            }
        }

        return disjointSets.cntSet;
    }

    class DisjointSets {

        int cntSet;
        int[] parent;
        int m, n;

        public DisjointSets(char[][] grid) {

            m = grid.length;
            n = grid[0].length;
            cntSet = 0;
            parent = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        cntSet++;
                        parent[i * n + j] = i * n + j;
                    }
                }
            }
        }

        public int find(int x) {

            if (x == parent[x]) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {

            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                cntSet--;
                parent[rootX] = rootY;
            }
        }
    }
}
