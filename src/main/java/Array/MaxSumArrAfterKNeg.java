package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxSumArrAfterKNeg {
    public static void main(String[] args) {
        MaxSumArrAfterKNeg inst = new MaxSumArrAfterKNeg();
        int[] A = {4,2,3};
        int k = 1;
        System.out.println(inst.largestSumAfterKNegations(A, k));

    }

    public int largestSumAfterKNegations(int[] A, int K) {
        List<Integer> list = new ArrayList<>();
        for (int num : A) list.add(num);
        Collections.sort(list, (a, b) -> (Math.abs(b) - Math.abs(a)));
        int a = 0;
        for (int num : list) {
            A[a++] = num;
        }
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                if (K > 0) {
                    K--;
                    A[i] = -A[i];
                }
            }
            sum += A[i];
        }
        if (K == 0) return sum;
        K = K % 2;
        int j = A.length - 1;
        while (K-- > 0) {
            sum -= 2 * A[j--];
        }
        return sum;
    }
}
