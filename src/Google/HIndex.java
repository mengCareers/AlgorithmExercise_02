package Google;

import java.util.Arrays;

/**
 * 0 1 3 5 6
 * output : h, where
 * h citations with value >= h at least
 * n - h citations with value <= h
 * Search Space : h [1, n]
 * # BINARY SEARCH NOT PROPER FOR DUPLICATE NUMBERS
 */
public class HIndex {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        for (int h = n; h >= 1; h--) {
            int cntAtLeast = getAtLeast(h, citations);
            int cntNoMoreThan = getNoMoreThan(h, citations);
            if (cntAtLeast >= h && cntNoMoreThan >= n - h) {
                return h;
            }
        }
        return 0;
    }

    private static int getNoMoreThan(int h, int[] citations) {
        int cnt = 0;
        for (int citation : citations) {
            if (citation <= h)
                cnt++;
        }
        return cnt;
    }

    private static int getAtLeast(int h, int[] citations) {
        int cnt = 0;
        for (int citation : citations) {
            if (citation >= h)
                cnt++;
        }
        return cnt;
    }
}
