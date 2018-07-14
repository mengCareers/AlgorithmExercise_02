package Google;

public class OrientationOfThreeOrderedPoints {
    public static void main(String[] args) {
        OrientationOfThreeOrderedPoints inst = new OrientationOfThreeOrderedPoints();
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 4);
        Point p3 = new Point(1, 2);

        int o = inst.getOrientation(p1, p2, p3);

        if (o == 0)
            System.out.print("Linear");
        else if (o == 1)
            System.out.print("Clockwise");
        else
            System.out.print("CounterClockwise");

    }

    /**
     * @param p1
     * @param p2
     * @param p3
     * @return 0 if colinear, 1 if clockwise, 2 if counterclockwise
     */
    public int getOrientation(Point p1, Point p2, Point p3) {
        int val = (p2.y - p1.y) * (p3.x - p2.x) - (p3.y - p2.y) * (p2.x - p1.x);
        if (val == 0) {
            return 0;
        }
        return val < 0 ? 2 : 1;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
