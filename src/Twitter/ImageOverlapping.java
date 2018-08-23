package Twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ImageOverlapping {
    public static void main(String[] args) {
        String[][] grid1 = {
                {"0", "1", "0", "0"},
                {"1", "0", "0", "1"},
                {"0", "0", "1", "1"},
                {"0", "0", "1", "1"}};
        String[][] grid2 = {
                {"0", "1", "0", "1"},
                {"1", "0", "0", "1"},
                {"0", "0", "1", "1"},
                {"0", "0", "1", "1"}};
        System.out.println(imageSame(grid1, grid2));
    }

    public static int imageSame(String[][] grid1, String[][] grid2) {

        Map<String, String> startToShape1 = new HashMap<>();
        findRegion(grid1, startToShape1);

        Map<String, String> startToShape2 = new HashMap<>();
        findRegion(grid2, startToShape2);

        int cntSame = 0;
        for (String key : startToShape2.keySet()) {
            if (startToShape1.containsKey(key) && startToShape1.get(key).equals(startToShape2.get(key))) {
                cntSame++;
            }
        }

        return cntSame;
    }

    private static void findRegion(String[][] grid, Map<String, String> startToShape) {

        int rows = grid.length, cols = grid[0].length;
        Set<String> visited = new HashSet<>();
        StringBuilder curShape;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].equals("1")) {
                    curShape = new StringBuilder();
                    findRegionFrom(i, j, grid, curShape, "o");
                    startToShape.put(i + "," + j, curShape.toString());
                }
            }
        }
    }

    private static void findRegionFrom(int x, int y, String[][] grid, StringBuilder curShape, String direction) {

        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y].equals("1")) {
            grid[x][y] = "0";
            curShape.append(direction);
            findRegionFrom(x + 1, y, grid, curShape, "d"); // down
            findRegionFrom(x - 1, y, grid, curShape, "u"); // up
            findRegionFrom(x, y + 1, grid, curShape, "r"); // right
            findRegionFrom(x, y - 1, grid, curShape, "l"); // left
            curShape.append(" ");
        }
    }

}
