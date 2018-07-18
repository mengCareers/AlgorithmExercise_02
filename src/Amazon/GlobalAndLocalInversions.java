package Amazon;

/**
 * output: true if and only if the # of global inversions == # of local inversions
 */
/*
all local belong to global
aim non-local == 0
              if A[i] > A[j] with i + 1 < j
                             with i + 2 <= j
              if max(A[i]) > A[i + 2]
 */
public class GlobalAndLocalInversions {
    public boolean isIdealPermutation(int[] A) {
        int maxAiValue = Integer.MIN_VALUE;
        for (int i = 0; i + 2 < A.length; i++) {
            maxAiValue = Math.max(maxAiValue, A[i]);
            if (maxAiValue > A[i + 2]) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdealPermutationSLOW(int[] A) {
        int cntGlobal = 0, cntLocal = 0;
        for (int i = 0; i < A.length - 1; i++) {
            int curNum = A[i];
            for (int j = i + 1; j < A.length; j++) {
                if (curNum > A[j]) {
                    cntGlobal++;
                    if (j == i + 1) {
                        cntLocal++;
                    }
                }
            }
        }
        return cntGlobal == cntLocal;
    }
}
