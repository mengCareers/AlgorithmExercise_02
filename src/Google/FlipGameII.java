package Google;

/**
 * input : s containing only + and -
 * flip ++ into --
 * output: true if the 1st can guarantee a win
 * <p>
 * ++++
 tmp stores the flipped s for each round.
 For the current round, we flip s to tmp in all possible ways, and our enemy will take tmp as the input and flip it in all possible ways.. Recursion exists, that is,

 for every possible tmp,
 if (!canWin(tmp))
 return true;

 As the graph below showed,

 ++++
 /     \     \
 --++  +--+  ++--
 |             |
 ----        ----
 we are promised to win following the second path (++++, +--+).
 */
public class FlipGameII {
    public static void main(String[] args) {
        String s = "aa";
        System.out.println(s.substring(1) + ",");
        System.out.println(s.substring(2) + ",");
    }

    public boolean canWin(String s) {
        String tmp = "";
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '+' && s.charAt(i) == '+') {
                tmp = s.substring(0, i - 1) + "--" + s.substring(i + 1);
                if (!canWin(tmp)) {
                    return true;
                }
            }
        }
        return false;
    }
}
