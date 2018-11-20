package DisjointSets;

import java.util.HashSet;
import java.util.Set;

public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'O', 'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'O'}, {'X', 'O', 'X', 'O', 'X'}, {'O', 'X', 'O', 'O', 'O'}, {'X', 'X', 'O', 'X', 'O'}
        };
        SurroundedRegions inst = new SurroundedRegions();
        inst.solve(board);
    }

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    Set<Integer> setInvalid;

    public void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }

        setInvalid = new HashSet<>();
        DisjointSets disjointSets = new DisjointSets(board);
        int rows = board.length, cols = board[0].length;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if ((x == 0 || x == rows - 1 || y == 0 || y == cols - 1) && board[x][y] == 'O') {
                    int borderO = x * cols + y;
                    setInvalid.add(borderO);
                    for (int[] dir : directions) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];
                        if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && board[nx][ny] == 'O') {
                            int neighbor = nx * cols + ny;
                            setInvalid.add(neighbor);
                            disjointSets.union(borderO, neighbor);
                        }
                    }
                }
            }
        }

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                if (board[x][y] == 'O' && !setInvalid.contains(x * cols + y)) {
                    board[x][y] = 'X';
                }
            }
        }

    }

    class DisjointSets {

        int[] parent;

        public DisjointSets(char[][] board) {

            int rows = board.length, cols = board[0].length;
            parent = new int[rows * cols];

            for (int x = 0; x < rows; x++) {
                for (int y = 0; y < cols; y++) {
                    if (board[x][y] == 'O') {
                        int id = x * cols + y;
                        parent[id] = id;
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
                parent[rootX] = rootY;
            }
        }
    }
}
