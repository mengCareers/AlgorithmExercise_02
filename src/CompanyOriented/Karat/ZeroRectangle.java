package CompanyOriented.Karat;

import java.util.*;

/**
 * friend cycle
 */
public class ZeroRectangle {

    public static void main(String[] args) {
        int[] u = {1, 2};
        List<int[]> list = new ArrayList<>();
        list.add(u);
        System.out.println(list.get(0)[0]);
        u = new int[]{3, 4};
        list.add(u);
        System.out.println(list.get(0)[0]);
        System.out.println(list.get(1)[0]);
    }

    boolean[][] visited;
    List<int[]> endPoints;// [startPoint, endPoint]
    List<List<int[]>> shapeList; // all points of each shape in list

    public void rectangle(int[][] matrix) {
        endPoints = new ArrayList<>();
        shapeList = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0 && !visited[i][j]) {
                    List<int[]> temp = new ArrayList<>();
                    endPoints.add(new int[]{i, j});
                    bfs(matrix, i, j, temp);
                }
            }
        }

    }

    int[][] dirsForStartEnd = {{0, 1}, {1, 0}};
    int[][] dirsForAll = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void bfs(int[][] matrix, int x, int y, List<int[]> nodesList) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] curNode = queue.poll();
            nodesList.add(curNode);
            for (int[] dir : dirsForStartEnd) {
                int nx = curNode[0] + dir[0];
                int ny = curNode[1] + dir[1];
                if (nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length || visited[nx][ny] || matrix[nx][ny] != 0) {
                    continue;
                }
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});

            }
            if (queue.isEmpty()) {
                endPoints.add(new int[]{curNode[0], curNode[1]});
            }
        }
    }


    public int numDistinctIslands(int[][] grid) {
        Set<String> dinstinctIslands = new HashSet<>();
        StringBuilder curShape = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    curShape.setLength(0);
                    curShape.append("s"); // start
                    describeIslandShape(curShape, i, j, grid);
                    dinstinctIslands.add(curShape.toString());
                }
            }
        }

        return dinstinctIslands.size();
    }

    private void describeIslandShape(StringBuilder curShape, int x, int y, int[][] grid) {

        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) {
            return;
        }

        visited[x][y] = true;
        describeIslandShape(curShape.append("d"), x + 1, y, grid); // down
        describeIslandShape(curShape.append("u"), x - 1, y, grid); // up
        describeIslandShape(curShape.append("r"), x, y + 1, grid); // right
        describeIslandShape(curShape.append("l"), x, y - 1, grid); // left

        curShape.append("_");
    }
}
