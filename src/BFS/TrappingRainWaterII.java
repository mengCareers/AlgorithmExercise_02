package BFS;

import java.util.PriorityQueue;

public class TrappingRainWaterII {
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int trapRainWater(int[][] heightMap) {

        if (heightMap == null || heightMap.length == 0 || heightMap[0].length <= 1) {
            return 0;
        }

        PriorityQueue<int[]> pq_minheight = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        int rows = heightMap.length, cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
                    pq_minheight.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int result = 0;

        while (!pq_minheight.isEmpty()) {
            int[] cur = pq_minheight.poll();
            for (int[] direction : directions) {
                int nx = cur[0] + direction[0];
                int ny = cur[1] + direction[1];
                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    result += Math.max(0, cur[2] - heightMap[nx][ny]);
                    pq_minheight.add(new int[]{nx, ny, Math.max(heightMap[nx][ny], cur[2])});
                }
            }
        }

        return result;
    }
}
