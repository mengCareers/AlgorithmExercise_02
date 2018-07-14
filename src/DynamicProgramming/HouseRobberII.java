package DynamicProgramming;

/**
 It is fluffy for nums is a circle.
 Thus, we try making it linear, which is the same as HouseRobber adding boundaries.
 To break the circle,
 if we do not rob i, broken at i, rob(nums, 1, nums.length - 1)
 if we do rob i, broken at i - 1, rob(nums, 0, num.length - 2)
 and we choose the bigger one of them.
 For simplicity, we set i as 0, i - 1 as nums.length - 1 since it is cyclic.
 */
public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 1, nums.length - 1), rob(nums, 0, nums.length - 2));
    }

    public int rob(int[] nums, int s, int e) {
        if (e + 1 - s <= 0) {
            return s;
        }
        if (e + 1 - s == 1) {
            return nums[s];
        }
        int prepre = nums[s];
        int pre = Math.max(nums[s], nums[s + 1]);
        int cur = pre;
        for (int i = s + 2; i <= e; i++) {
            cur = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = cur;
        }
        return cur;
    }
}
