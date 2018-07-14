package DynamicProgramming;

/**
 state[i]: # of ways to decode s[0..i]
 start state: state[0]
 end state: state[slen-1]
 aim state: state[slen-1]
 state transition: state[i] = state[i - 1] + state[i - 2]*/
public class DecodeWays {
    public static void main(String[] args) {
        DecodeWays inst = new DecodeWays();
        int result = inst.numDecodings("11");
        System.out.println(result);
    }

    public int numDecodings(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int[] state = new int[s.length()];
        state[0] = 1;
        state[1] = 2;
        int tmp = 0;
        String tmpStr = "";
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) != '0')
                state[i] += state[i - 1];
            tmpStr = s.substring(i - 1, i + 1);
            if (tmpStr.charAt(0) == '0')
                continue;
            tmp = Integer.valueOf(tmpStr);
            if (tmp >= 1 && tmp <= 26)
                state[i] += state[i - 2];
        }
        return state[s.length() - 1];
    }

}
