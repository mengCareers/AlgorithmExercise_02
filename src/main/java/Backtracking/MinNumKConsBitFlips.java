package Backtracking;

public class MinNumKConsBitFlips {
    public static void main(String[] args) {
        MinNumKConsBitFlips inst = new MinNumKConsBitFlips();
        int[] A = {0, 1, 0};
        System.out.println(inst.minKBitFlips(A, 1));
    }

    public int minKBitFlips(int[] A, int K) {
        return minKBitFlipsAfter(getFirstNonOne(A), A, K);
    }

    private int minKBitFlipsAfter(int start, int[] A, int K) {
        if (start == A.length) return 0; // Flip all 0s.
        if (start + K > A.length) return -1; // Not enough elements to flip.

        // Flip [start, start + k - 1].
        for (int i = start; i < start + K; i++) {
            A[i] = 1 - A[i];
        }

        int firstNonOne = getFirstNonOne(A);
        int rest = minKBitFlipsAfter(firstNonOne, A, K);
        if (rest == -1) return -1; // Cannot flip.
        else return 1 + rest;
    }

    private int getFirstNonOne(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 1) return i;
        }
        return A.length;
    }
}
