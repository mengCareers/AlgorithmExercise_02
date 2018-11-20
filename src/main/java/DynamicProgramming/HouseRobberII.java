package DynamicProgramming;

import java.util.Arrays;

/**
 * It is fluffy for nums is a circle.
 * Thus, we try making it linear, which is the same as HouseRobber adding boundaries.
 * To break the circle,
 * if we do not rob i, broken at i, rob(nums, 1, nums.length - 1)
 * if we do rob i, broken at i - 1, rob(nums, 0, num.length - 2)
 * and we choose the bigger one of them.
 * For simplicity, we set i as 0, i - 1 as nums.length - 1 since it is cyclic.
 */
public class HouseRobberII {
    int[][] memo;

    public int rob(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        memo = new int[nums.length][nums.length];
        for (int[] tmp : memo)
            Arrays.fill(tmp, -1);

        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }


    public int rob(int[] nums, int start, int end) {

        if (nums == null || end < start) {
            return 0;
        }

        if (memo[start][end] != -1) {
            return memo[start][end];
        }

        int n = nums.length;
        int rob = nums[start];
        int abort = 0;
        for (int i = start + 1; i <= end; i++) {
            int preAbort = abort;
            abort = Math.max(abort, rob);
            rob = preAbort + nums[i];
        }

        memo[start][end] = Math.max(abort, rob);
        return memo[start][end];
    }
}
