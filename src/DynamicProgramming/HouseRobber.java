package DynamicProgramming;

/**
 * state: state[i], maximum amount of money able to rob nums[0, i]
 * end state: state[len-1]
 * aim state: state[len-1]
 * state transition: state[i] = max(state[i - 2] + nums[i], state[i - 1])
 * state[0] = nums[0];
 * state[1] = max(nums[0], nums[1]);
 */
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] state = new int[nums.length];
        state[0] = nums[0];
        state[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i <= nums.length - 1; i++) {
            state[i] = Math.max(state[i - 2] + nums[i], state[i - 1]);
        }
        return state[nums.length - 1];
    }

    public int robOneDimension(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int prepre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        int cur = pre;
        for (int i = 2; i <= nums.length - 1; i++) {
            cur = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = cur;
        }
        return cur;
    }
}
