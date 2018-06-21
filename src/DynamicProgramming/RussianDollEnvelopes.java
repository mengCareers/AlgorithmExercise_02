package DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

/** 354. Russian Doll Envelopes
 * The problem is to get the maximum # of envelopes I can Russian doll,
 * Russian doll is formed when w1 < w2 and h1 < h2 from envelope 0 to n - 1

 It seems difficult to induce the State Transformation Equation.
 Let's think about it in the way of Exhaustive Searching:

 Firstly, we sort the envelopes by width increasingly,
 if two envelopes share the same width,
 we sort their heights decreasingly for further comparison.
 It's inductive that the Longest Increasing Subsequence among heights will be the answer.

 As for the Longest Increasing Subsequence, we will apply the bottom-up DP.

 State : dpi to represent the LIS from nums[0] to nums[i] ending at nums[i]
 Aim State : max(dp[i]) for 0 <= i < nums.length
 State Transfer :
 for nums[i] (0 <= i < nums.length)
 if nums[j] < nums[i] (0<= j < i), dp[i] = max(dp[j]) + 1;
 if not, dp[i] = 1;
 and we will keep track of the max(dp[i]) on the fly
 */
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
