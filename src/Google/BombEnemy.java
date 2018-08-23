package Google;

/**
 * DFS
 * each 'E' can be start
 * if (W or Edge) return ( no need )
 */
public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {
        int maxVal = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    maxVal = Math.max(maxVal, maxKilledEnemiesFrom(i, j, grid));
                }
            }
        }
        return maxVal;
    }

    private int maxKilledEnemiesFrom(int x, int y, char[][] grid) {
        int cntKilled = 0;
        // downward
        int xcopy = x, ycopy = y;
        while (xcopy < grid.length) {
            if (grid[xcopy][ycopy] == 'W') {
                break;
            }
            if (grid[xcopy][ycopy] == 'E') {
                cntKilled++;
            }
            xcopy++;
        }
        // upward
        xcopy = x;
        ycopy = y;
        while (xcopy >= 0) {
            if (grid[xcopy][ycopy] == 'W') {
                break;
            }
            if (grid[xcopy][ycopy] == 'E') {
                cntKilled++;
            }
            xcopy--;
        }
        // right
        xcopy = x;
        ycopy = y;
        while (ycopy < grid[0].length) {
            if (grid[xcopy][ycopy] == 'W') {
                break;
            }
            if (grid[xcopy][ycopy] == 'E') {
                cntKilled++;
            }
            ycopy++;
        }
        // left
        xcopy = x;
        ycopy = y;
        while (ycopy >= 0) {
            if (grid[xcopy][ycopy] == 'W') {
                break;
            }
            if (grid[xcopy][ycopy] == 'E') {
                cntKilled++;
            }
            ycopy--;
        }

        return cntKilled;
    }
}
