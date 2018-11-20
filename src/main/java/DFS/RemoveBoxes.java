package DFS;

/*
input: boxes
output: maximum points I can get
remove k continuous boxes with the same color, and get k * k points
 */

import java.util.LinkedList;
import java.util.List;

/**
 * 1, 3, 2, 2, 2, 3, 4, 3, 1 [3 x 3] 9
 * 1, 4, 3, 1 [3 x 2] 6
 * 1, 3, 1 [1 x 1] 1
 * 1, 1 [1 x 1] 1
 * [2 x 2] 2
 * 19
 * <p>
 * DP
 * points[i, j] = Math.max(points after remove, points not remove)
 */
public class RemoveBoxes {

    public static void main(String[] args) {

        RemoveBoxes inst = new RemoveBoxes();
        int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
        int answer = inst.removeBoxes(boxes);
        System.out.println(answer);

    }

    public int removeBoxes(int[] boxes) {

        int n = boxes.length;
        int[][][] state = new int[n][n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                state[i][i][k] = (k + 1) * (k + 1);
            }
        }

        for (int l = 1; l < n; l++) {
            for (int j = l; j < n; j++) {
                int i = j - l;
                for (int k = 0; k <= i; k++) {
                    int curRes = (k + 1) * (k + 1) + state[i + 1][j][0];
                    for (int m = i + 1; m <= j; m++) {
                        if (boxes[m] == boxes[i]) {
                            curRes = Math.max(curRes, state[i + 1][m - 1][0] + state[m][j][k + 1]);
                        }
                    }
                    state[i][j][k] = curRes;
                }
            }
        }

        return (n == 0) ? 0 : state[0][n - 1][0];
    }


    public int removeBoxesBF_ParaAsSubResult(int[] boxes) {

        return removeBoxesFrom(boxes, 0);
    }


    private int removeBoxesFrom(int[] curBoxes, int cntPoints) {

        if (curBoxes.length == 0) {
            return cntPoints;
        }

        int maxPoints = 0;
        for (int i = 0; i < curBoxes.length; i++) {
            int amount = 1;
            int curBox = curBoxes[i];
            int j = i + 1;
            while (j < curBoxes.length && curBoxes[j] == curBox) {
                amount++;
                j++;
            }
            int[] newBoxes = new int[curBoxes.length - amount];
            int ni = 0;
            for (int k = 0; k < curBoxes.length; k++) {
                if (k < i || k >= j) {
                    newBoxes[ni++] = curBoxes[k];
                }
            }
            maxPoints = Math.max(removeBoxesFrom(newBoxes, cntPoints + amount * amount), maxPoints);
        }

        return maxPoints;
    }


    public int removeBoxesBF(int[] boxes) {

        return removeBoxesFrom(boxes);
    }


    private int removeBoxesFrom(int[] curBoxes) {

        if (curBoxes.length == 0) {
            return 0;
        }

        int maxPoints = 0;
        for (int i = 0; i < curBoxes.length; i++) {
            int amount = 1;
            int curBox = curBoxes[i];
            int j = i + 1;
            while (j < curBoxes.length && curBoxes[j] == curBox) {
                amount++;
                j++;
            }
            int[] newBoxes = new int[curBoxes.length - amount];
            int ni = 0;
            for (int k = 0; k < curBoxes.length; k++) {
                if (k < i || k >= j) {
                    newBoxes[ni++] = curBoxes[k];
                }
            }
            maxPoints = Math.max(removeBoxesFrom(newBoxes) + amount * amount, maxPoints);
        }

        return maxPoints;
    }
}
