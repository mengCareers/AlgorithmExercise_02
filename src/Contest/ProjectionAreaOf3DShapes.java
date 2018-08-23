package Contest;

public class ProjectionAreaOf3DShapes {

    public static void main(String[] args) {
        ProjectionAreaOf3DShapes inst = new ProjectionAreaOf3DShapes();
        int[][] grid = {{1, 2}, {3, 4}};
        inst.projectionArea(grid);
    }

    public int projectionArea(int[][] grid) {

        int rLen = grid.length, cLen = grid[0].length;

        int bottomS = 0;
        for (int[] row : grid) {
            for (int i : row) {
                if (i > 0) {
                    bottomS++;
                }
            }
        }

        int frontS = 0;
        for (int[] row : grid) {
            for (int i = 0; i < rLen; i++) {
                if (row[i] > 0) {
                    int maxH = row[i];
                    for (int j = i; j < rLen; j++) {
                        if (row[j] > maxH) {
                            maxH = row[j];
                        }
                    }
                    frontS += maxH;
                    break;
                }
            }
        }

        int sideS = 0;
        for (int j = 0; j < cLen; j++) {
            for (int i = 0; i < rLen; i++) {
                if (grid[i][j] > 0) {
                    int maxH = grid[i][j];
                    for (int k = i; k < rLen; k++) {
                        if (grid[k][j] > maxH) {
                            maxH = grid[k][j];
                        }
                    }
                    sideS += maxH;
                    break;
                }
            }
        }

        return bottomS + frontS + sideS;
    }
}
