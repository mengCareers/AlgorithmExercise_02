package Google;

/**
 * Two overlap if they satisfy general case or special case
 */
public class CheckIfTwoLineSegmentsIntersect {
    public static void main(String[] args) {
        CheckIfTwoLineSegmentsIntersect inst = new CheckIfTwoLineSegmentsIntersect();
        Point p1 = new Point(10, 0);
        Point p2 = new Point(0, 10);
        Point p3 = new Point(0, 0);
        Point p4 = new Point(10, 10);
        boolean answer = inst.doIntersect(p1, p2, p3, p4);
        System.out.println(answer);
    }

    public boolean doIntersect(Point p1, Point q1, Point p2, Point q2) {
        int o1 = getOrientation(p1, q1, p2);
        int o2 = getOrientation(p1, q1, q2);
        int o3 = getOrientation(p2, q2, p1);
        int o4 = getOrientation(p2, q2, q1);

        // if general case
        if ((o1 * o2 == 2) && (o3 * o4 == 2)) {
            return true;
        }

        // if special case
        if (o1 == 0 && isOnSegment(p1, p2, q1)) return true;
        if (o2 == 0 && isOnSegment(p1, q2, q1)) return true;
        if (o3 == 0 && isOnSegment(p2, p1, q2)) return true;
        if (o4 == 0 && isOnSegment(p2, q1, q2)) return true;

        return false;
    }

    /**
     * True if q is on segment pr
     *
     * @param p
     * @param q
     * @param r
     * @return
     */
    private boolean isOnSegment(Point p, Point q, Point r) {
        if (q.x <= Math.max(p.x, r.x) && q.y <= (Math.max(p.y, r.y)) &&
                q.x >= Math.min(p.x, r.x) && q.y >= (Math.min(p.y, r.y))) {
            return true;
        }
        return false;
    }

    /**
     * @param p1
     * @param p2
     * @param p3
     * @return 0 if colinear, 1 if clockwise, 2 if counterclockwise
     */
    private int getOrientation(Point p1, Point p2, Point p3) {
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
