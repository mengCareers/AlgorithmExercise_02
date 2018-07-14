package DynamicProgramming;

/**
 state: state[i], minimum # of coins sum up to amount i
 start state: state[0]
 end state: state[amount]
 aim state: state[amount]
 state transition: min(state[i - coins[j]] + 1) = state[i]
  */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] state = new int[amount + 1];
        state[0] = 0;
        for (int i = 1; i <= amount; i++) {
            state[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0 && state[i - coins[j]] != Integer.MAX_VALUE)
                    state[i] = Math.min(state[i], state[i - coins[j]] + 1);
            }
        }
        return state[amount] == Integer.MAX_VALUE ? -1 : state[amount];
    }
}
