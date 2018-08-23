package Amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
find k nearest points to origin

min heap
 */
public class FinkKNearestPoint {
    public Point[] kNearestToOrigin(Point[] array, Point origin, int k) {

        Point[] result = new Point[k];

        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return (int) (getDistance(o1, origin) - getDistance(o2, origin));
            }
        });
        for (Point p : array) {
            pq.offer(p);
        }

        int i = 0;
        while (i < k) {
            result[i] = pq.poll();
            i++;
        }

        return result;
    }

    private double getDistance(Point a, Point b) {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
    }


}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}