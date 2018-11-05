package CompanyOriented.Karat;

import java.util.*;


public class ZeroRectangle {

    public static void main(String[] args) {
        ZeroRectangle zeroRectangle = new ZeroRectangle();
        int[][] matrix = {
                {1, 1, 1, 1, 1, 1},
                {0, 0, 1, 0, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {1, 1, 1, 0, 1, 0},
                {1, 0, 0, 1, 1, 1}};

        System.out.println(zeroRectangle.getZeroRectangles(matrix));
        matrix = new int[][]{
                {1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1}
        };
        System.out.println(zeroRectangle.getZeroConnectedComponents(matrix));
    }

    private int[][] directionsRectangle = {{0, 1}, {1, 0}};
    private int[][] directionsComponent = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private static int rows, cols;

    // Q1
    public List<List<Integer>> getZeroRectangles(int[][] matrix) {

        List<List<Integer>> result = new ArrayList<>(); // Result list.
        cols = matrix[0].length;
        rows = matrix.length;
        Set<Integer> visited = new HashSet<>(); // Cells visited.

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0 && !visited.contains(encode(i, j))) {
                    List<Integer> orders = bfsTraversal(encode(i, j), visited, matrix, directionsRectangle);
                    int[] downright = decode(orders.get(orders.size() - 1));
                    result.add(new ArrayList<>(Arrays.asList(i, j, downright[0], downright[1])));
                }
            }
        }

        return result;
    }

    // Q2
    public List<List<Integer>> getZeroConnectedComponents(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>(); // Result list.
        cols = matrix[0].length;
        rows = matrix.length;
        Set<Integer> visited = new HashSet<>(); // Cells visited.

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0 && !visited.contains(encode(i, j))) {
                    List<Integer> orders = bfsTraversal(encode(i, j), visited, matrix, directionsComponent);
                    result.add(buildResult(orders));
                }
            }
        }

        return result;
    }

    private List<Integer> buildResult(List<Integer> orders) {
        List<Integer> result = new ArrayList<>();
        int[] coordinate;
        for (int order : orders) {
            coordinate = decode(order);
            result.add(coordinate[0]);
            result.add(coordinate[1]);
        }
        return result;
    }

    private List<Integer> bfsTraversal(int startOrder, Set<Integer> visited, int[][] matrix, int[][] directions) {

        List<Integer> orders = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startOrder);
        visited.add(startOrder);

        while (!queue.isEmpty()) {
            orders.add(queue.peek());
            int[] curr = decode(queue.poll());
            for (int[] dir : directions) {
                int nx = curr[0] + dir[0];
                int ny = curr[1] + dir[1];
                if (nx >= 0 && ny >= 0 && nx < rows && ny < cols) {
                    int newOrder = encode(nx, ny);
                    if (matrix[nx][ny] == 0 && !visited.contains(newOrder)) {
                        queue.offer(newOrder);
                        visited.add(newOrder);
                    }
                }
            }
        }
        return orders;
    }

    private int encode(int x, int y) {
        return x * cols + y;
    }

    private int[] decode(int order) {
        return new int[]{order / cols, order % cols};
    }

    // Q3
    public int numDistinctIslands(int[][] grid) {

        cols = grid[0].length;
        rows = grid.length;
        Set<Integer> visited = new HashSet<>(); // Cells visited.
        Set<String> uniqueShapes = new HashSet<>(); // Unique shpes.
        StringBuilder shape;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    int order = encode(i, j);
                    if (!visited.contains(order)) {
                        visited.add(order);
                        shape = new StringBuilder("o");
                        dfsTraversal(i, j, visited, grid, shape);
                        uniqueShapes.add(shape.toString());
                    }
                }
            }
        }

        return uniqueShapes.size();
    }


    private void dfsTraversal(int x, int y, Set<Integer> visited, int[][] matrix, StringBuilder shape) {

        for (int i = 0; i < directionsComponent.length; i++) {
            int nx = x + directionsComponent[i][0];
            int ny = y + directionsComponent[i][1];
            if (nx >= 0 && ny >= 0 && nx < rows && ny < cols) {
                int newOrder = encode(nx, ny);
                if (matrix[nx][ny] == 1 && !visited.contains(newOrder)) {
                    shape.append(i);
                    visited.add(newOrder);
                    dfsTraversal(nx, ny, visited, matrix, shape);
                }
            }
        }
        shape.append("_");

    }

}
