package Facebook;

/**
 * output: # of palindromic substrings
 * e.g. abc
 * 3 = a b c
 * e.g. aaa
 * 6 = a a a aa aa aaa
 * different according to indices
 * state: state[i][j] as true if s[i, j] is palindrome for 0 <= i, j < s.length() [0-based]
 *  * aim state: count true during iteration
 * state transition:
 * standing at i,
 * state[i][j] true if s[j] == s[i] && state[i+1][j-1]
 * e.g. b..b
 * i  j
 * SO CALLED 区间DP
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int cntSubstr = 0;
        boolean[][] state = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            state[i][i] = true;
            cntSubstr++;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int dif = 1; i + dif < s.length(); dif++) {
                int j = i + dif;
                if (dif == 1)
                    state[i][j] = s.charAt(j) == s.charAt(i);
                else if (s.charAt(j) == s.charAt(i) && state[i + 1][j - 1])
                    state[i][j] = true;
                if (state[i][j]) {
                    cntSubstr++;
                }
            }
        }
        return cntSubstr;
    }
}
