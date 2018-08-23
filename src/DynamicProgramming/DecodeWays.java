package DynamicProgramming;

/**
 * state[i]: # of ways to decode s[0..i]
 * start state: state[0]
 * end state: state[slen-1]
 * aim state: state[slen-1]
 * state transition: state[i] = state[i - 1] + state[i - 2]
 */
public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays inst = new DecodeWays();
        int result = inst.numDecodings("11");
        System.out.println(result);
    }

    public int numDecodings(String s) {

        if (s.length() == 0) {
            return 0;
        }

        if (s.length() == 1) {
            return s.equals("0") ? 0 : 1;
        }

        int n = s.length();
        int[] state = new int[n];
        state[0] = (s.charAt(0) == '0') ? 0 : 1;
        if (s.charAt(1) != '0') {
            state[1] = state[0];
        }
        if (s.charAt(0) != '0' && Integer.valueOf(s.substring(0, 2)) <= 26) {
            state[1]++;
        }

        for (int i = 2; i < n; i++) {
            if (s.charAt(i) != '0') {
                state[i] += state[i - 1];
            }
            if (s.charAt(i - 1) != '0' && Integer.valueOf(s.substring(i - 1, i + 1)) <= 26) {
                state[i] += state[i - 2];
            }
        }

        return state[n - 1];
    }

}
