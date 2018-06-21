package DynamicProgramming;

/**
 * input: coins of different denominations, total amount
 * output: the fewest # of coins needed to make up the amount
 * OR -1
 * IS IT GREEDY?
 * NOT SURE. LET'S TRY DYNAMIC PROGRAMMING.
 * State: dp[i] as the minimum # of coins needed to total amount i, for 0 <= i <= amount
 * Final State: dp[amount]
 * State Transformation:
 * Standing at current stage, dp[i],
 * we can take any one of coins, coin
 * dp[i] = max(dp[i - coins[0]] + 1, dp[i - coins[1]] + 1 .. dp[i - coins[coins.length - 1]] + 1);
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int ci = 0; ci < coins.length; ci++) {
                if (i - coins[ci] >= 0 && dp[i - coins[ci]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[ci]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
