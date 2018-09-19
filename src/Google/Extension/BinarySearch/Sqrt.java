package Google.Extension.BinarySearch;

public class Sqrt {
    public int mySqrt(int x) {

        if (x <= 1) {
            return x;
        }

        long lo = 1, hi = x / 2;

        while (lo <= hi) {
            long mi = lo + ((hi - lo) >> 1);
            if (mi * mi <= x && (mi + 1) * (mi + 1) > x) {
                return (int) mi;
            }
            if ((mi + 1) * (mi + 1) <= x) {
                lo = mi + 1;
            }
            if (mi * mi > x) {
                hi = mi - 1;
            }
        }

        throw null;
    }
}
