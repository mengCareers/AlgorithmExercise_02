package Facebook;

/**
 * input: A[], B[] same length
 * we can swap A[i] and B[i]
 * output: minimum # of swaps make that A and B are both strictly increasing
 */
/*
Shortest Path: BFS, dp
n1, cost of making first i-1 cols increasing not swapping i-1th col
s1, coast of making first i-1 cols increasing swapping ith col
we want n2, cost of making first i cols increasing not swapping ith col
        s2
a1 = A[i-1], b1 = B[i-1]
a2 = A[i], b2 = B[i]
if (a1 < a2 && b1 < b2) both unswapped or both swapped
        n2 = min(n2, n1)
        s2 = min(s2, s1+1)
if (a1 < b2 and b1 < a2) one exactly swapped
        n2 = min(n2, s1)
        s2 = min(s2, n1 + 1)
 */

/**
 * 3 2
 * 1 5
 */
public class MinimumSwapsToMakeSeqIncreasing {
    public int minSwap(int[] A, int[] B) {
        int[][] state = new int[A.length][2];
        state[0][0] = 0;
        state[0][1] = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]
                    && B[i - 1] < A[i] && A[i - 1] < B[i]) {
                // doesn't matter the front swap or not
                state[i][0] = Math.min(state[i - 1][0], state[i - 1][1]);
                state[i][1] = Math.min(state[i - 1][0], state[i - 1][1]) + 1;
            } else if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                // following the front swap or not
                state[i][0] = state[i - 1][0];
                state[i][1] = state[i - 1][1] + 1;
            } else {
                // opposite to the front swap or not
                state[i][0] = state[i - 1][1];
                state[i][1] = state[i - 1][0] + 1;
            }
        }
        return Math.min(state[A.length - 1][0], state[A.length - 1][1]);
    }
}
