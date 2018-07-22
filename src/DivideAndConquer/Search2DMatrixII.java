package DivideAndConquer;

/*
base case: matrix[i][j] == target, return true;
if < target, col++
else, row--
 */
public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int col = matrix[0].length - 1;
        for (int row = 0; row < matrix.length; row++) {
            while (col >= 0 && matrix[row][col] > target) {
                col--;
            }
            if (col < 0) {
                return false;
            }
            if (matrix[row][col] == target) {
                return true;
            }
        }

        return false;
    }
}
