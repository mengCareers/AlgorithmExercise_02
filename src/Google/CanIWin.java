package Google;

import java.util.*;

/**
 States of the problem are decided by the numbers the player can shoose from.
 We pick a number num, and the other player pick any one from the rest. To force a win, we need to ensure that any number the other player picked is impossible to make him win the game.
 Thus, we simply list all possibilities and see if there is a valid num using DFS.
 To avoid duplicate calculations, we use a hashmap for memorization.
 Instead of using boolean[] visited to indicate whether a number has been picked, we use int[] visited for quicker serialization in map.
 */
public class CanIWin {

    public static void main(String[] args) {
//        CanIWin inst = new CanIWin();
//        inst.canIWin(10, 11);
        boolean[] arr = {true, false, true};
        System.out.println(Arrays.toString(arr));
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int[] visited = new int[maxChoosableInteger + 1];
        if (desiredTotal < 2 || maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            return false;
        }
        if (sum == desiredTotal) {
            return maxChoosableInteger % 2 == 1;
        }
        return canIWinFrom(maxChoosableInteger, desiredTotal, visited, new HashMap<>());
    }

    private boolean canIWinFrom(int maxChoosableInteger, int desiredTotal, int[] visited, Map<String, Boolean> map) {
        if (desiredTotal <= 0) {
            return false;
        }
        String strVisited = Arrays.toString(visited);
        if (map.containsKey(strVisited)) {
            return map.get(strVisited);
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (visited[i] == 1) {
                continue;
            }
            visited[i] = 1;
            if (!canIWinFrom(maxChoosableInteger, desiredTotal - i, visited, map)) {
                visited[i] = 0;
                map.put(strVisited, true);
                return true;
            }
            visited[i] = 0;
        }
        map.put(strVisited, false);
        return false;
    }
}