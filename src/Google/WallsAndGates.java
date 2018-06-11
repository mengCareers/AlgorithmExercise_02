package Google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * -1  wall
 * 0   gate
 * INF empty
 * <p>
 * Fill each INF with distance to its nearst 0, if impossible to reach 0, remain INF
 * Logical:
 * for each INF, start from it, bfs, to find nearest 0 and set its value to the level
 * <p>
 * BFS is fluffy,
 * MENTOR : TRY DFS, FROM 0
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    updateDistanceFrom(i, j, rooms, new boolean[rooms.length][rooms[0].length], 0);
                }
            }
        }
    }

    private final static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static void updateDistanceFrom(int x, int y, int[][] rooms, boolean[][] visited, int dist) {

        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length || visited[x][y] || rooms[x][y] < dist) {
            return;
        }
        visited[x][y] = true;
        rooms[x][y] = dist;
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            updateDistanceFrom(nx, ny, rooms, visited, dist + 1);
        }
        visited[x][y] = false;
    }

}
