package DFS;

/*
start point: (sr, sc)
    for current point, check if adjacent equal to image[sr][sc], if it is, flip to new Color
end point: out of boundary OR no adjacent equal to image[sr][sc]

need no visited[], for we change its color after we visit it
 */
public class FloodFill {

    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        if (image[sr][sc] == newColor) {
            return image;
        }

        floodFillFrom(sr, sc, image, image[sr][sc], newColor);
        return image;
    }

    private void floodFillFrom(int curX, int curY, int[][] image, int srcColor, int newColor) {

        if (curX < 0 || curY < 0 || curX >= image.length || curY >= image[0].length || image[curX][curY] != srcColor) {
            return;
        }

        image[curX][curY] = newColor;
        for (int[] direction : directions) {
            floodFillFrom(curX + direction[0], curY + direction[1], image, srcColor, newColor);
        }
    }

}
