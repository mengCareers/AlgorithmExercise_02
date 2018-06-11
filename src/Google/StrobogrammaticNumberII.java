package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * a strobogrammatic number is a number that looks the same when rotated 189 degrees
 * output: all strobogrammatic numbers of length n
 *
 *
 * It's given that the length of the number is n,
 * if n = 1,  0   1   8
 * if n = 2,              11    88     69     96
 * if n = 3, 101 111 181
 *           808 818 888
 *           609 619 689
 *           906 916 986
 * if n = 4,             1111  1881   1691   1961   1001
 *                       8118  8888   8698   8968   8008
 *                       6119  6889   6699   6969   6009
 *                       9116  9886   9696   9966   9006
 *
 * if n = ..., ...
 * After careful observation, it's inductive that
 * if s is strobogrammatic
 *   1s1,
 *   8s8,
 *   6s9,
 *   9s6,
 * ..0s0
 *       are also strobogrammatic.
 * So we start from l = 0, r = n - 1,
 * enumerate all possible values that ensure [l,r] to be strobogrammatic if [l + 1, r - 1] are strobogrammatic,
 * and we move forward to the next step,
 * we end when l >= r.
 */
public class StrobogrammaticNumberII {
    List<String> result;

    public List<String> findStrobogrammatic(int n) {
        result = new ArrayList<>();
        findStrobogrammaticFrom(new char[n], 0, n - 1);
        return result;
    }

    private void findStrobogrammaticFrom(char[] curRes, int l, int r) {
        if (l > r) {
            result.add(new String(curRes));
            return;
        }
        if (l == r) {
            curRes[l] = '0';
            result.add(new String(curRes));
            curRes[l] = '1';
            result.add(new String(curRes));
            curRes[l] = '8';
            result.add(new String(curRes));
            return;
        }
        if (l != 0) {
            curRes[l] = '0';
            curRes[r] = '0';
            findStrobogrammaticFrom(curRes, l + 1, r - 1);
        }

        curRes[l] = '1';
        curRes[r] = '1';
        findStrobogrammaticFrom(curRes, l + 1, r - 1);

        curRes[l] = '8';
        curRes[r] = '8';
        findStrobogrammaticFrom(curRes, l + 1, r - 1);

        curRes[l] = '6';
        curRes[r] = '9';
        findStrobogrammaticFrom(curRes, l + 1, r - 1);

        curRes[l] = '9';
        curRes[r] = '6';
        findStrobogrammaticFrom(curRes, l + 1, r - 1);
    }
}
