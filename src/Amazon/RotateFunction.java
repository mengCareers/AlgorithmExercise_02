package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * rather than permutation, it is rotation
 * rather than try all possible rotation, we select one with bigger numbers the latter
 */
public class RotateFunction {
    public static void main(String[] args) {
        RotateFunction inst = new RotateFunction();
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        inst.maxRotateFunction(A);
    }

    public int maxRotateFunction(int[] A) {

        if (A == null || A.length == 0) {
            return 0;
        }

        int n = A.length;
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        int[] f = new int[n];
        f[0] = evaluate(A);

        long maxF = f[0];
        for (int i = 1; i < A.length; i++) {
            f[i] = f[i - 1] - sum + A[i - 1] * n;
            if (f[i] > maxF) {
                maxF = f[i];
            }
        }

        return (int) maxF;
    }

    private int evaluate(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result += i * A[i];
        }
        return result;
    }

    public int maxRotateFunctionSLOW(int[] A) {
        List<List<Integer>> Bs = new LinkedList<>();
        long maxB = rotateAndGetMaxB(A, Bs);
        long maxF = Long.MIN_VALUE;
        for (List<Integer> B : Bs) {
            if (B.get(B.size() - 1) == maxB)
                maxF = Math.max(evaluate(B), maxF);
        }
        return maxF == Long.MIN_VALUE ? 0 : (int) maxF;
    }

    private int evaluate(List<Integer> B) {
        int result = 0;
        for (int i = 0; i < B.size(); i++) {
            result += i * B.get(i);
        }
        return result;
    }

    private long rotateAndGetMaxB(int[] A, List<List<Integer>> result) {

        int[] As = new int[A.length + A.length];
        int ai = 0, si = 0;
        long maxB = Long.MIN_VALUE;
        List<Integer> curRes = new LinkedList<>();

        for (int a : A) {
            As[ai++] = a;
            if (a > maxB) {
                maxB = a;
            }
            curRes.add(a);
            result.add(new LinkedList<>(curRes));
        }

        for (int a : A) {
            As[ai++] = a;
        }

        while (si < A.length - 1) {
            curRes.remove(0);
            curRes.add(As[si + A.length]);
            result.add(new LinkedList<>(curRes));
            si++;
        }

        return maxB;
    }
}
