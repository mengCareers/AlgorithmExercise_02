package Array;

import java.util.HashMap;
import java.util.Map;

public class MostStonesRmvWithSameRowOrCol {
    public static void main(String[] args) {
        MostStonesRmvWithSameRowOrCol inst = new MostStonesRmvWithSameRowOrCol();
        int[][] stones = {{0, 1}, {1, 2}, {1, 3}, {3, 3}, {2, 3}, {0, 2}};
        System.out.println(inst.removeStones(stones));
    }

    public int removeStones(int[][] stones) {
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        for (int[] stone : stones) {
            int x = stone[0];
            int y = stone[1];
            row.put(x, row.getOrDefault(x, 0) + 1);
            col.put(y, col.getOrDefault(y, 0) + 1);
        }

        int move = 0;
        for (int[] stone : stones) {
            // If share same row or col
            int x = stone[0];
            int y = stone[1];
            if (row.get(x) > 1 || col.get(y) > 1) {
                move++;
                row.put(x, row.get(x) - 1);
                col.put(y, col.get(y) - 1);
            }
        }
        return move;
    }
}
