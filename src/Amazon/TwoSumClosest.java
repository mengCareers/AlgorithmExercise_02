package Amazon;

import java.util.Arrays;

/**
 * input: nums, target
 * output: minimum difference between the sum and the target
 */
/*
intuitive to try all possible two-sum and find the one nearest to target
 */
public class TwoSumClosest {
    public int twoSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int s = 0, e = nums.length - 1;
        int dist = Integer.MAX_VALUE;
        while (s < e) {
            if (nums[s] + nums[e] == target) {
                return 0;
            } else if (nums[s] + nums[e] > target) {
                e--;
            } else {
                s++;
            }
            dist = Math.min(dist, Math.abs(nums[s] + nums[e] - target));
        }

        return dist;
    }
}
