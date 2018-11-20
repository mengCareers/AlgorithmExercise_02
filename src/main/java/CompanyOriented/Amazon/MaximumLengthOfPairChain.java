package CompanyOriented.Amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * state: state[i] as maximum length of pair chain ends at i
 * final state: max(state[i])
 * state transition: if pairs[i-1][1] < pairs[i][0], state[i] = state[i - 1] + 1
 * input: [1,2] [2,3] [3,4]
 */
public class MaximumLengthOfPairChain {
    public int findLongestChain(int[][] pairs) {
        int longestChain = 0;
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] state = new int[pairs.length];
        Arrays.fill(state, 1);
        for (int i = 1; i < state.length; i++) {
            if (pairs[i - 1][1] < pairs[i][0]) {
                state[i] = state[i - 1] + 1;
                longestChain = Math.max(longestChain, state[i]);
            }
        }
        return longestChain;
    }
}
