package 算法竞赛入门经典;

/*
还需要凑足的每种面值 AS NODE
START NODE: target
END NODE: 0
Currently at state[i], if pick j next, at state[i - Vj]
+ IF state[i] AS 去凑足i最少要多少枚
state[i] = min(state[i - Vj] + 1)
+ IF state[i] AS 去凑足i最多要多少枚
state[i] = max(state[i - Vj] + 1)
 */
public class CoinChange {

    public void minAndMaxCoinChange(int[] V, int target) {
        int n = V.length;
        int[] minState = new int[n + 1];
        int[] maxState = new int[n + 1];
        minState[0] = maxState[0] = 0;
        for (int i = 1; i <= target; i++) {
            minState[i] = Integer.MAX_VALUE;
            maxState[i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= V[j]) {
                    minState[i] = Math.min(minState[i], minState[i - V[j]] + 1);
                    maxState[i] = Math.max(maxState[i], maxState[i - V[j]] + 1);
                }
            }
        }

    }

}
