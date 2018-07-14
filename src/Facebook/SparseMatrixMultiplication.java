package Facebook;

/**
 * input: 2 sparse matrices A, B
 * output: AB
 */
public class SparseMatrixMultiplication {
    public int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length, colsA = A[0].length, colsB = B[0].length;
        int[][] result = new int[rowsA][colsB];
        for (int xA = 0; xA < rowsA; xA++) {
            for (int yA = 0; yA < colsA; yA++) {
                if (A[xA][yA] != 0) {
                    for (int yB = 0; yB < colsB; yB++) {
                        result[xA][yB] += A[xA][yA] * B[yA][yB];
                    }
                }
            }
        }
        return result;
    }

}
