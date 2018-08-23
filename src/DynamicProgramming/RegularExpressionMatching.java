package DynamicProgramming;

public class RegularExpressionMatching {
    public boolean isMatch(String sStr, String pStr) {

        int slen = sStr.length(), plen = pStr.length();
        char[] s = sStr.toCharArray(), p = pStr.toCharArray();
        boolean[][] state = new boolean[slen + 1][plen + 1];
        state[0][0] = true;
        for (int j = 1; j < plen + 1; j++) {
            if (p[j - 1] == '*') {
                state[0][j] = state[0][j - 2];
            }
        }

        for (int i = 1; i < slen + 1; i++) {
            for (int j = 1; j < plen + 1; j++) {
                if (p[j - 1] == s[i - 1]) {
                    state[i][j] = state[i - 1][j - 1];
                } else if (p[j - 1] == '.') {
                    state[i][j] = state[i - 1][j - 1];
                } else if (p[j - 1] == '*') {
                    if (s[i - 1] != p[j - 2] && p[j - 2] != '.') {
                        state[i][j] = state[i][j - 2];
                    } else {
                        state[i][j] = (state[i][j - 2] || state[i][j - 1] || state[i - 1][j]);
                    }
                }
            }
        }

        return state[slen][plen];
    }
}
