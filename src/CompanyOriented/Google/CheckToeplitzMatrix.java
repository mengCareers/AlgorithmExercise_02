package CompanyOriented.Google;

/**
 * Find if given matrix is Toeplitz or not
 * Toeplitz, all elements in a diagonal are same.
 * check each diagonal
 * 00 11 22 33
 * 01 12 23
 * 02 13
 * <p>
 * 10 21 32 43
 * 20 31 42
 * 30 41
 * The rules among them exist
 */
public class CheckToeplitzMatrix {

    public static void main(String[] args) {
        CheckToeplitzMatrix inst = new CheckToeplitzMatrix();

        int mat[][] = {
                {6, 7, 8, 9},
                {4, 6, 7, 8},
                {1, 4, 6, 7},
                {0, 1, 4, 6},
                {2, 0, 1, 4}
        };

        if (inst.isToeplitz(mat))
            System.out.println("Matrix is a Toepliz ");
        else
            System.out.println("Matrix is not a Toepliz ");
    }

    public boolean isToeplitz(int[][] mat) {
        for (int i = 0; i < mat[0].length; i++) {
            if (!isDiagonalValid(0, i, mat))
                return false;
        }
        for (int i = 1; i < mat.length; i++) {
            if (!isDiagonalValid(i, 0, mat))
                return false;
        }
        return true;
    }

    private boolean isDiagonalValid(int x, int y, int[][] mat) {
        int val = mat[x][y];
        while (++x < mat.length && ++y < mat[0].length) {
            if (mat[x][y] != val) {
                return false;
            }
        }
        return true;
    }
}
