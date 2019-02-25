package Recursion;

import java.util.HashSet;
import java.util.Set;

public class RottenOranges {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int cols, rows, numOranges;

    public int orangesRotting(int[][] grid) {
        cols = grid[0].length;
        rows = grid.length;
        numOranges = 0;
        Set<Integer> rottenSet = new HashSet<>(); // Rotten oranges so far.
        Set<Integer> newlyRotten = new HashSet<>(); // Newly rotten oranges to rot around.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) numOranges++;
                else if (grid[i][j] == 2) {
                    numOranges++;
                    int curMark = mark(i, j);
                    rottenSet.add(curMark);
                    newlyRotten.add(curMark);
                }
            }
        }

        return rottenFrom(grid, rottenSet, newlyRotten);
    }

    private int rottenFrom(int[][] grid, Set<Integer> rottenSet, Set<Integer> newlyRotten) {
        if (numOranges == rottenSet.size()) return 0;
        Set<Integer> newRottenMarks = new HashSet<>();
        for (int rottenMark : newlyRotten) {
            int x = rottenMark / cols;
            int y = rottenMark % cols;
            for (int[] direction : directions) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                int nmark = nx * cols + ny;
                if (nx < rows && nx >= 0 && ny < cols && ny >= 0 && grid[nx][ny] == 1 && !rottenSet.contains(nmark)) {
                    rottenSet.add(nmark);
                    newRottenMarks.add(nmark);
                }
            }
        }
        if (newRottenMarks.size() == 0) return -1;
        int times = rottenFrom(grid, rottenSet, newRottenMarks);

        if (times == -1) return -1;
        else return 1 + times;
    }

    private int mark(int x, int y) {
        return x * cols + y;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 2}};
        RottenOranges inst = new RottenOranges();
        System.out.println(inst.orangesRotting(grid));
    }
}
/*
 * input: 0 empty 1 fresh 2 rotten
 *
 *
 * set of 2s,
 * for current 2s, make adj 1 to 2
 * set of 2s
 *
 * until reach num1
 *
 * (currentSituation, set of 2, visited )
 * */
