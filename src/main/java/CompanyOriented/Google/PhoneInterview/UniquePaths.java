package CompanyOriented.Google.PhoneInterview;

import java.util.Arrays;

/*
input: m, n -- m x n grid, start located at top-left
move down or right
output：# of possible unique paths
 */
public class UniquePaths {
    public int uniquePaths_TopLeft_TopRight_Pass3Points(int m, int n, int[][] pointsToPass) {
        Arrays.sort(pointsToPass, (a, b) -> (a[1] - b[1]));

        int[][] state = new int[m][n];
        state[0][0] = 1;
        int k = 0;
        while (pointsToPass[k][1] == 0)
            k++;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                state[i][j] = state[i][j - 1];
                if (i == 0)
                    state[i][j] += state[i + 1][j - 1];
                else if (i == m - 1)
                    state[i][j] += state[i - 1][j - 1];
                else
                    state[i][j] += state[i - 1][j - 1] + state[i + 1][j - 1];
            }
            if (k < 3 && j == pointsToPass[k][1]) {
                for (int q = 0; q < m; q++) {
                    if (q != pointsToPass[k][0])
                        state[q][j] = 0;
                }
                k++;
            }
        }

        return (k == 3) ? state[0][n - 1] : 0;
    }

    public boolean existPaths_TopLeft_TopRight_Pass3Points(int m, int n, int[][] pointsToPass) {
        int[][] points = new int[5][2];
        points[0] = new int[]{0, 0};
        points[1] = new int[]{0, n - 1};
        points[2] = pointsToPass[0];
        points[3] = pointsToPass[1];
        points[4] = pointsToPass[2];

        /* From left to right. */
        Arrays.sort(points, (a, b) -> (a[1] - b[1]));

        for (int i = 1; i < 5; i++) {
            int dy = points[i][1] - points[i - 1][1];
            int x = points[i][0];
            int y = points[i][1];
            if (x - dy > points[i - 1][0] || points[i - 1][0] > x + dy)
                return false;
        }

        return true;
    }

    public int uniquePaths_TopLeft_TopRight(int m, int n) {

        /* state[i][j] number of paths from (0, 0) to (i, j). */
        int[][] state = new int[m][n];
        state[0][0] = 1;

        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                state[i][j] = state[i][j - 1];
                if (i == 0)
                    state[i][j] += state[i + 1][j - 1];
                else if (i == m - 1)
                    state[i][j] += state[i - 1][j - 1];
                else
                    state[i][j] += state[i - 1][j - 1] + state[i + 1][j - 1];
            }
        }

        return state[0][n - 1];
    }

    public int uniquePaths(int m, int n) {

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
}
/*
overlap subproblem
optimal structure

x x
x x(i,j)
If we define state(i, j) from (i, j) to (m-1, n-1)
goal state: state(0, 0)
state(i, j) = state(i, j + 1) + state(i + 1, j) from bigger to smaller
e.g. m = 7, n = 3
state(7,2) = state(7, 3)
if (i == m -1 && j == n -1)
    state(i, j) = 1
if (i == m -1 )
    state(i, j) = state(i, j + 1)
if (j == n - 1)
    state(i, j) = state(i + 1, j)
initial state
    state(m-1, n-1) = 0 or 1
 */