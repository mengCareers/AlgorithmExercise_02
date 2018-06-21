package DynamicProgramming;

/**
 Problem : Find the length of the longest valid parentheses substring in s[0, slen - 1].
 State : dp[i], length of the longest valid parenthese substring in s[0, i] ending at s[i].
 Aim State : max(dp[i]), 0 <= i < slen
 State Transfer :

 For s[i],
 if s[i] is '(', dp[i] will be 0.
 else,
 dp[i] will be dp[i - 2] + 2 when s[i - 1] = '('
 i.e. ...()
 i
 dp[i] will be dp[i - 1] + dp[i - dp[i - 1] - 2]+ 2 when s[i-1] = ')' && s[i - dp[i - 1] - 1] = '('
 i.e. ...))
 i
 e.g.  ()(() )      j = i - dp[i - 1] - 1
 j   i

 We keep track of the maximum dp[i] on the fly.
 */
public class LongestValidParenthesis {
    /**
     * Find the length of the longest valid parentheses substring in s[0, slen - 1]
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxVal = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = ((i >= 2) ? dp[i - 2] : 0) + 2;
                } else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1] - 2) >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
            }
            maxVal = Math.max(maxVal, dp[i]);
        }
        return maxVal;
    }
}
