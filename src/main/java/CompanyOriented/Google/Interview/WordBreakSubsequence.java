package CompanyOriented.Google.Interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
rather than break target word using words in dictionary
we break target word using subsequence of a word
 */
/*
m1. list subsequences of word in a dictionary and follow the pattern of Word Break (d[,
    dfs, or dp
m2. map trick
m3. greedy
 */
public class WordBreakSubsequence {
    // true if target can be segmented into one or more subsequences of word
    Set<String> subsequences;

    private void getSubsequences(String str, int curIndex, String curResult) {
        if (!curResult.isEmpty())
            subsequences.add(curResult);
        for (int i = curIndex; i < str.length(); i++) {
            curResult += str.charAt(i);
            getSubsequences(str, i + 1, curResult);
            curResult = curResult.substring(0, curResult.length() - 1);
        }
    }

    // m0.
    public int bToA(String A, String B) {
        getSubsequences(A, 0, "");
        return 1;
    }


    public static void main(String[] args) {
        WordBreakSubsequence inst = new WordBreakSubsequence();
        System.out.println(inst.canTargetBreakHash("ABCACAD", "ABCD"));
    }


    // m1.
    public boolean canTargetBreak(String target, String word) {
        subsequences = new HashSet<>();
        getSubsequences(word, 0, "");
        return wordBreak(target, subsequences);
    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        return wordBreakFrom(0, s, wordDict);
    }

    private boolean wordBreakFrom(int curIndex, String s, Set<String> wordDict) {
        if (curIndex == s.length()) {
            return true;
        }
        for (int i = curIndex + 1; i <= s.length(); i++) {
            String wordCandidate = s.substring(curIndex, i);
            if (wordDict.contains(wordCandidate) && wordBreakFrom(i, s, wordDict)) {
                return true;
            }
        }
        return false;
    }


    // m2.
    public int canTargetBreakHash(String target, String word) {

        // map character in word to its index in word
        int wordLength = word.length(), targetLength = target.length(), countWord = 1;
        char[] wordArray = word.toCharArray(), targetArray = target.toCharArray();
        int[] hash = new int[256];
        Arrays.fill(hash, -1);
        for (int i = 0; i < wordLength; i++) {
            hash[wordArray[i]] = i;
        }

        // judge target, if hash[ch] <= hash[prevch], countWord++; if hash[ch] = -1, return -1;
        if (hash[target.charAt(0)] == -1) {
            return -1;
        }
        for (int i = 1; i < targetLength; i++) {
            char ch = targetArray[i];
            char prevch = targetArray[i - 1];
            if (hash[ch] == -1)
                return -1;
            if (hash[ch] <= hash[prevch]) {
                countWord++;
            }
        }
        return countWord;
    }

    // m3.
    public int canTargetBreakGreedy(String target, String word) {

        char[] targetArray = target.toCharArray(), wordArray = word.toCharArray();
        int count = 0, i = 0, targetLength = targetArray.length, wordLength = wordArray.length;
        while (i < targetLength) {
            int j = 0;
            int prev = i;
            while (i < targetLength && j < wordLength) {
                if (wordArray[j] == targetArray[i]) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (prev == i) {
                return -1;
            }
            count++;
        }

        return count;
    }

}
