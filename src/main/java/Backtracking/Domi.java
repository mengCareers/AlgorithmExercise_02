package Backtracking;

public class Domi {
    private int n;

    public int minDominoRotations(int[] A, int[] B) {
        n = A.length;
        return func(0, A, B, new boolean[n]);
    }

    private int func(int start, int[] A, int[] B,  boolean[] swapped) {
        if (start == n) {
            if (same(A) || same(B)) return 0;
            else return -1;
        }

        // for each diff, we can swap
        int minRotate = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (A[i] != B[i] && !swapped[i]) {
                // swap
                swap(A, B, i);
                swapped[i] = true;
                int subRes = func(i + 1, A, B, swapped);
                if (subRes != -1) minRotate = Math.min(minRotate, subRes + 1);
                swap(A, B, i);
                swapped[i] = false;
            }
        }
        return minRotate == Integer.MAX_VALUE ? -1 : Integer.MAX_VALUE;
    }

    private void swap(int[] A, int[] B, int i) {
        int temp = A[i];
        A[i] = B[i];
        B[i] = temp;
    }

    private boolean same(int[] arr) {
        int x = arr[0];
        for (int num : arr) {
            if (x != num) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Domi inst = new Domi();
        int[] A = {2, 1, 2, 4, 2, 2};
        int[] B = {5, 2, 6, 2, 3, 2};
        System.out.println(inst.minDominoRotations(A, B));
    }
}
