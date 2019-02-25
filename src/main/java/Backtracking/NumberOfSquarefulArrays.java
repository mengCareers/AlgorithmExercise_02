package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* Count numbers ocuurrence.
For each number i, find all possible next number j that i + j is square.
* */
public class NumberOfSquarefulArrays {
    public static void main(String[] args) {
        int[] A = {1, 17, 8};
        NumberOfSquarefulArrays inst = new NumberOfSquarefulArrays();
        System.out.println(inst.numSquarefulPerms(A));
    }

    public int numSquarefulPerms(int[] A) {
        int numSquareful = 0;
        Arrays.sort(A);
        List<int[]> permutations = listPermutations(A);
        for (int[] permutation : permutations) {
            if (isSquarefulArray(permutation)) numSquareful++;
        }
        return numSquareful;
    }

    private List<int[]> listPermutations(int[] A) {
        List<int[]> result = new ArrayList<>();
        permute(result, new int[A.length], 0, A, new boolean[A.length]);
        return result;
    }

    private void permute(List<int[]> result, int[] cur, int curIndex, int[] A, boolean[] used) {
        // Base case.
        if (curIndex == A.length) {
            result.add(Arrays.copyOf(cur, A.length));
            return;
        }

        for (int i = 0; i < A.length; i++) {
            if (used[i] || i > 0 && used[i - 1] && A[i] == A[i - 1]) continue;
            cur[curIndex] = A[i];
            used[i] = true;
            permute(result, cur, curIndex + 1, A, used);
            used[i] = false;
        }
    }

    private boolean isSquarefulArray(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            if (!isSquareful(A[i] + A[i + 1])) return false;
        }
        return true;
    }

    private boolean isSquareful(double num) {
        double sqrtNum = Math.sqrt(num);
        return Math.floor(sqrtNum) == sqrtNum;
    }
}
/*
 * input: A
 * a squareful array IS every pair of adjacent elements, their sum is a perfect square.
 * output: # of unique permutations of A that are squareful
 * */