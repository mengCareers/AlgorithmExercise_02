package Array;

/*
 * 8 * 8
 * 1 w rook    R
 * n w bishops B
 * n b pawns   p
 *
 *
 * */
public class AvailableCapturesForRook {
    private static final int BOARD_LEN = 8;

    public int numRookCaptures(char[][] board) {
        // Find white rook R.
        for (int i = 0; i < BOARD_LEN; i++) {
            for (int j = 0; j < BOARD_LEN; j++) {
                if (board[i][j] == 'R') {
                    return captureAround(i, j, board);
                }
            }
        }
        return 0;
    }

    private int captureAround(int x, int y, char[][] board) {
        int numCaptures = 0;
        // Go up, x keep decrease.
        int nx = x;
        while (nx >= 0) {
            if (board[nx][y] == 'B') break;
            if (board[nx][y] == 'p') {
                numCaptures++;
                break;
            }
            nx--;
        }
        // Go right, y keep increase.
        int ny = y;
        while (ny < BOARD_LEN) {
            if (board[x][ny] == 'B') break;
            if (board[x][ny] == 'p') {
                numCaptures++;
                break;
            }
            ny++;
        }
        // Go down, x keep increase.
        nx = x;
        while (nx < BOARD_LEN) {
            if (board[nx][y] == 'B') break;
            if (board[nx][y] == 'p') {
                numCaptures++;
                break;
            }
            nx++;
        }
        // Go left, y keep decrease.
        ny = y;
        while (ny >= 0) {
            if (board[x][ny] == 'B') break;
            if (board[x][ny] == 'p') {
                numCaptures++;
                break;
            }
            ny--;
        }
        return numCaptures;
    }
}
