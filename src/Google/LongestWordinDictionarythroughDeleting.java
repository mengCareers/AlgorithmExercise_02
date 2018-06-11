package Google;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * input : s, dictionary of strings
 * output:the longest string in dict can be fromed by deleting chars of s
 * <p>
 * abpcplea,backtrack
 * MENTOR: CAN WE TRY THE LONGEST FROM D?
 Since the size of the dictionary and the length of all the strings are not too large, we may start with the longest word in the dictionary.
 We may sort the words in the dictionary by their lengths decreasingly. Please note that if some words share the same length, we sort them in the smallest lexicographical order by o1.compareTo(o2).
 The utility method canForm(char[] s, char[] word) will return true if word be formed by deleting some characters of s.
 The complete code in Java is as below:
 */
public class LongestWordinDictionarythroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o2.length() == o1.length()) {
                    return o1.compareTo(o2);
                }
                return o2.length() - o1.length();
            }
        });
        char[] sArr = s.toCharArray();
        for (String word : d) {
            if (canForm(sArr, word.toCharArray())) {
                return word;
            }
        }
        return "";
    }

    private static boolean canForm(char[] s, char[] word) {
        int wi = 0, si = 0;
        while (wi < word.length && si < s.length) {
            if (word[wi] == s[si]) {
                wi++;
                si++;
            } else {
                si++;
            }
        }
        return wi == word.length;
    }
}
