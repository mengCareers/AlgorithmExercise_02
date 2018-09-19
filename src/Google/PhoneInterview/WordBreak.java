package Google.PhoneInterview;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(wordDict);

        return wordBreakFrom(s, wordSet);
    }

    private boolean wordBreakFrom(String s, Set<String> wordDict) {
        if (0 == s.length()) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            String wordCandidate = s.substring(0, i);
            if (wordDict.contains(wordCandidate) && wordBreakFrom(s.substring(i), wordDict)) {
                return true;
            }
        }
        return false;
    }
}
