package Array;

import java.util.HashSet;
import java.util.Set;

public class SubarrayWithKDiffInteger {
    public static void main(String[] args) {
        SubarrayWithKDiffInteger inst = new SubarrayWithKDiffInteger();
        int[] A = {1, 2, 1, 2, 3};
        int K = 2;
        System.out.println(inst.subarraysWithKDistinct(A, K));
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        return subarrayWithAtMostKDistinct(A, K) - subarrayWithAtMostKDistinct(A, K - 1);
    }

    public int subarrayWithAtMostKDistinct(int[] A, int K) {
        int numValid = 0, i = 0, j = 0; // Sliding window [i, j].
        while (j < A.length) {
            while (i <= j && countDistinct(A, i, j) > K) {
                i++;
            }
            if (countDistinct(A, i, j) <= K) numValid += j - i + 1;
            j++;
        }
        System.out.println(numValid);
        return numValid;
    }

    /**
     * True if at most K distinct chars in A[i, j].
     */
    private int countDistinct(int[] A, int i, int j) {
        Set<Integer> set = new HashSet<>();
        for (int k = i; k <= j; k++) {
            set.add(A[k]);
        }
        return set.size();
    }
}
