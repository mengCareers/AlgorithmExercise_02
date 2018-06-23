package Google;

/**
 * Min Swap Array
 * array row of length N, which contains integers from 0 up to N-1 in random order
 * free to choose any two and swap
 * output: minimum # of swaps needed so that we have i = row[i] (i.e., sort the array)
 */
public class MinSwapArray {

    public int miniSwapsArray(int[] row) {
        int res = 0, N = row.length;
        for (int i = 0; i < N; i++) {
            for (int j = row[i]; j != i; j = row[i]) {
                swap(row, i, j);
                res++;
            }
        }
        return res;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
