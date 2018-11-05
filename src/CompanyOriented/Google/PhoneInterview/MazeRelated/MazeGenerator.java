package CompanyOriented.Google.PhoneInterview.MazeRelated;

public class MazeGenerator {

    boolean[][] east;
    boolean[][] south;
    boolean[][] west;
    boolean[][] north;
    boolean[][] visited;
    int n;

    public MazeGenerator(int n) {
        this.n = n;
        StdDraw.setXscale(0, n + 2);
        StdDraw.setYscale(0, n + 2);
        init();
        mazeBuilder();
    }

    public void init() {
        // initialze all walls as present
        north = new boolean[n + 2][n + 2];
        east = new boolean[n + 2][n + 2];
        south = new boolean[n + 2][n + 2];
        west = new boolean[n + 2][n + 2];
        for (int x = 0; x < n + 2; x++) {
            for (int y = 0; y < n + 2; y++) {
                north[x][y] = true;
                east[x][y] = true;
                south[x][y] = true;
                west[x][y] = true;
            }
        }

        // initialize border cells as already visited
        visited = new boolean[n + 2][n + 2];
        for (int x = 0; x < n + 2; x++) {
            visited[x][0] = true;
            visited[x][n + 1] = true;
        }
        for (int y = 0; y < n + 2; y++) {
            visited[0][y] = true;
            visited[n + 1][y] = true;
        }

    }

    public void mazeBuilder() {
        mazeBuilder(1, 1);
    }

    private void mazeBuilder(int x, int y) {
        visited[x][y] = true;
        while (!visited[x + 1][y] || !visited[x][y + 1] || !visited[x - 1][y] || !visited[x][y - 1]) {
            while (true) {
                double r = StdRandom.uniform(4);

                if (r == 0 && !visited[x + 1][y]) {
                    east[x][y] = false;
                    west[x + 1][y] = false;
                    mazeBuilder(x + 1, y);
                    break;
                } else if (r == 1 && !visited[x][y + 1]) {
                    north[x][y] = false;
                    south[x][y + 1] = false;
                    mazeBuilder(x, y + 1);
                    break;
                } else if (r == 2 && !visited[x - 1][y]) {
                    west[x][y] = false;
                    east[x - 1][y] = false;
                    mazeBuilder(x - 1, y);
                    break;
                } else if (r == 3 && !visited[x][y - 1]) {
                    south[x][y] = false;
                    north[x][y - 1] = false;
                    mazeBuilder(x, y - 1);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        MazeGenerator mazeGenerator = new MazeGenerator(n);
        StdDraw.enableDoubleBuffering();
        mazeGenerator.draw();
    }

    // draw the maze
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(n / 2.0 + 0.5, n / 2.0 + 0.5, 0.075);
        StdDraw.filledCircle(1.5, 1.5, 0.075);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (south[x][y]) StdDraw.line(x, y, x + 1, y);
                if (north[x][y]) StdDraw.line(x, y + 1, x + 1, y + 1);
                if (west[x][y]) StdDraw.line(x, y, x, y + 1);
                if (east[x][y]) StdDraw.line(x + 1, y, x + 1, y + 1);
            }
        }
        StdDraw.show();
        StdDraw.pause(1000);
    }
}
