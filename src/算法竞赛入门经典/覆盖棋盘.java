package 算法竞赛入门经典;

public class 覆盖棋盘 {

    public static void main(String[] args) {
        覆盖棋盘 inst = new 覆盖棋盘();
        int n = 8;
        int[][] board = new int[n][n];
        inst.coverBoard(board, 2, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    int tile;

    public void coverBoard(int[][] board, int bi, int bj) {
        tile = 0;
        int n = board.length;
        coverBoardFrom(board, 0, 0, bi, bj, n);
    }

    private void coverBoardFrom(int[][] board, int x, int y, int bi, int bj, int size) {
        if (size == 1) {
            return;
        }
        int sz = size / 2;
        int t = ++tile;
        if (bi < x + sz && bj < y + sz) {
            coverBoardFrom(board, x, y, bi, bj, sz);
        } else {
            board[x + sz - 1][y + sz - 1] = t;
            coverBoardFrom(board, x, y, x + sz - 1, y + sz - 1, sz);
        }

        if (bi < x + sz && bj >= y + sz) {
            coverBoardFrom(board, x, y + sz, bi, bj, sz);
        } else {
            board[x + sz - 1][y + sz] = t;
            coverBoardFrom(board, x, y + sz, x + sz - 1, y + sz, sz);
        }

        if (bi >= x + sz && bj < y + sz) {
            coverBoardFrom(board, x + sz, y, bi, bj, sz);
        } else {
            board[x + sz][y + sz - 1] = t;
            coverBoardFrom(board, x + sz, y, x + sz, y + sz - 1, sz);
        }

        if (bi >= x + sz && bj >= y + sz) {
            coverBoardFrom(board, x + sz, y + sz, bi, bj, sz);
        } else {
            board[x + sz][y + sz] = t;
            coverBoardFrom(board, x + sz, y + sz, x + sz, y + sz, sz);
        }
    }

}
