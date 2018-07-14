package Facebook;

/**
 * input: grid of 0 or 1
 * output: # of corner rectangles
 * -- 4 distinct 1s
 */
/*
if 1, check edge 2, 3, 4.. if possible
(x, y)  (x, y + edge)
(s + edge, y) (x + edge, y + edge)
state[x][y][edge] left corner with edge[e] true if is corner rectangel
state[x][y][edge] is true if  state[x - 1][y - 1][edge]
     (x, y)
     (x, y + edge)
     (s + edge, y)
     (x + edge, y + edge) are 1
 */

/**
 * MENTOR: fix two rows first,
 * then check column by column to find '1' on both rows
 * say you find n pairs,
 */
public class NumberOfCornerRectangles {
    public int countCornerRectangles(int[][] grid) {
        int cntCornerRect = 0;
        int rows = grid.length, cols = grid[0].length;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < rows; j++) {
                // fix row i nad row j
                int pairs = 0;
                for (int col = 0; col < cols; col++) {
                    if (grid[i][col] == 1 && grid[j][col] == 1) {
                        pairs++;
                    }
                }
                if (pairs > 0) {
                    cntCornerRect += pairs * (pairs - 1) / 2;
                }
            }
        }
        return cntCornerRect;
    }
    /* Incorrect but good
    public int countCornerRectangles(int[][] grid) {
        int cntCornerRect = 0;
        int rows = grid.length, cols = grid[0].length;
        boolean[][][] state = new boolean[rows][cols][Math.min(rows, cols)];
        state[rows - 1][cols - 1][0] = true;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                for (int edge = 0; i + edge < rows && j + edge < cols; edge++) {
                    if (state[i + 1][j + 1][edge]) {
                        boolean valid = true;
                        for (int x = i; x < i + edge; x++) {
                            if (grid[x][j] != 1) {
                                valid = false;
                                break;
                            }
                        }
                        if (valid) {
                            for (int y = j; y < j + edge; y++) {
                                if (grid[i][y] != 1) {
                                    valid = false;
                                    break;
                                }
                            }
                        }
                        if (valid) {
                            state[i][j][edge] = true;
                            cntCornerRect++;
                        }
                    }
                }
            }
        }
        return cntCornerRect;
    }
    */
}
