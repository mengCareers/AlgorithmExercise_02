package Facebook;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordDict);

        Set<Integer> lengthSet = new HashSet<>();
        for (String word : wordDict) {
            lengthSet.add(word.length());
        }

        int n = s.length();
        boolean[] state = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int length : lengthSet) {
                if (i - length + 1 >= 0) {
                    if (i - length >= 0) {
                        if (wordSet.contains(s.substring(i - length + 1, i + 1))) {
                            state[i] = state[i] || state[i - length];
                        }
                    } else {
                        state[i] = state[i] || wordSet.contains(s.substring(0, i + 1));
                    }
                }
            }
        }


        return state[n - 1];
    }
}
