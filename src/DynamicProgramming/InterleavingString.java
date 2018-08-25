package DynamicProgramming;

/*
true if s3 is combination of s1, s2
state(i, j, i+j+1) can identify a state that s1[0..i], s2[0..j] can build s3[0..i+j] or not
state(n1-1, n2-1, n1+n2-1) is goal
state transition:
state(i, j, i+j+1) = state(i - 1, j, i+j) && (s3[i + j - 1] == s1[i])   ||
                   state(i, j - 1, i+j - 1) && (s3[i + j - 1] == s2[j])

 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {

        int len1 = s1.length(), len2 = s2.length();
        if (len1 + len2 != s3.length()) {
            return false;
        }
        boolean[][] state = new boolean[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    state[0][0] = true;
                } else if (i == 0) {
                    state[0][j] = state[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
                } else if (j == 0) {
                    state[i][0] = state[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
                } else {
                    state[i][j] = (state[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1))) || (state[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)));
                }
            }
        }

        return state[len1][len2];
    }
}
