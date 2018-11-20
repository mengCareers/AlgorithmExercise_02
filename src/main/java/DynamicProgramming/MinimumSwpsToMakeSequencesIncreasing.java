package DynamicProgramming;

/*
801. Minimum Swaps To Make Sequences Increasing
 */
public class MinimumSwpsToMakeSequencesIncreasing {
    public int minSwap_0(int[] A, int[] B) {

        if (A == null || B == null || A.length == 0 || B.length == 0) {
            throw new IllegalArgumentException();
        }

        int n = A.length;
        int[][] state = new int[n][2];
        state[0][0] = 0;
        state[0][1] = 1;

        for (int i = 1; i < n; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i] && A[i - 1] < B[i] && B[i - 1] < A[i]) {
                state[i][0] = Math.min(state[i - 1][0], state[i - 1][1]);
                state[i][1] = Math.min(state[i - 1][0], state[i - 1][1]) + 1;
            } else if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                state[i][0] = state[i - 1][0];
                state[i][1] = state[i - 1][1] + 1;
            } else if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                state[i][0] = state[i - 1][1];
                state[i][1] = state[i - 1][0] + 1;
            }
        }

        return Math.min(state[n - 1][0], state[n - 1][1]);
    }

    public int minSwap_1(int[] A, int[] B) {

        int swap = 1, no_swap = 0, n = A.length, tmp;

        for (int i = 1; i < n; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i] && A[i - 1] < B[i] && B[i - 1] < A[i]) {
                tmp = Math.min(no_swap, swap);
                no_swap = tmp;
                swap = tmp + 1;
            } else if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                no_swap = no_swap;
                swap = swap + 1;
            } else if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                tmp = no_swap;
                no_swap = swap;
                swap = tmp + 1;
            }
        }

        return Math.min(swap, no_swap);
    }
}
