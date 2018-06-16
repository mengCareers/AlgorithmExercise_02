package Google;

/**
 * State : dp[x][y] as true if ball begins at start and can stop at (x, y)
 * Final(Aim) State : dp[destination[0]][destination[1]]
 * State Transfer : dp[x][y] = dp[preX][preY] && (preX,preY) can stop at (x, y)
 * <p>
 * DFS
 * Start at start,
 * for state (x, y) {
 * rolling up, down, left or right, keep rolling
 * until hit a wall, mark stopping position as true
 * }
 * End at destination,
 * <p>
 */
public class TheMaze {

    private final static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return hasPathFrom(maze, start[0], start[1], destination, new boolean[maze.length][maze[0].length]);
    }

    private boolean hasPathFrom(int[][] maze, int x, int y, int[] destination, boolean[][] visited) {
        if (x == destination[0] && y == destination[1]) {
            return true;
        }
        int nx = 0, ny = 0;
        for (int[] direction : directions) {
            nx = x;
            ny = y;
            while ((nx + direction[0] >= 0) && (nx + direction[0] < maze.length)
                    && (ny + direction[1] >= 0) && (ny + direction[1] < maze[0].length)
                    && (maze[nx + direction[0]][ny + direction[1]] == 0)) {
                nx += direction[0];
                ny += direction[1];
            }
            if (visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            if (hasPathFrom(maze, nx, ny, destination, visited)) {
                return true;
            }
        }
        return false;
    }
}
