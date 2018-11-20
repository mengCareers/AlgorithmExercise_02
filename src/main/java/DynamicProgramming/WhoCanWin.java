package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * curDesiredTotal and integersChoosable decide the STATE uniquely.
 * state[integersChoosable][curDesiredTotal] true if the running total can reach or exceed curDesiredTotal with integersChoosable
 * goal state: state[1..maxChoosableInteger][curDesiredTotal]
 * state transition:
 * with integersChoosable and curDesiredTotal currently,
 * I can pick any curInt from integersChoosable,
 * and state becomes state[integersChoosable without curInt][curDesiredTotal - curInt] (the state of opponent)
 * so if I can make one of state[integersChoosable without curInt][curDesiredTotal - curInt] false,
 * I am promised to win
 */
public class WhoCanWin {


    public static void main(String[] args) {

        WhoCanWin inst = new WhoCanWin();
        inst.canIWin(4, 6);
    }


    Map<String, Boolean> memo;

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {

        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        if (((1 + maxChoosableInteger) / 2 * maxChoosableInteger) < desiredTotal) {
            return false;
        }
        memo = new HashMap<>();

        return canIWinFrom(maxChoosableInteger, desiredTotal, new boolean[maxChoosableInteger + 1]);
    }

    private boolean canIWinFrom(int maxChoosableInteger, int curDesiredTotal, boolean[] visited) {

        if (curDesiredTotal <= 0) {
            return false;
        }

        String visitedStr = Arrays.toString(visited);

        if (memo.containsKey(visitedStr)) {
            return memo.get(visitedStr);
        }

        for (int i = maxChoosableInteger; i >= 1; i--) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (!canIWinFrom(maxChoosableInteger, curDesiredTotal - i, visited)) {
                memo.put(visitedStr, true);
                visited[i] = false;
                return true;
            }
            visited[i] = false;
        }

        memo.put(visitedStr, false);
        return false;
    }
}