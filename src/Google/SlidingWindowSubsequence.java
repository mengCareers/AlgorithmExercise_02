package Google;

import java.util.Arrays;

/**
 * Minimum Window Subsequence
 * find W (substring of S)
 * such that T is subsequence of W
 * order matters
 * e.g. S = "abcdebdde", T = "bde"
 * bcde
 * bdde
 State:
 dp[i][j] = x indicates that T[0..j] is subsequence of S[x..i]
 Final(Aim) State:
 min(i - dp[i][T.length() - 1] + 1) for 0 <= i <= S.length() - 1
 State Transfer:
 If S[i] equals to T[j], dp[i][j] = max(dp[x][j - 1]) for 0 <= x <= i - 1
 */
public class SlidingWindowSubsequence {
    public String minWindow(String S, String T) {
        int[][] dp = new int[S.length()][T.length()];
        for (int[] tmp : dp) {
            Arrays.fill(tmp, -1);
        }
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(0)) {
                dp[i][0] = i;
            }
        }
        for (int j = 1; j < T.length(); j++) {
            int tmp = -1;
            for (int i = 0; i < S.length(); i++) {
                if (S.charAt(i) == T.charAt(j)) {
                    dp[i][j] = tmp;
                }
                tmp = Math.max(tmp, dp[i][j - 1]);
            }
        }
        /* The same function as the loop above, however, will get TLE
        for (int i = 0; i < S.length(); i++) {
            for (int j = 1; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    for (int x = 0; x <= i - 1; x++) {
                        dp[i][j] = Math.max(dp[i][j], dp[x][j - 1]);
                    }
                }
            }
        }
        */
        int minLength = Integer.MAX_VALUE, minStart = -1;
        for (int i = 0; i < S.length(); i++) {
            if (i - dp[i][T.length() - 1] + 1 < minLength) {
                if (dp[i][T.length() - 1] != -1) {
                    minLength = i - dp[i][T.length() - 1] + 1;
                    minStart = dp[i][T.length() - 1];
                }
            }
        }
        return minStart == -1 ? "" : S.substring(minStart, minStart + minLength);
    }
}
