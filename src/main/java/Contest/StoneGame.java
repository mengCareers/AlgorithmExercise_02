package Contest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StoneGame {

    int[][] memo;

    public boolean stoneGame(int[] piles) {

        int sum = 0, n = piles.length;
        initMemo(n);
        for (int num : piles)
            sum += num;
        int playerOneScore = getMaxScore(piles, 0, n);

        return playerOneScore >= sum - playerOneScore;
    }

    private void initMemo(int n) {

        memo = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                memo[i][j] = -1;
            }
        }
    }

    private int getMaxScore(int[] piles, int i, int j) {

        if (i >= j) {
            return 0;
        }
        if (i + 1 == j) {
            return piles[i];
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int result = Math.max(piles[i] + Math.min(getMaxScore(piles, i + 2, j), getMaxScore(piles, i + 1, j - 1)),
                piles[j - 1] + Math.min(getMaxScore(piles, i, j - 2), getMaxScore(piles, i + 1, j - 1)));
        memo[i][j] = result;

        return result;
    }
    
}