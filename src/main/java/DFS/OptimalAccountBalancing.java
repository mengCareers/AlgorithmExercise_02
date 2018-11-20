package DFS;

import java.util.HashMap;
import java.util.Map;

/*
 * 465. Optimal Account Balancing
 * */
public class OptimalAccountBalancing {
    public int minTransfers(int[][] transactions) {

        int giver, taker, debtAmount;
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] transaction : transactions) {
            giver = transaction[0];
            taker = transaction[1];
            debtAmount = transaction[2];
            map.put(giver, map.getOrDefault(giver, 0) - debtAmount);
            map.put(taker, map.getOrDefault(taker, 0) + debtAmount);
        }

        int[] debts = new int[map.size()];
        int di = 0;
        for (int person : map.keySet()) {
            debts[di++] = map.get(person);
        }

        return minTransfersFrom(debts, 0);
    }

    private int minTransfersFrom(int[] debts, int curId) {

        while (curId < debts.length && debts[curId] == 0) {
            curId++;
        }

        if (curId == debts.length) {
            return 0;
        }

        int minimumTransfers = Integer.MAX_VALUE;

        for (int i = curId + 1; i < debts.length; i++) {
            if (debts[i] * debts[curId] < 0) {
                debts[i] += debts[curId];
                minimumTransfers = Math.min(minimumTransfers, 1 + minTransfersFrom(debts, curId + 1));
                debts[i] -= debts[curId];
            }
        }

        return minimumTransfers;
    }
}
