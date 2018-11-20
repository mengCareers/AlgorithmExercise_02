package DynamicProgramming;

/**
 * LongestValidParenthesis
 * input: s
 * output: maximum length of the valid parentheses substring
 * state: state[i] as maximum length of the valid parentheses substring ending at s[i]
 * start state: state[0]
 * end state: state[slen-1]
 * aim state: max(state[i]) 0 <= i <= slen-1
 * state transition: state[i - ] = state[i]
 * <p>
 * e.g.
 * if s[i] = (, state[i] = 0
 * if s[i] = ),
 * if s[i-1] = (, state[i] = state[i - 2] + 2
 * if s[i-1] = ) && s[i - state[i - 1] - 1] = (, state[i] = state[i - state[i-1]- 2] + state[i - 1] + 2
 * e.g. ((..))
 *      j    i
 * j = i - state[i-1]- 1
 */
public class LongestValidParenthesis {
    public int longestValidParentheses(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        int[] state = new int[s.length()];
        int maxVal = 0;
        for (int i = 1; i <= s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                state[i] = 0;
            } else {
                if (s.charAt(i - 1) == '(') {
                    state[i] = i - 2 >= 0 ? state[i - 2] : 0 + 2;
                } else {
                    if (i - state[i - 1] - 1 >= 0 && s.charAt(i - state[i - 1] - 1) == '(')
                        state[i] = i - state[i - 1] - 2 >= 0 ? state[i - state[i - 1] - 2] : 0 + 2 + state[i - 1];
                }
                maxVal = Math.max(maxVal, state[i]);
            }
        }
        return maxVal;
    }
}
