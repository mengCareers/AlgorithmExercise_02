/**
 * The problem is to find the sum of the elements defined by (r1, c1) and (r2, c2)
 * State : for row i, the sum of the elements from index 0 to index c
 * Final State :
 * State Transfer : dp[i][j] = dp[i][j - 1] + mat[i][j]
 */
public class RangeSumQuery2DImmutable {

    int[][] dp;

    public RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        dp = new int[matrix.length][matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (c == 0) {
                    dp[r][c] = matrix[r][c];
                    continue;
                }
                dp[r][c] = dp[r][c - 1] + matrix[r][c];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        if (col1 == 0) {
            for (int r = row1; r <= row2; r++) {
                sum += dp[r][col2];
            }
        } else {
            for (int r = row1; r <= row2; r++) {
                sum += dp[r][col2] - dp[r][col1 - 1];
            }
        }
        return sum;
    }
}

