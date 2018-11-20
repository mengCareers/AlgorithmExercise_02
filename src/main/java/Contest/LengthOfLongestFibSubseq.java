package Contest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1,2,3,4,5,6,7,8
 */
public class LengthOfLongestFibSubseq {

    public int lenLongestFibSubseq(int[] A) {

        int n = A.length;
        int[][] state = new int[n][n];
        int maxLen = 0;
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < A.length; i++)
            valToIndex.put(A[i], i);


        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] - A[j] < A[j] && valToIndex.containsKey(A[i] - A[j])) {
                    state[j][i] = state[valToIndex.get(A[i] - A[j])][j] + 1;
                } else {
                    state[j][i] = 2;
                }
                maxLen = Math.max(maxLen, state[j][i]);
            }
        }

        return maxLen == 2 ? 0 : maxLen;
    }

    public int lenLongestFibSubseq_ExhaustiveSearch(int[] A) {

        Set<Integer> valA = new HashSet<>();
        for (int val : A)
            valA.add(val);
        int a, b, lengthFib, longestLengthFib = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                a = A[i];
                b = A[j];
                lengthFib = 2;
                while (valA.contains(a + b)) {
                    b = a + b;
                    a = b - a;
                    lengthFib++;
                }
                longestLengthFib = Math.max(longestLengthFib, lengthFib);
            }
        }

        return longestLengthFib == 2 ? 0 : longestLengthFib;
    }

    // That's what I wanted, but that was Wrong
    public int lenLongestFibSubseqWrong(int[] A) {

        int n = A.length;
        int[] state = new int[n];
        int maxLen = 0;
        for (int i = 0; i < n; i++)
            state[i] = 1;

        for (int i = 1; i < n; i++) {
            int maxStateJ = 0;
            for (int j = 0; j < i; j++) {
                for (int x = j - 1; x >= 0; x--) {
                    if (A[j] + A[x] == A[i]) {
                        maxStateJ = Math.max(state[j] + 1, maxStateJ);
                        break;
                    }
                }
            }
            if (maxStateJ != 0) {
                state[i] = maxStateJ;
            }
            maxLen = Math.max(maxLen, state[i]);
        }

        return maxLen;
    }
}
