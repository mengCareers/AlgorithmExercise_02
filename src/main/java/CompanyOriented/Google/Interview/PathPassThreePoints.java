package CompanyOriented.Google.Interview;

import java.util.Arrays;

/**
 * input: m x n grid, 3 points' coordinates
 * output: true if exist a path from top-left to top-right passing 3 points
 * rule:
 * can only move right or top-right or bottom-right
 */

public class PathPassThreePoints {

    /**
     * Put all points together, sort by x-axis increasingly -- from left to right.
     * <p>
     * If a path exists from (x, y) to (nx, ny)
     * <p>
     * if move dist steps,
     * - move right: nx = x, ny = y + dist
     * - move top-right: nx = x - dist, ny = y + dist
     * - move bottom-right: nx = x + dist,ny = y + dist
     * <p>
     * so ny = y + dist, x - dist <= nx <= x + dist
     * x - (ny - y) <= nx <= x + (ny - y)
     * x - dy <= nx <= x + dy
     */

    public boolean existPath(int[][] grid, int[][] pointsToPass) {
        int[][] points = new int[5][2];
        points[0] = new int[]{0, 0};
        points[1] = new int[]{0, grid[0].length - 1};
        points[2] = pointsToPass[0];
        points[3] = pointsToPass[1];
        points[4] = pointsToPass[2];

        /* In the order of from left to right. */
        Arrays.sort(points, (a, b) -> (a[1] - b[1]));

        for (int i = 1; i < 5; i++) {
            int nx = points[i][0], x = points[i - 1][0], y = points[i - 1][1];
            int dy = points[i][1] - y;
            if (x - dy > nx || x + dy < nx)
                return false;
        }

        return true;
    }
}


