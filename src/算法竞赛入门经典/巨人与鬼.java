package 算法竞赛入门经典;

/**
 * n black points, n white points, pair one black point with one white point,
 * such that no intersection
 */
public class 巨人与鬼 {

    int[] ans; // ans[point1.id] = point2.id; ans[point2.id] = point1.id; if point1 and point2 are connected

    public void pairPoints(Node[] p) {
        ans = new int[p.length];
        pairPointsFrom(0, p.length - 1, p);
    }


    private void pairPointsFrom(int l, int r, Node[] p) {

        int posPoint = l;
        for (int i = l + 1; i <= r; i++) { // to find the leftdown most
            if (p[i].y < p[posPoint].y || p[i].y == p[posPoint].y && p[i].x < p[posPoint].x) {
                posPoint = i;
            }
        }

        // make p[l] the leftdown most
        Node t = p[l];
        p[l] = p[posPoint];
        p[posPoint] = t;

        // calculate angle to leftdown most
        int cnt = p[l].type;
        for (int i = l + 1; i <= r; i++) {
            p[i].angle = (int) Math.toDegrees(Math.atan2(p[i].y - p[l].y, p[i].x - p[l].x));
        }

        // sort them by angle to leftdown most
        for (int i = l + 1; i <= r; i++) {
            for (int j = i; j <= r; j++) {
                if (p[i].angle > p[j].angle) {
                    t = p[i];
                    p[i] = p[j];
                    p[j] = t;
                }
            }
        }

        // find the one with smallest angle that makes cnt of both points equal
        for (int i = l + 1; i <= r; i++) {
            cnt += p[i].type;
            if (cnt == 0) {
                ans[p[l].id] = p[i].id;
                ans[p[i].id] = p[l].id;
                pairPointsFrom(l + 1, i - 1, p);
                pairPointsFrom(i + 1, r, p);
                break;
            }
        }
    }

    class Node {
        int id;
        int x;
        int y;
        int type; // 1, -1
        int angle;

        Node(int id, int x, int y, int type) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}
