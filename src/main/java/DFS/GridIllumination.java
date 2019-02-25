package DFS;

import java.util.HashSet;
import java.util.Set;

public class GridIllumination {
    private int N;

    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        this.N = N;

        Set<Integer> lampSet = buildLampSet(lamps);
        int[][] mat = buildScoreMat(lamps);
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            result[i] = (mat[x][y] > 0) ? 1 : 0;
            turnOffLights(x, y, mat, lampSet);
        }
        return result;
    }

    private void turnOffLights(int x, int y, int[][] mat, Set<Integer> lampSet) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == x || j == y || Math.abs(i - x) == Math.abs(j - y)) {
                    int nmark = getMark(i, j);
                    if (lampSet.contains(nmark)) {
                        turnOffLight(i, j, mat, -1);
                        lampSet.remove(nmark);
                    }
                }
            }
        }
    }

    private void turnOffLight(int x, int y, int[][] mat, int score) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == x || j == y || Math.abs(i - x) == Math.abs(j - y)) {
                    mat[i][j] += score;
                }
            }
        }
        mat[x][y] += score * 3;
    }

    private int getMark(int x, int y) {
        return x * N + y;
    }

    private int[][] buildScoreMat(int[][] lamps) {
        int[][] mat = new int[N][N];
        for (int[] lamp : lamps) {
            turnOffLight(lamp[0], lamp[1], mat, 1);
        }
        return mat;
    }

    private Set<Integer> buildLampSet(int[][] lamps) {
        Set<Integer> lampSet = new HashSet<>();
        for (int[] lamp : lamps) {
            lampSet.add(getMark(lamp[0], lamp[1]));
        }
        return lampSet;
    }
}
