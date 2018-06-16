package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * input : S, string of digits
 * output: list of Fibonacci-like sequence split from S
 * F[i] + F[i + 1] = F[i + 2]
 * list all of the possibilities
 * e.g.123456579
 * 1   12  123 12345
 * 2 23
 * <p>
 * state: dp[i] true if 0..i is Fib-like
 * final state: dp[slen - 1]
 * it is hard to find the rule for state transformation, so we try listing all possibilities
 * <p>
 * start state: curIdx = 0, result is empty
 * final state: curIdx = slen && result.size() >= 3
 * <p>
 * state transformation:
 * beginning at curIdx, which equals to its previous step's end position + 1,
 * we can choose an ending from [curIdx, s.length() - 1]
 * we try each possible ending (that satisfies Fib with the previous two elements) to see if it can reach the final state
 */
public class SplitArrayIntoFibonacciSequence {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        splitIntoFibonacciFrom(0, result, S); // start state
        return result;
    }

    private boolean splitIntoFibonacciFrom(int curIdx, List<Integer> result, String S) {
        if (curIdx == S.length() && result.size() >= 3) { // end state (base cases)
            return true;
        }
        for (int i = curIdx; i <= S.length() - 1; i++) {
            if (S.charAt(curIdx) == '0' && i > curIdx) {
                break;
            }
            long num = Long.valueOf(S.substring(curIdx, i + 1));
            if (num > Integer.MAX_VALUE) {
                break;
            }
            if (result.size() <= 1 || num == (long) result.get(result.size() - 1) + (long) result.get(result.size() - 2)) {
                result.add((int) num);
                if (splitIntoFibonacciFrom(i + 1, result, S)) {
                    return true;
                }
                result.remove(result.size() - 1);
            }
        }
        return false;
    }
}
