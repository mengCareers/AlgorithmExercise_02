package Contest;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 6, 4, 8
 * 8 9 16
 */

public class NthMagicalNumber {

    private static double M = 1e9 + 7;

    public int nthMagicalNumber(int N, int A, int B) {

        long lo = 0, hi = (long) 1e9;
        int lcm = A / gcd(A, B) * B;
        while (lo < hi) {
            long mi = lo + (hi - lo) / 2;
            if (mi / A + mi / B - mi / lcm < N) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }

        return (int) (lo % M);
    }

    private int gcd(int x, int y) {
        if (x == 0) {
            return y;
        }
        return gcd(y % x, x);
    }

    public static void main(String[] args) {
        NthMagicalNumber inst = new NthMagicalNumber();
        inst.nthMagicalNumber(5, 2, 4);
    }


    public int nthMagicalNumberLE(int N, int A, int B) {
        int result = Integer.MIN_VALUE;
        int an = 1, bn = 1;
        int ni = 0;
        while (ni++ != N) {
            if (an * A % M < bn * B % M) {
                result = Math.max(result, (int) (an * A % M));
                an++;

            } else if (an * A % M == bn * B % M) {
                result = Math.max(result, (int) (an * A % M));
                bn++;
                an++;
            } else {
                result = Math.max(result, (int) (bn * B % M));
                bn++;
            }
        }

        return result;
    }

}
