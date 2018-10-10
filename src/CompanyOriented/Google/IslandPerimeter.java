package CompanyOriented.Google;

/**
 * 1 : land
 * 0 : water
 * one island only
 * output : perimeter of the island
 * Start State : land here
 * End State:
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] != 1) result++;
                    if (i == grid.length - 1 || grid[i + 1][j] != 1) result++;
                    if (j == 0 || grid[i][j - 1] != 1) result++;
                    if (j == grid[0].length - 1 || grid[i][j + 1] != 1) result++;
                }
            }
        }
        return result;
    }
}
