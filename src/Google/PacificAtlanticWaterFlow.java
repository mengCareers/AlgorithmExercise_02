package Google;
/**
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   * Atlantic
 * output : the list of cells where water can flow to both Pacific and Atlantic
 * water can flow in four directions from a cell to another one with height equal or lower
 * TP: if water flow from Pacific
 * can flow over area Sp,
 * if water flow from Atlantic
 * can flow over area Sa
 * Sp and Sa intersection is the answer
 * if (x, y) can reach P, canReachP[x][y] = true canReachP[x][y] can also indicate whether from border Ps has been visited
 * if (x, y) can reach A, canReachA[x][y] = true
 * if canReachP[x][y] = true and canReachA[x][y] = true, result
 * Even better, we DFS from target cell
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PacificAtlanticWaterFlow {

    private final static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        List<int[]> result = new ArrayList<>();
        boolean[][] canReachP = new boolean[matrix.length][matrix[0].length];
        boolean[][] canReachA = new boolean[matrix.length][matrix[0].length];
        /**
         * |
         * |
         * |_
         */
        for (int i = 0; i < matrix.length; i++) {
            if (!canReachP[i][0]) {
                canReachP[i][0] = true;
                flowFrom(i, 0, matrix, canReachP);
            }
        }
        /**
         *  ______
         * |
         */
        for (int j = 0; j < matrix[0].length; j++) {
            if (!canReachP[0][j]) {
                canReachP[0][j] = true;
                flowFrom(0, j, matrix, canReachP);
            }
        }
        /**
         * _
         *  |
         *  |
         *  |
         */
        for (int i = 0; i < matrix.length; i++) {
            if (!canReachA[i][matrix[0].length - 1]) {
                canReachA[i][matrix[0].length - 1] = true;
                flowFrom(i, matrix[0].length - 1, matrix, canReachA);
            }
        }
        /**
         * |______
         *
         */
        for (int j = 0; j < matrix[0].length; j++) {
            if (!canReachA[matrix.length - 1][j]) {
                canReachA[matrix.length - 1][j] = true;
                flowFrom(matrix.length - 1, j, matrix, canReachA);
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (canReachA[i][j] && canReachP[i][j]) {
                    result.add(new int[]{i, j});
                }
            }
        }

        return result;
    }

    private static void flowFrom(int x, int y, int[][] matrix, boolean[][] visited) {
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (nx < 0 || nx >= matrix.length || ny < 0 || ny >= matrix[0].length || visited[nx][ny] || matrix[nx][ny] < matrix[x][y]) {
                continue;
            }
            visited[nx][ny] = true;
            flowFrom(nx, ny, matrix, visited);
        }
    }

}
