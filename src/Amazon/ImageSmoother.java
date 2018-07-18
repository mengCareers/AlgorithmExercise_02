package Amazon;

import java.util.Arrays;

/**
 * input: M[][]
 * output: make each cell value average (rounding down) of all 8 surrounding cells and itself
 */
/*
BFS or DFS
 */
public class ImageSmoother {
    private final static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};

    public int[][] imageSmoother(int[][] M) {
        int rows = M.length, cols = M[0].length;
        int[][] copyM = copyArray(M);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                imageSmootherFrom(i, j, copyM, M);
            }
        }
        return M;
    }

    private void imageSmootherFrom(int x, int y, int[][] mat, int[][] result) {
        int cntSurroundingCells = 1;
        int sumSurroundingCells = mat[x][y];
        for (int[] direction : directions) {
            int nx = x + direction[0];
            int ny = y + direction[1];
            if (nx >= 0 && nx < mat.length && ny >= 0 && ny < mat[0].length) {
                sumSurroundingCells += mat[nx][ny];
                cntSurroundingCells++;
            }
        }
        result[x][y] = sumSurroundingCells / cntSurroundingCells;
    }

    private int[][] copyArray(int[][] M) {
        int rows = M.length, cols = M[0].length;
        int[][] copyM = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            copyM[i] = Arrays.copyOf(M[i], cols);
        }
        return copyM;
    }
}
