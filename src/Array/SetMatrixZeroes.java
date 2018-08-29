package Array;

import java.util.Arrays;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {

        int rows = matrix.length, cols = matrix[0].length;
        boolean[] colIfAlreadyZero = new boolean[cols], rowIfAlreadyZero = new boolean[rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rowIfAlreadyZero[i] && colIfAlreadyZero[j]) {
                    continue;
                }
                if (matrix[i][j] == 0) {
                    rowIfAlreadyZero[i] = true;
                    colIfAlreadyZero[j] = true;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            if (rowIfAlreadyZero[i]) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int j = 0; j < cols; j++) {
            if (colIfAlreadyZero[j]) {
                for (int i = 0; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
