package Amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * input: grid of 0's and 1's connected 4-directionally
 * output: # of distinct islands
 */
/*
represent all islands by DFS using 4 coordinates
decide if they are distinct

start state: 1 place
end state: out of boundary || the island is shaped // visited?
for (x,y) we can go any direction with 1
 */

/**
 * MENTOR: WE MAY GATHER THEM AT TOP-LEFT CORNER
 */
public class NumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        Set<String> shapeVisited = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder curShape = new StringBuilder();
                    getIslandFrom(grid, i, j, "o", curShape);
                    grid[i][j] = 0;
                    shapeVisited.add(curShape.toString());
                }
            }
        }
        return shapeVisited.size();
    }

    // direction: u, d, l, r, o, e
    private void getIslandFrom(int[][] grid, int x, int y, String direction, StringBuilder shape) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
            return;
        }
        shape.append(direction);
        grid[x][y] = 0; // visited
        getIslandFrom(grid, x + 1, y, "d", shape);
        getIslandFrom(grid, x - 1, y, "u", shape);
        getIslandFrom(grid, x, y + 1, "r", shape);
        getIslandFrom(grid, x, y - 1, "l", shape);
        shape.append("e");
    }

}
