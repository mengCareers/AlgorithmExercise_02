package Google;

import java.util.Arrays;

/**
 * true if can be splitted into two arrays with the same average
 * totalSum / n = ASum / k + BSum / (n - k)
 * ASum = totalSum * k / n, which is an integer, i.e., totalSum * k % n = 0;
 * that is, from an arary find k sum = ASum, i.e., totalSum * k / n
 * It is actually Combination Sum problem
 * State: dp[i] as combination sum to i
 * Final State: dp[target]
 * <p>
 * 2, 3 target 7
 * for 2, it can append 2 or 3 to result
 */
public class SplitTheArrayWithTheSameAverage {
    public boolean splitArraySameAverage(int[] A) {
        Arrays.sort(A);
        int totalSum = 0;
        for (int a : A) {
            totalSum += a;
        }
        for (int k = 1; k <= A.length / 2; k++) {
            if (totalSum * k % A.length == 0) {
                if (combinationSumFrom(A, totalSum * k / A.length, 0, k)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean combinationSumFrom(int[] A, int target, int curIdx, int sz) {
        if (curIdx < A.length && target < sz * A[curIdx]) {
            return false;
        }
        if (sz == 0) {
            return target == 0;
        }
        for (int i = curIdx; i <= A.length - sz; i++) {
            if (target - A[i] >= 0 && combinationSumFrom(A, target - A[i], i + 1, sz - 1)) {
                return true;
            }
        }
        return false;
    }
}

