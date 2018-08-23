package DynamicProgramming;

public class GlobalAndLocalInversions {

    public boolean isIdealPermutation(int[] A) {

        for (int i = 0; i < A.length; i++) {
            if (Math.abs(i - A[i]) > 1)
                return false;
        }

        return true;
    }

    public boolean isIdealPermutation_BF(int[] A) {

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[i]) {
                    if (j != i + 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
