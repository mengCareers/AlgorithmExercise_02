package DFS;

public class MinCostToMergeStones {
    public static void main(String[] args) {
        MinCostToMergeStones inst = new MinCostToMergeStones();
        int[] stones = {3, 2, 4, 1};
        inst.mergeStones(stones, 3);
    }

    private int K;
    private int[] preSum; // preSum[i] is sum of stones[0] to stones[i].

    public int mergeStones(int[] stones, int K) {
        if (stones.length == 1) return 0;
        if ((stones.length - 1) % (K - 1) != 0) return -1;
        this.K = K;

        preSum = new int[stones.length + 1];
        buildPreSum(stones, K);

        int result = mergeStonesRecur(1, stones.length, 1, stones);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void buildPreSum(int[] stones, int K) {
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }
    }

    // Minimum cost merging piles from i to j into targetPiles pile.
    public int mergeStonesRecur(int i, int j, int targetPiles, int[] stones) {

        if (i == j) return (targetPiles == 1) ? 0 : Integer.MAX_VALUE;

        if (targetPiles == 1) return preSum[j] - preSum[i - 1] + mergeStonesRecur(i, j, K, stones);

        int minCost = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            minCost = Math.min(mergeStonesRecur(i, k, targetPiles - 1, stones) + mergeStonesRecur(k + 1, j, 1, stones), minCost);
        }
        return minCost;
    }
}
