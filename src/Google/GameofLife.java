package Google;

/**
 * rule:
 * live cell neighbors live < 2 die
 * live cell neighbors 2 <= live <= 3 live
 * live cell neighbors live > 3 die
 * dead cell neighbors live = 3 live
 * input:
 * board[][]
 * output:
 * next state of board[][]
 * tp:
 * we can loop through board,
 * for live cells, we cnt neighbors live
 * for ddad cells,
 */
public class GameofLife {

    public static void main(String[] args) {
        GameofLife gm = new GameofLife();
        int[][] board = {{1}};
        gm.gameOfLife(board);
    }

    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

    public void gameOfLife(int[][] board) {
        int[][] oriBoard = new int[board.length][board[0].length];
        for (int i = 0; i < oriBoard.length; i++) {
            for (int j = 0; j < oriBoard[0].length; j++) {
                oriBoard[i][j] = board[i][j];
            }
        }
        int cntLive = 0;
        for (int i = 0; i < oriBoard.length; i++) {
            for (int j = 0; j < oriBoard[0].length; j++) {
                cntLive = cntLiveNeighborsFrom(i, j, oriBoard);
                if (oriBoard[i][j] == 1 && (cntLive < 2 || cntLive > 3)) {
                    board[i][j] = 0;
                } else if (oriBoard[i][j] == 0 && cntLive == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }

    private int cntLiveNeighborsFrom(int x, int y, int[][] board) {
        int nx = 0, ny = 0, cntLive = 0;
        for (int[] direction : directions) {
            nx = x + direction[0];
            ny = y + direction[1];
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] == 1) {
                cntLive++;
            }
        }
        return cntLive;
    }

}
