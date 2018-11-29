package CompanyOriented.Google;

/*
input: s
output: longest palindromic substring in s

It is intuitive to list all the substrings, and pick those palindromic, and get the longest one.
Let's try dividing the problem top-down,
    e.g. cbabc,
                cbabc is palindromic if c == c and bab is palindromic
                bab is palindromic if b == b and a is palindromic
subproblem: str is palindromic if str[s] == str[e] && str.sub(s + 1, e) is palindromic
Let's try implementing it bottom-up to avoid duplicate calculations
    state[s][e] is true if str[s,e] is palindromic

    state[s][e] is true if str[s] == str[e] && str.substring[s + 1][e - 1] true, for   s + 2 <= e ,
                        if str[s] == str[e], for   s + 1 == e,
                        for   s == e

 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String str) {

        int n = str.length();
        boolean[][] state = new boolean[n][n];
        int maxLen = 0;
        int resultS = 0, resultE = 0;

        for (int s = 0; s < n; s++) {
            for (int e = s; e < n; e++) {
                if (s == e) {
                    state[s][e] = true;
                } else if (s + 1 == e) {
                    state[s][e] = str.charAt(s) == str.charAt(e);
                } else if (s + 2 <= e) {
                    state[s][e] = (str.charAt(s) == str.charAt(e)) && (state[s + 1][e - 1]);
                }
                if (state[s][e] && (e - s + 1 >= maxLen)) {
                    maxLen = e - s + 1;
                    resultS = s;
                    resultE = e;
                }
            }
        }

        return str.substring(resultS, resultE + 1);
    }

    /*
    That sounds correct, however,
    (state[s + 1][e - 1]); should be caluculated before state[s][e]
    e.g.s = 1, e = 6
    3 4  dist = 1
    2 5  dist = 3   2 4 dist = 2
    1 6  dist = 5   1 5 dist = 4
     */
    public String longestPalindromeWrong(String str) {

        int n = str.length();
        boolean[][] state = new boolean[n][n];
        int maxLen = 0;
        int resultS = 0, resultE = 0;
        for (int i = 0; i < n; i++) {
            state[i][i] = true;
        }
        for (int s = n - 1; s >= 0; s--) {
            for (int dist = 1; s + dist < n; dist++) {
                int e = s + dist;
                if (s + 1 == e) {
                    state[s][e] = str.charAt(s) == str.charAt(e);
                } else if (s + 2 <= e) {
                    state[s][e] = (str.charAt(s) == str.charAt(e)) && (state[s + 1][e - 1]);
                }
                if (state[s][e] && (e - s + 1 >= maxLen)) {
                    maxLen = e - s + 1;
                    resultS = s;
                    resultE = e;
                }
            }
        }

        return str.substring(resultS, resultE + 1);
    }
}
