package DynamicProgramming;

public class MaxRect {
    public static void main(String[] args) {
        MaxRect inst = new MaxRect();
        String[][] matrix = {{"1", "0", "1", "0", "0"}, {"1", "0", "1", "1", "1"}, {"1", "1", "1", "1", "1"}, {"1", "0", "0", "1", "0"}};
        inst.maximalRectangle(matrix);
    }

    public int maximalRectangle(String[][] matrix) {
        if (matrix.length == 0) return 0;

        int maxArea = 0, rows = matrix.length, cols = matrix[0].length;
        int[][] consecutiveOnesToLeft = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j].equals("0")) continue;
                // (i, j) as bottom right corner of a rectangle.
                consecutiveOnesToLeft[i][j] = (j > 0) ? consecutiveOnesToLeft[i][j - 1] + 1 : 1;
                int curWidth = consecutiveOnesToLeft[i][j];
                for (int k = i; k >= 0; k--) {
                    curWidth = Math.min(curWidth, consecutiveOnesToLeft[k][j]);
                    maxArea = Math.max(maxArea, curWidth * (i - k + 1));
                }
            }
        }
        return maxArea;
    }
}
