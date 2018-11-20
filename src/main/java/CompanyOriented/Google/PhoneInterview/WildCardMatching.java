package CompanyOriented.Google.PhoneInterview;

public class WildCardMatching {
    public static boolean isMatch(String s, String p) {

        int slen = s.length(), plen = p.length();
        boolean[][] state = new boolean[slen + 1][plen + 1]; // state[i][j] is true if s[0..i) matches p[0..j).
        state[0][0] = true;

        for (int j = 1; j <= plen; j++) {
            state[0][j] = (state[0][j - 1]) && (p.charAt(j - 1) == '*');
        }

        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                char pChar = p.charAt(j - 1);
                if (pChar != s.charAt(i - 1)) {
                    if (pChar == '?')
                        state[i][j] = state[i - 1][j - 1];
                    else if (pChar == '*')
                        state[i][j] = state[i][j - 1] || state[i - 1][j];
                } else {
                    state[i][j] = state[i - 1][j - 1];
                }
            }
        }

        return state[slen][plen];
    }
}
