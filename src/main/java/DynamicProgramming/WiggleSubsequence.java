package DynamicProgramming;

/**

 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {

        if (nums == null || nums.length == 0)
            return 0;

        int n = nums.length, maxLength = 0;
        if (n < 2) {
            return n;
        }

        int[][] state = new int[n][2];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] < nums[j]) {
                    state[i][0] = Math.max(state[i][0], state[j][1] + 1);
                    maxLength = Math.max(maxLength, state[i][0]);
                } else if (nums[j] < nums[i]) {
                    state[i][1] = Math.max(state[i][1], state[j][0] + 1);
                    maxLength = Math.max(maxLength, state[i][1]);
                }
            }
        }

        return maxLength + 1;
    }
}
