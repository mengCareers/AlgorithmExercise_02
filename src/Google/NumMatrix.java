package Google;

/**
 Binary Indexed Tree
 It is an array rather than a tree. The essence of it is e[i], which represents range sum to nums[i]. And the range is decided by the position of rightmost 1 in the binary representation of i.
 For example,
 i = 4 = 100　 e[4] = a[1]+a[2]+a[3]+a[4]; 	range = 4
 i = 6 = 110　	e[6] = a[5]+a[6]; 	range = 2
 i = 7 = 111　	e[7] = a[7];	range = 1
 In this way,
 rangeSum(a[5] to a[7])
 = rangeSum(a[1] to a[7]) - rangeSum(a[1] to a[4])
 = e[7] + e[6] + e[4] - e[4],
 we can save a lot of work with the use of e[ ].
 Attention: We escape e[0] by adding one more element to e[ ] than nums[ ] for getRightmostOne(0) doesn't make sense. However, getRightmostOne(1) will always return 0, i.e., e[1] = 0.

 Thinking Process
 Range Sum Query 2D is composed of Range Sum Query 1Ds, i.e., a row is corresponding to a of Range Sum Query 1D problem.
 So we build int[][] es, such that es[i] is corresponding to the e[ ] of BIT (we mentioned above) for the row i.
 Attention: Standard update(int val, int index) in BIT means nums[index] += val.
 However, according to the problem description, we should take that as nums[index] = val.


 */
public class NumMatrix {
    int[][] matrix;
    int[][] es;

    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        this.matrix = matrix;
        es = new int[matrix.length][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 1; c <= matrix[0].length; c++) {
                update(c, matrix[r][c - 1], es[r]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int valChanged = val - matrix[row][col];
        matrix[row][col] = val;
        int e[] = es[row];
        update(col + 1, valChanged, e);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int r = row1; r <= row2; r++) {
            int[] e = es[r];
            sum += rangeSum(col1 + 1, col2 + 1, e);
        }
        return sum;
    }

    private static int rangeSum(int i, int[] e) {
        int sum = 0;
        while (i != 0) {
            sum += e[i];
            i = i - getRightmostOne(i);
        }
        return sum;
    }

    /**
     * row[i] += v;
     *
     * @param i
     * @param v
     */
    private static void update(int i, int v, int[] e) {
        while (i < e.length) {
            e[i] += v;
            i = i + getRightmostOne(i);
        }
    }

    private static int getRightmostOne(int num) {
        return num & (~num + 1);
    }

    private static int rangeSum(int start, int end, int[] e) {
        return rangeSum(end, e) - rangeSum(start - 1, e);
    }
}