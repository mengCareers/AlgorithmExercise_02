package DynamicProgramming;

import java.util.Arrays;

/*
input: grid
output: minimum path sum from (0, 0) to (m-1, n-1)
state: state[i][j] as minimum path sum from (0, 0) to (i, j)
start state: state[0][0]
end state: state[m-1][n-1]
state transition:
state[i][j] = min(state[i-1][j], state[i][j-1]) + grid[i][j];
if i == 0, state[i][j] = state[i][j-1] + grid[i][j]
if j == 0, state[i][j] = state[i-1][j] + grid[i][j]
if i == 0 && j == 0, state[i][j] = grid[i][j]
 * */
public class MinimumPathSum {
    public int minPathSum_01(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] state = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    state[i][j] = grid[i][j];
                } else if (i == 0) {
                    state[i][j] = state[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    state[i][j] = state[i - 1][j] + grid[i][j];
                } else {
                    state[i][j] = Math.min(state[i - 1][j], state[i][j - 1]) + grid[i][j];
                }
            }
        }
        return state[m - 1][n - 1];
    }

    /**
     * If we define STATE dp[i][j] as the Minimum Path Sum from (0,0) to (i-1, j-1),
     * the AIM STATE is dp[m][n].
     * STATE TRANSFER as below:
     * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i - 1][j - 1]; for 1 < i <= m, 1 < j <= n
     * if i == 1, dp[i][j] = dp[i][j-1] + grid[i-1][j-1];
     * if j == 1, dp[i][j] = dp[i-1][j] + grid[i-1][j-1];
     * if i == 1 && j == 1, dp[i][j] = grid[i-1][j-1]
     *
     * @param grid
     * @return
     */
    public int minPathSum_02(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = grid[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    /**
     * to one dimensional
     * dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i - 1][j - 1]; 1 < i <= m, 1 < j <= n
     * if (j-1 == 0) dp[j] = dp[j] + grid[i - 1][j - 1];
     * if (i-1 == 0) dp[j] = dp[j - 1] + grid[i - 1][j - 1];
     * if (i-1 == 0 && j-1 == 0) dp[j] = grid[i - 1][j - 1];
     *
     * @param grid
     * @return
     */
    public int minPathSum_03(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[j] = grid[i - 1][j - 1];
                    continue;
                }
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[n];
    }
}
