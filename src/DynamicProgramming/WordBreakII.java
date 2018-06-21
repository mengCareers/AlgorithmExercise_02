package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordBreakII {
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (!isBreakable(s, wordDict)) {
            return new ArrayList<>();
        }
        List<List<String>> resList = new ArrayList<>();
        wordBreakFrom(s, new HashSet<String>(wordDict), 0, new ArrayList<>(), resList);
        List<String> result = new ArrayList<>();
        for (List<String> list : resList) {
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str).append(" ");
            }
            result.add(sb.toString().trim());
        }
        return result;
    }

    private void wordBreakFrom(String s, HashSet<String> wordDict, int start, List<String> curList, List<List<String>> allList) {
        if (start == s.length()) {
            allList.add(new ArrayList<>(curList));
            return;
        }
        for (int spacePos = start + 1; spacePos <= s.length(); spacePos++) {
            if (wordDict.contains(s.substring(start, spacePos))) {
                curList.add(s.substring(start, spacePos));
                wordBreakFrom(s, wordDict, spacePos, curList, allList);
                curList.remove(curList.size() - 1);
            }
        }
    }

    public boolean isBreakable(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
