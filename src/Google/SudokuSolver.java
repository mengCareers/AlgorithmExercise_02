package Google;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * rules :
 * Each of the digits 1-9 must occur exactly once in each row
 * Each of the digits 1-9 must occur exactly once in each col
 * Each of the digits 1-9 must occur exactly once in each of the 3 x 3 sub-boxes of the grid
 * <p>
 * Empty cells are marked with '.'
 * <p>
 * List<Cell> emptyCells contains of all the cells
 * curIdx as the index of current empty cell
 * Start State: curIdx = 0
 * End State: curIdx = emptyCells.size()
 * For each step, excluding invalid numbers from 1-9, try each rest one,
 * and check if it can contribute to a valid answer
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        List<Cell> emptyCells = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    emptyCells.add(new Cell(i, j));
                }
            }
        }
        solveSudokuFrom(board, emptyCells, 0);
    }

    private boolean solveSudokuFrom(char[][] board, List<Cell> emptyCells, int curIdx) {
        if (curIdx == emptyCells.size()) {
            return true;
        }
        Cell curCell = emptyCells.get(curIdx);
        Set<Integer> impossibleValues = getImpossibleValues(curCell.x, curCell.y, board);
        for (char i = '1'; i <= '9'; i++) {
            if (impossibleValues.contains(i - '0')) {
                continue;
            }
            board[curCell.x][curCell.y] = i;
            if (solveSudokuFrom(board, emptyCells, curIdx + 1)) {
                return true;
            }
            impossibleValues.add(i - '0');
            board[curCell.x][curCell.y] = '.';
        }
        return false;
    }

    private Set<Integer> getImpossibleValues(int x, int y, char[][] board) {
        Set<Integer> impossibleValues = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (board[i][y] == '.') {
                continue;
            }
            impossibleValues.add(board[i][y] - '0');
        }
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == '.') {
                continue;
            }
            impossibleValues.add(board[x][i] - '0');
        }
        int si = getBoundary(x);
        int sj = getBoundary(y);
        for (int i = si; i <= si + 2; i++) {
            for (int j = sj; j <= sj + 2; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                impossibleValues.add(board[i][j] - '0');
            }
        }
        return impossibleValues;
    }

    private int getBoundary(int val) {
        if (val >= 0 && val <= 2) {
            return 0;
        }
        if (val >= 3 && val <= 5) {
            return 3;
        }
        return 6;
    }

    class Cell {
        int x, y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
