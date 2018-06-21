package Google;

import java.util.Arrays;

/**
 * input : S, T
 * output: minimum window subsequence
 * find the minimum (contiguous) substring W of S,
 * so that T is a subsequence of W
 * return the leftmost
 * e.g. S = "abcdebdde", T = "bde"
 * State : dp[i][j] = k such that T[0..j] is a subsequence of S[k..i]
 * Final State : min(i - dp[i][lenT - 1 ]) 0 <= i <= slen - 1
 * State Transformation :
 * if S[i] = T[j]
 * dp[i][j] = max(dp[si][j - 1])  for all si < i
 */
public class MinimumWindowSubsequence {
    public static void main(String[] args) {
        for (int j = 1; j < 5; j++) {
            for (int i = 1; i < 5; i++) {

                System.out.println(i + " " + j);
            }
        }
    }

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
        int startForMin = -1, minLength = Integer.MAX_VALUE;
        for (int i = 0; i < S.length(); i++) {
            if (dp[i][T.length() - 1] != -1) {
                if (i - dp[i][T.length() - 1] + 1 < minLength) {
                    minLength = i - dp[i][T.length() - 1] + 1;
                    startForMin = dp[i][T.length() - 1];
                }
            }
        }
        return startForMin == -1 ? "" : S.substring(startForMin, startForMin + minLength);
    }
}
