package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. Russian Doll Envelopes
 **/
public class RussianDollEnvelopes {
    private int getLongestIncreasingSequence(int[] nums) {
        int[] dp = new int[nums.length];
        int maxVal = 0;
        for (int i = 0; i < nums.length; i++) {
            int maxDPj = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    maxDPj = Math.max(maxDPj, dp[j]);
                }
            }
            dp[i] = maxDPj + 1;
            maxVal = Math.max(maxVal, dp[i]);
        }
        return maxVal;
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                }
                return o1[0] - o2[0];
            }
        });

        int[] nums = new int[n];
        int ni = 0;
        for (int[] envelope : envelopes) {
            nums[ni++] = envelope[1];
        }
        return getLongestIncreasingSequence(nums);
    }
}
