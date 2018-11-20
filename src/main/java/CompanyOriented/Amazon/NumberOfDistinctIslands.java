package CompanyOriented.Amazon;

import java.util.*;

/**
 * 684. Redundant Connection
 */
public class NumberOfDistinctIslands {
    public static void main(String[] args) {
        NumberOfDistinctIslands inst = new NumberOfDistinctIslands();
        int[][] grid = {
                {1, 1, 0},
                {0, 1, 1},
                {0, 0, 0},
                {1, 1, 1},
                {0, 1, 0}};
        System.out.println(inst.numDistinctIslands(grid));
    }

    public int numDistinctIslands(int[][] grid) {

        Set<String> dinstinctIslands = new HashSet<>();
        StringBuilder curShape;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) { // island start
                    curShape = new StringBuilder();
                    describeIslandShape(i, j, grid, curShape, "o");
                    dinstinctIslands.add(curShape.toString());
                }
            }
        }
        return dinstinctIslands.size();
    }

    private void describeIslandShape(int x, int y, int[][] grid, StringBuilder curShape, String direction) {

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
            return;
        }
        grid[x][y] = 0;
        curShape.append(direction);
        describeIslandShape(x, y + 1, grid, curShape, "r");
        describeIslandShape(x, y - 1, grid, curShape, "l");
        describeIslandShape(x + 1, y, grid, curShape, "d");
        describeIslandShape(x - 1, y, grid, curShape, "u");
        curShape.append("_");
    }
}
