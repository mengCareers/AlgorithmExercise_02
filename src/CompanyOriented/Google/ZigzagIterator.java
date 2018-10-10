package CompanyOriented.Google;

import java.util.List;

/**
 * input : v1, v2
 * output: iterator,
 * <p>
 * set 2 pointers, p1 for v1, p2 for v2
 * read one, move forward until boundary
 * boolean flag to show toUse, if false, use v1, else use v2
 */
public class ZigzagIterator {
    int p1, p2; // to print
    List<Integer> v1;
    List<Integer> v2;
    boolean useV2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        useV2 = false;
        p1 = 0;
        p2 = 0;
    }

    public int next() {
        if (!useV2 && p1 < v1.size()) {
            useV2 = true;
            return v1.get(p1++);
        }
        if (useV2 && p2 < v2.size()) {
            useV2 = false;
            return v2.get(p2++);
        }
        return p1 < v1.size() ? v1.get(p1++) : v2.get(p2++);
    }

    public boolean hasNext() {
        return p1 < v1.size() || p2 < v2.size();
    }
}
