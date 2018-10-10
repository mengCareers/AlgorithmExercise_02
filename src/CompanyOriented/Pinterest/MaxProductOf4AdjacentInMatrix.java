package CompanyOriented.Pinterest;

/*
Given a square matrix, find the maximum product of four adjacent elements of matrix. The adjacent elements of matrix can be top, down, left, right, diagonal or anti diagonal. The four or more numbers should be adjacent to each other.
Note: n should be greater than or equal to 4 i.e n >= 4
 */
public class MaxProductOf4AdjacentInMatrix {
    public static int findMaxProduct(int[][] matrix) {
        int n = matrix.length, maxProduct = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j >= 3)
                    maxProduct = Math.max(maxProduct, matrix[i][j] * matrix[i][j - 1] * matrix[i][j - 2] * matrix[i][j - 3]);
                if (i >= 3)
                    maxProduct = Math.max(maxProduct, matrix[i][j] * matrix[i - 1][j] * matrix[i - 2][j] * matrix[i - 3][j]);
                if (j >= 3 && i >= 3)
                    maxProduct = Math.max(maxProduct, matrix[i][j] * matrix[i - 1][j - 1] * matrix[i - 2][j - 2] * matrix[i - 3][j - 3]);
                if (i >= 3 && j < n - 3)
                    maxProduct = Math.max(maxProduct, matrix[i - 3][j + 3] * matrix[i - 2][j + 2] * matrix[i - 1][j + 1] * matrix[i][j]);
            }
        }
        return maxProduct;
    }
}
