package DynamicProgramming;

/**
 * 198.House Robber
 */
public class HouseRobber {
    public int rob_0(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[][] state = new int[n][2]; // 0 - abort, 1 - rob
        state[0][0] = 0;
        state[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            state[i][0] = Math.max(state[i - 1][0], state[i - 1][1]);
            state[i][1] = state[i - 1][0] + nums[i];
        }

        return Math.max(state[n - 1][0], state[n - 1][1]);
    }

    public int rob_1(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] rob = new int[n];
        int[] abort = new int[n];
        rob[0] = nums[0];
        abort[0] = 0;
        for (int i = 1; i < n; i++) {
            abort[i] = Math.max(abort[i - 1], rob[i - 1]);
            rob[i] = abort[i - 1] + nums[i];
        }

        return Math.max(abort[n - 1], rob[n - 1]);
    }

    public int rob_2(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int rob = nums[0];
        int abort = 0;
        for (int i = 1; i < n; i++) {
            int preAbort = abort;
            abort = Math.max(abort, rob);
            rob = preAbort + nums[i];
        }

        return Math.max(abort, rob);
    }
}
