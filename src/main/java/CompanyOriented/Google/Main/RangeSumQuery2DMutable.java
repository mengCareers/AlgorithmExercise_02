package CompanyOriented.Google;

public class RangeSumQuery2DMutable {
    int[][] matrix;

    public RangeSumQuery2DMutable(int[][] matrix) {
        this.matrix = matrix;
    }

    public void update(int row, int col, int val) {
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) {
            for (int c = col1; c <= col2; c++) {
                sum += matrix[row1][col1];
            }
        }
        return sum;
    }
}
