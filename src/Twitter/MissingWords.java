package Twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MissingWords {
    public static void main(String[] args) {
        MissingWords inst = new MissingWords();
        List<String> result = inst.getMissingWords("a b b c", "a b c");
        System.out.println(result);
    }


    private List<String> getMissingWords(String s, String t) {

        List<String> result = new ArrayList<>();
        String[] sArr = s.split(" ");
        String[] res = getLCSubsequence_0(sArr, t.split(" "), new ArrayList<>()).trim().split(" ");
        for (String r : res) {
            System.out.println(r);
        }
        List<String> list = Arrays.asList(res);
        int si = 0, li = 0;
        while (si < sArr.length && li < list.size()) {
            if (sArr[si].equals(list.get(li))) {
                si++;
                li++;
            }
            else {
                result.add(sArr[si]);
                si++;
            }
        }

        return result;
    }

    private String getLCSubsequence_0(String[] s, String[] t, List<String> list) {

        int slen = s.length, tlen = t.length;
        String[][] state = new String[slen + 1][tlen + 1];
        for (String[] tmp : state)
            Arrays.fill(tmp, "");
        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= tlen; j++) {
                if (s[i - 1].equals(t[j - 1])) {
                    state[i][j] = state[i - 1][j - 1] + " " + s[i - 1];
                } else {
                    state[i][j] = (state[i - 1][j].length() > state[i][j - 1].length()) ?
                            state[i - 1][j] : state[i][j - 1];
                }
            }
        }
        return state[slen][tlen];
    }

}
