package CompanyOriented.Google;

/**
 * N couples sit in 2N seats arranged in a row and want to hold hands
 * output: minimum # of swaps so that every couple is sitting side by side
 * 01 -> /2 = 0
 * 23 -> /2 = 1
 * 45 -> /2 = 2
 */
public class CouplesHoldingHands {
    public int minSwapsCouples(int[] row) {
        int result = 0, N = row.length;
        int[] partner = new int[N];
        int[] posInRow = new int[N];
        for (int i = 0; i < N; i++) {
            partner[i] = (i % 2 == 0) ? i + 1 : i - 1;
            posInRow[row[i]] = i;
        }
        for (int i = 0; i < N; i++) {
            for (int j = partner[posInRow[partner[row[i]]]]; j != i; j = partner[posInRow[partner[row[i]]]]) {
                swap(row, i, j);
                swap(posInRow, row[i], row[j]);
                result++;
            }
        }
        return result;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
