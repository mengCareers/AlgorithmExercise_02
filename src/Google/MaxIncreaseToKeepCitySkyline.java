package Google;

/**
 * list both skylines
 * smaller than min of them
 */
public class MaxIncreaseToKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int result = 0;
        int[] skylineTopBottom = getSkyline(grid, true);
        int[] skylineLeftRight = getSkyline(grid, false);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                result += Math.min(skylineTopBottom[j], skylineLeftRight[i]) - grid[i][j];
            }
        }
        return result;
    }

    private int[] getSkyline(int[][] grid, boolean isTopBottom) {
        int[] result;
        if (isTopBottom) {
            result = new int[grid[0].length];
            for (int j = 0; j < grid[0].length; j++) {
                result[j] = Integer.MIN_VALUE;
                for (int i = 0; i < grid.length; i++) {
                    result[j] = Math.max(grid[i][j], result[j]);
                }
            }
        } else {
            result = new int[grid.length];
            for (int i = 0; i < grid.length; i++) {
                result[i] = Integer.MIN_VALUE;
                for (int j = 0; j < grid[0].length; j++) {
                    result[i] = Math.max(grid[i][j], result[i]);
                }
            }
        }
        return result;
    }
}
