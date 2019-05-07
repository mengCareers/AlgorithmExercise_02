package Array;

public class MaxConOnesIII {
    public static void main(String[] args) {
        MaxConOnesIII inst = new MaxConOnesIII();
        int[] A = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        System.out.println(inst.longestOnes(A, 3));
    }

    public int longestOnes(int[] A, int K) {
        int lenA = A.length, maxLen = 0, right = 0;
        for (int left = 0; left < lenA; left++) {
            for (; right < lenA; right++) {
                int numZero = countZero(left, right, A);
                if (numZero == K) {
                    // Update maxLen.
                    maxLen = Math.max(maxLen, right - left + 1);
                } else if (numZero > K) {
                    break;
                }
            }
        }
        return maxLen;
    }

    private int countZero(int start, int end, int[] A) {
        int numZero = 0;
        for (int i = start; i <= end; i++) {
            if (A[i] == 0) numZero++;
        }
        return numZero;
    }
}
