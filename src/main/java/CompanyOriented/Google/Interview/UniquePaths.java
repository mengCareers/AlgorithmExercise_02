package CompanyOriented.Google.Interview;

/**
 * input: m x n grid
 * output：# of possible unique paths
 */
public class UniquePaths {

    /**
     * solution0:
     * we are at top-left initially
     * move down or right only
     * to bottom-right finally
     */

    public int solution0(int m, int n) {

        /* state[i][j] number of paths from (i, j) to (m-1, n-1). */
        int[][] state = new int[m][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1)
                    state[i][j] = 1;
                else if (i == m - 1)
                    state[i][j] = state[i][j + 1];
                else if (j == n - 1)
                    state[i][j] = state[i + 1][j];
                else
                    state[i][j] = state[i][j + 1] + state[i + 1][j];
            }
        }

        return state[0][0];
    }

    /**
     * solution1:
     * we are at top-left initially
     * move down or top-right or bottom-right only
     * to top-right finally
     */

    public int solution1(int m, int n) {

        /* state[i][j] as (0,0) to (i,j) # of paths. */
        int[][] state = new int[m][n];
        state[0][0] = 1;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                state[i][j] = state[i][j - 1];
                if (i > 0)
                    state[i][j] += state[i - 1][j - 1];
                if (i < m - 1)
                    state[i][j] += state[i + 1][j - 1];
            }

        }

        return state[0][n - 1];
    }

    /**
     * solution2:
     * we are at top-left initially
     * move down or top-right or bottom-right only
     * to top-right finally and need to pass 3 points
     */
    /**
     * state[i][j] =
     * state[i][j - 1] // comes from left
     * + state[i - 1][j - 1] //comes from top-left
     * + state[i + 1][j - 1] // comes from bottom-left
     */

    public int solution2(int m, int n, int[][] pointsToPass) {

        /* state[i][j] as (0,0) to (i,j) # of paths. */
        int[][] state = new int[m][n];
        state[0][0] = 1;
        int k = 0;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                state[i][j] = state[i][j - 1];
                if (i > 0)
                    state[i][j] += state[i - 1][j - 1];
                if (i < m - 1)
                    state[i][j] += state[i + 1][j - 1];
            }
            while (k < 3 && j == pointsToPass[k][1]) {
                for (int q = 0; q < m; q++) {
                    if (q != pointsToPass[k][0])
                        state[q][j] = 0;
                }
                k++;
            }
        }

        return k == 3 ? state[0][n - 1] : 0;
    }
}
