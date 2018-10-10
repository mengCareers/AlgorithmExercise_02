package CompanyOriented.Google;

/**
 * input : n
 * output: the least # of perfect square numbers which sum to n
 The problem is to get the least number of perfect square numbers which sum to n.

 State :
 dp[i] as the least # of perfect square numbers which sum to i for 1 <= i <= n.
 Final State :
 dp[n] as the least # of perfect square numbers which sum to n
 Transition of State :
 dp[1] = 1;
 dp[2] = dp[2 - 1*1] + 1 = 2;
 dp[3] = dp[3 - 1*1] + 1 = 3;
 dp[4] = min(dp[4 - 1*1] + 1, dp[4 - 2*2] + 1) = 1
 dp[5] = min(dp[5 - 1*1] + 1, dp[5 - 2*2] + 1) = 2
 ..
 dp[9] = min(dp[9 - 1*1] + 1, dp[9 - 2*2] + 1, dp[9 - 3*3] + 1) = ...
 It's inductive that
 dp[i] = min(dp[i - j * j] + 1) for (1 <= j) and (j * j <= i)
 */
public class PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
