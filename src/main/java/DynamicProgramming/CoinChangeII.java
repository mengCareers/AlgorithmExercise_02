package DynamicProgramming;

/**
 state: state[i] as # of ways sum up to i
 start state: state[0]
 end state: state[amount]
 aim state: state[amount]
 state transition: state[i] = sum(state[i - coins[j]])
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
        int[] state = new int[amount + 1];
        state[0] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                state[i] += state[i - coins[j]];
            }
        }
        return state[amount];
    }
}
