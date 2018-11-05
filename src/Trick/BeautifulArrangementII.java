package Trick;

/*
667. Beautiful Arrangement II
 */
public class BeautifulArrangementII {
    public int[] constructArray(int n, int k) {

        int[] result = new int[n];
        int s = 1, e = n, ri = 0, cnt = 0;
        boolean isSTurn = k % 2 != 0;

        while (cnt < k) {
            if (isSTurn) {
                result[ri++] = s;
                s++;
            } else {
                result[ri++] = e;
                e--;
            }
            isSTurn = !isSTurn;
            cnt++;
        }
        for (int val = s; val <= e; val++) {
            result[ri++] = val;
        }

        return result;
    }
}
