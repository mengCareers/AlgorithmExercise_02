package CompanyOriented.Google.PhoneInterview;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
matrix parallel to axis
 */
public class RandomPointsInRectangle {

    static class Rectangle {
        int topLeftX;
        int topLeftY;
        int width;
        int height;
        int area;

        public Rectangle(int topLeftX, int topLeftY, int width, int height) {
            this.topLeftX = topLeftX;
            this.topLeftY = topLeftY;
            this.width = width;
            this.height = height;
            area = width * height;
        }
    }

    /*
    1,2,3,4
    5,6,7,8
    ares
    1 2 3 4 5
    1 3 6 10 15
         7
     */
    private static final Random random = new Random();

    public int[] pickPointsRandomly(List<Rectangle> rectangleList) {
        int n = rectangleList.size();
        int[] presumArea = new int[n];
        for (int i = 0; i < n; i++) {
            presumArea[i] += rectangleList.get(i).area;
        }

        int randomArea = random.nextInt(presumArea[n - 1]) + 1;
        int insertionIndex = Arrays.binarySearch(presumArea, randomArea);
        return pickPointsRandomly(rectangleList.get(insertionIndex));
    }

    public int[] pickPointsRandomly(Rectangle rectangle) {
        int randomX = random.nextInt(rectangle.height + 1) + rectangle.topLeftX;
        int randomY = random.nextInt(rectangle.width + 1) + rectangle.topLeftY;
        return new int[]{randomX, randomY};
    }
}
