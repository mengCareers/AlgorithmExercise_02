package DynamicProgramming;

/**
 * 377. Combination Sum IV
 */
public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        int[] state = new int[target + 1];
        state[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0)
                    state[i] += state[i - nums[j]];
            }
        }
        return state[target];
    }
}
