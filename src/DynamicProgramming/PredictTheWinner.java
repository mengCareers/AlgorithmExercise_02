package DynamicProgramming;

import java.util.Arrays;

/*
*     regard candidate range [i, j) as state,
    state[i][j] as true if 1st one can win if get from nums[i, j)
    goal: return state[0][n - 1]
    state transition:
        if 1st picks nums[i], 2nd picks from [i + 1, j)
                             if 2nd picks nums[i + 1], 1st picks from nums[i + 2, j)
                             if 2nd picks nums[j - 1], 1st picks from nums[i + 1, j - 1)
        if 1st picks nums[j - 1], 2nd picks from [i, j - 1)
                             if 2nd picks nums[i], 1st picks from nums[i + 1, j - 1)
                             if 2nd picks nums[j - 2], 1st picks from nums[i, j - 2)
        max scores 1st can get =
            max(nums[i] + min(maxscores 1st can get next round),
            nums[j - 1] + min(maxscores 1st can get next round))

* */
public class PredictTheWinner {
    int[][] memo;

    public boolean PredictTheWinner(int[] nums) {

        memo = new int[nums.length][nums.length];
        for (int[] tmp : memo)
            Arrays.fill(tmp, -1);
        int scoreSum = 0;
        for (int num : nums) {
            scoreSum += num;
        }
        int scoreFirst = perdictTheWinnerFrom(0, nums.length - 1, nums);
        return scoreFirst >= scoreSum - scoreFirst;
    }

    private int perdictTheWinnerFrom(int i, int j, int[] nums) {

        if (i > j) {
            return 0;
        }

        if (i == j) {
            return nums[i];
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        memo[i][j] = Math.max(nums[i] + Math.min(perdictTheWinnerFrom(i + 2, j, nums), perdictTheWinnerFrom(i + 1, j - 1, nums)),
                nums[j] + Math.min(perdictTheWinnerFrom(i, j - 2, nums), perdictTheWinnerFrom(i + 1, j - 1, nums)));

        return memo[i][j];
    }
}
