package DynamicProgramming;

/**
 * input : total amount, denominations
 * output: # of ways that make up amount
 State:
 dp[i] as the number of combinations that make up total amount i for 0 <= i <= amount
 Final State:
 dp[amount]
 State Transformation:
 dp[i] = dp[i - coins[0]] + dp[i - coins[1]] + ... + dp[i - coins[coins.length - 1]] if i - coins[0] >= 0
 Please note that the outer loop should be about coins, while the inner loop should be about amount. Or else, there may be duplicates in the result, e.g. for input: amount = 5, coins = [1, 2, 5], it counts [2, 2, 1] and [2, 1, 2] as two different combinations, so it will return 9 rather than 5. All in all, the order of coins doesn't matterin this case, so we set it as the outer loop.
 */


public class CoinChangeII {
    public static void main(String[] args) {
        CoinChangeII inst = new CoinChangeII();
        int amount = 5;
        int[] coins = {1, 2, 5};
        int ans = inst.change(amount, coins);
        System.out.println(ans);
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int j = 0; j < coins.length; j++) {
            for (int i = 1; i <= amount; i++) {
                if (i - coins[j] >= 0) {
                    dp[i] += dp[i - coins[j]];
                }
            }
        }
        return dp[amount];
    }
}
