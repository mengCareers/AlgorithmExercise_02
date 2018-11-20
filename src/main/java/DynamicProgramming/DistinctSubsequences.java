package DynamicProgramming;

/**
 * output: number of distinct subsequences of S which equals T
 * state: state[i][j] as number of distinct subsequences of S[0..j] which equals T[0..i]
 * start state: state[0][0]
 * end state: state[tlen-1][slen-1]
 * aim state: max(state[i][j])
 * state transition:
 * state[i][j] = state[i-1][j-1] + state[i][j-1] (if s[j] = t[i])
 * = state[i][j-1] (if s[j] != t[i])
 * e.g. baag
 * j
 * bcg
 * i
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int tLength = t.length(), sLength = s.length();
        int[][] state = new int[tLength + 1][sLength + 1];
        for (int j = 0; j < sLength; j++)
            state[0][j] = 1;
        for (int i = 1; i <= tLength; i++) {
            for (int j = 1; j <= sLength; j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    state[i][j] = state[i - 1][j - 1] + state[i][j - 1];
                } else {
                    state[i][j] = state[i][j - 1];
                }
            }
        }
        return state[tLength][sLength];
    }
}
