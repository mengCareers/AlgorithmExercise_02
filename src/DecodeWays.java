/**
 The problem is to figure out the number of ways to decode s(0, slen-1).
 State : dp[], number of ways to decode s(0, slen-1)
 Aim State : dp[slen], 1-based
 State Transfer : dp[i] = dp[i-1] + dp[i-2]

 Since dp[] is 1-based, dp[0] doesn't make sense. In case it fails the equation for dp[1], we set it to 1.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) { // index
            int tmp = s.charAt(i - 1) - '0';
            if (tmp != 0) {
                dp[i] += dp[i - 1];
            }
            if (i == 1) {
                continue;
            }
            String tmpStr = s.substring(i - 2, i);
            if (tmpStr.charAt(0) == '0') {
                continue;
            }
            tmp = Integer.valueOf(tmpStr);
            if (tmp >= 1 && tmp <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
