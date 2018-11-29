package CompanyOriented.Salesforce;

/**
 * input: chr[][] grid or W and B
 * B-region: Bs connected
 * output: # of B-region
 */
public class BlackWhite {

    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int countRegion(char[][] grid) {
        int rows = grid.length, cols = grid[0].length, numRegions = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 'B') {
                    numRegions++;
                    findRegionsFrom(i, j, grid);
                }
            }
        }

        return numRegions;
    }

    private void findRegionsFrom(int x, int y, char[][] grid) {
        grid[x][y] = '.';
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 'B') {
                findRegionsFrom(nx, ny, grid);
            }
        }
    }
}
/*
for each B, DFS
    durring DFS connecting all Bs and change to W, cnt++
 */