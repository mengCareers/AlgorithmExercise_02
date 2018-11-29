package CompanyOriented.Google.Interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * input: target interval, candidate intervals e.g. [100, 120], [200, 240], [400, 410]
 * output: if target interval can cover range of interval addition
 * rule:
 * interval addition - [100, 120] + [200, 240] = [300, 360]
 */

public class CoverRangeCombinationSum {

    private static Map<String, Boolean> memo;

    public static boolean canCoverRange(int[] target, int[][] ranges) {

        /* Memorization for Top-down DP. */
        memo = new HashMap<>();

        for (int[] range : ranges)
            if (canCover(target, range, ranges))
                return true;

        return false;
    }


    private static boolean canCover(int[] target, int[] range, int[][] ranges) {

        String rangeStr = Arrays.toString(range);
        if (memo.containsKey(rangeStr))
            return memo.get(rangeStr);

        /* Rejection case. */
        if (target[1] - target[0] < range[1] - range[0])
            return false;

        /* Accepting case. */
        if (target[0] <= range[0] && target[1] >= range[1])
            return true;

        for (int[] curRange : ranges) {
            int[] newRange = new int[]{range[0] + curRange[0], range[1] + curRange[1]};
            boolean recurAnswer = canCover(target, newRange, ranges);
            memo.put(Arrays.toString(newRange), recurAnswer);
            if (recurAnswer)
                return true;
        }

        return false;
    }
}

