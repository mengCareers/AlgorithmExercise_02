/**
 * e.g. 1 2 3, target = 4
 * 1 1 1 1
 * 1 1 2
 * 1 2 1
 * 1 3
 * 2 1 1
 * 2 2
 * 3 1
 * TP : we can list all combinations and check the sum of each one
 * how can we do it in DP?
 The problem is to get the number of possible combinations that add up to target among nums.
 State : dp[t], the number of possible combinations that add up to t among nums.
 Aim State : dp[target], 0-based
 State Transfer :
 To reach t, the last selected num can be any one of nums.
 dp[t] = sum(dp[t - nums[i]]); (0 < t <= target, 0 <= i < nums.length)
 Implementation : Bottom-up DP
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int t = 1; t <= target; t++) {
            for (int i = 0; i < nums.length; i++) {
                if (t - nums[i] >= 0) {
                    dp[t] += dp[t - nums[i]];
                }
            }
        }
        return dp[target];
    }
}
