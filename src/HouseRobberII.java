/**
 In 198. House Robber, we have already determined the maximum amount of money I can rob from house 0 to house nums.length - 1. We modify the original method by adding parameters s(tart), and e(nd) such that we can determine the maximum amount of money I can rob from house s to house e (both inclusive).
 It's tricky that houses are arranged in circle, so we try breaking the circle first. It is inductive that the circle is broken at house i if we don't rob it. If we rob house i, we are unable to rob house i - 1, then the circle will be broken at house i - 1.
 For simplicity, we set i as 0, and i - 1 as nums.length - 1 since it is a circle.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    public int rob(int[] nums, int s, int e) {// s = 0, e = nums.length-1 in I
        if (e - s < 0) {
            return 0;
        }
        if (e - s == 0) {
            return nums[s];
        }
        int prepre = nums[s];
        int pre = Math.max(nums[s + 1], prepre);
        int cur = pre;
        for (int i = s + 2; i <= e; i++) {
            cur = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = cur;
        }
        return cur;
    }
}
