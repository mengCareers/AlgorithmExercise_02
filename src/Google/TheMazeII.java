package Google;

/**
 State : dp[x][y] as the minimum distance from (start[0], start[1]) to (x, y).
 Final(Aim) State : dp[destination[0]][destination[1]]
 State Transfer :
 dp[curX][curY] = min(dp[preX][preY] + distance from cur to pre);
 pre(preX, preY) belongs to a list of positions such that ball can stop at, and if ball stop there, it can reach cur(curX, curY) in the next step.
 Please note that dist will equal to minDistToHere[nx][ny] if (x, y) is a dead end. We update minDistToHere[nx][ny] only if dist < minDistToHere[nx][ny].
 */
public class TheMazeII { // to add visited

    public static void main(String[] args) {
        TheMazeII inst = new TheMazeII();
        int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] destination = {4, 4};
        int result = inst.shortestDistance(maze, start, destination);
        System.out.println(result);
    }

    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] dp = new int[maze.length][maze[0].length];
        dp[start[0]][start[1]] = 1;
        shortestDistanceFrom(maze, start[0], start[1], destination, dp);
        return dp[destination[0]][destination[1]] - 1;
    }

    private void shortestDistanceFrom(int[][] maze, int x, int y, int[] destination, int[][] minDistToHere) {
        if (x == destination[0] && y == destination[1]) {
            return;
        }
        int nx = 0, ny = 0, dist = 0;
        for (int[] direction : directions) {
            nx = x;
            ny = y;
            dist = maze[x][y];
            while (nx + direction[0] >= 0 && nx + direction[0] < maze.length
                    && ny + direction[1] >= 0 && ny + direction[1] < maze[0].length
                    && maze[nx + direction[0]][ny + direction[1]] == 0) {
                nx += direction[0];
                ny += direction[1];
                dist++;
            }
            if (minDistToHere[nx][ny] > 0 && dist >= minDistToHere[nx][ny]) {
                continue;
            }
            minDistToHere[nx][ny] = dist;
            shortestDistanceFrom(maze, nx, ny, destination, minDistToHere);
        }
    }

}
/**/