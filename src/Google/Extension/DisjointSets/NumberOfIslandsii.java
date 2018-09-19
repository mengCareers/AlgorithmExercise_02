package Google.Extension.DisjointSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberOfIslandsii {
    private final static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[] parent;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        parent = new int[m * n];
        Arrays.fill(parent, -1);
        int cntIslands = 0;
        List<Integer> result = new ArrayList<>();

        for (int[] pos : positions) {
            int id = pos[0] * n + pos[1];
            parent[id] = id;
            cntIslands++;
            for (int[] dir : directions) {
                int newX = pos[0] + dir[0];
                int newY = pos[1] + dir[1];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }
                int newId = newX * n + newY;
                if (parent[newId] == -1) {
                    continue;
                }
                if (union(id, newId))
                    cntIslands--;
            }
            result.add(cntIslands);
        }

        return result;
    }

    private boolean union(int x, int y) {

        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
            return true;
        }

        return false;
    }

    private int find(int x) {

        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }
}
