package DFS;

/**
 * wants us to find # of ways to do sth w/o giving specific steps. dp may help.
 * <p>
 * state[i][j] as minimal turns we need for s[i] to s[j]
 * state[i][i]  = 1
 * state[i][i+1] = 1 if s[i] == s[i+1]
 * state[i][i+1] = 2 if s[i] != s[i+1]
 * state[start][start+len] max = len + 1
 * we can further the divide into substrings, [start, start + k] [start + k + 1, start+len]
 */
public class StrangePrinter {

    public static void main(String[] args) {
        StrangePrinter inst = new StrangePrinter();
        inst.strangePrinter("abcba");
    }


    private String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public int strangePrinter(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        s = removeDuplicates(s);

        int n = s.length();
        int[][] state = new int[n][n];

        for (int i = 0; i < n; i++) {
            state[i][i] = 1;
            if (i < n - 1) {
                state[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 1 : 2;
            }
        }

        for (int dist = 2; dist < n; dist++) {
            for (int start = 0; start + dist < n; start++) {
                System.out.println(" start: " + start + " end: " + (start + dist));
                state[start][start + dist] = dist + 1;
                for (int k = 0; k < dist; k++) {
                    int temp = state[start][start + k] + state[start + k + 1][start + dist];
                    System.out.println(" (" + start + ", " + (start + k) + ") (" + (start + k + 1) + "," + (start + dist) + ")");
                    state[start][start + dist] = Math.min(state[start][start + dist],
                            s.charAt(start + k) == s.charAt(start + dist) ? temp - 1 : temp);
                }
            }
        }

        return state[0][n - 1];
    }
}
