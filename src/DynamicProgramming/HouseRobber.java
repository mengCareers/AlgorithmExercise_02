package DynamicProgramming;

/**
 * The problem is to determine the maximum amount of money I can rob
 * State: dp[i], the maximum amount of money I can rob if I rob from house 0 to house i
 * Aim State: dp[nums.length - 1]
 * State Transfer: standing at house[i], we can rob it or not
 * if we rob it, dp[i] = dp[i-2] + house[i]
 * if we don't rob it, dp[i] = dp[i-1]
 * we always choose the bigger one of them
 * dp[i] = Math.max(dp[i-2] + house[i], dp[i-1]); (
 * e.g. 0 1 2
 * dp[0] = nums[0]
 * dp[1] = Math.max(nums[1], dp[0]);
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public int robOneDimension(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int prepre = nums[0];
        int pre = Math.max(nums[1], prepre);
        int cur = pre;
        for (int i = 2; i < nums.length; i++) {
            cur = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = cur;
        }
        return cur;
    }
}
