package Design;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
0 empty
>0 type of candy
changing during player's move and maintain stable state as below:
    >= 3 candies of the same type are adjacent, set empty
    crush simultaneously, empty has candies on top of itself,

 */
/*
all candies on board needed to crush will crush together
if we crush one by one, will have influence on others in the same round
so we mark them, and crush them together
 */
public class CandyCrush {

    List<int[]> candyToCrush;

    public int[][] candyCrush(int[][] a) {

        candyToCrush = new LinkedList<>();
        int m = a.length, n = a[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != 0) {
                    getCandiesToCrush(i, j, a, a[i][j]);
                }
            }
        }

        if (candyToCrush.isEmpty()) {
            return a;
        }

        eliminateCandies(a);

        restoreBoard(a);

        return candyCrush(a);
    }

    private void getCandiesToCrush(int x, int y, int[][] a, int val) {
        int m = a.length, n = a[0].length;
        if (x + 2 < m && val == a[x + 1][y] && val == a[x + 2][y]) {
            for (int k = x; k < m && (a[k][y] == val); k++) {
                candyToCrush.add(new int[]{k, y});
            }
        }
        if (y + 2 < n && val == a[x][y + 1] && val == a[x][y + 2]) {
            for (int k = y; k < n && (a[x][k] == val); k++) {
                candyToCrush.add(new int[]{x, k});
            }
        }
    }

    private void eliminateCandies(int[][] a) {
        for (int[] coordinate : candyToCrush) {
            a[coordinate[0]][coordinate[1]] = 0;
        }
    }

    private void restoreBoard(int[][] a) {
        int m = a.length, n = a[0].length;
        for (int j = 0; j < n; j++) {
            int bottom = m - 1, top = m - 1;
            while (top >= 0) {
                if (a[top][j] == 0) {
                    top--;
                } else {
                    a[bottom][j] = a[top][j];
                    bottom--;
                    top--;
                }
            }
            while (bottom >= 0) {
                a[bottom][j] = 0;
                bottom--;
            }
        }
    }

}
