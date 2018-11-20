package DynamicProgramming;

public class BurstBalloons {
    public int maxCoins(int[] nums) {

        int n = nums.length + 2;
        int[] nums2 = new int[n];
        nums2[0] = nums2[n - 1] = 1;
        for (int i = 0; i < nums.length; i++) {
            nums2[i + 1] = nums[i];
        }
        int[][] state = new int[n][n];

        for (int dist = 1; dist < n - 1; dist++) {
            for (int i = 1; i + dist < n; i++) {
                int j = i + dist - 1;
                for (int k = i; k <= j; k++) {
                    state[i][j] = Math.max(state[i][j], state[i][k - 1] + nums2[i - 1] * nums2[k] * nums2[j + 1] + state[k + 1][j]);
                }
            }
        }
        return state[1][nums.length];
    }
}
