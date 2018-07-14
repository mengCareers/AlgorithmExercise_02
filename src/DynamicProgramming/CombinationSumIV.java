package DynamicProgramming;

/**
 state[i]: # of ways sum up to i
 start state: state[0] # of ways sum up to 0
 end state: state[target] # of ways sum up to target
 state transition: state[i] = sum(state[i - nums[j]])
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
