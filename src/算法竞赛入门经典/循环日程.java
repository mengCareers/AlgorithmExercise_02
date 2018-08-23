package 算法竞赛入门经典;

/**
 * n people
 */
public class 循环日程 {

    public static void main(String[] args) {
        循环日程 inst = new 循环日程();
        inst.circulateSchedule(8);
    }

    public void circulateSchedule(int n) {
        int[][] table = new int[n][n];
        table[0][0] = 1;
        circulateScheduleFrom(0, 0, n, table);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void circulateScheduleFrom(int row, int col, int n, int[][] table) {

        if (n == 1) {
            return;
        }
        int half = n / 2;
        table[row + half][col + half] = table[row][col];
        table[row + half][col] = table[row][col] + half;
        table[row][col + half] = table[row][col] + half;
        circulateScheduleFrom(row, col, half, table);
        circulateScheduleFrom(row, col + half, half, table);
        circulateScheduleFrom(row + half, col, half, table);
        circulateScheduleFrom(row + half, col + half, half, table);
    }
}
