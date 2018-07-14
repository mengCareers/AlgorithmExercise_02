package Google;

import java.util.LinkedList;
import java.util.List;

/**
 * 76. Minimum Window Substring
 e.g. S = "ADOBECODEBANC", T = "ABC"
 ADOBEC
 BECODEBA
 CODEBA
 BANC
 The substrings above are candidates for the result.
 How do we generate these candidates?
 A: Using the Sliding Window technique.

 The variables below help describe the algorithm:
 tMap[] saves characters and their corresponding appearances in t.
 left, right are two ends of the current Sliding Window.
 countMatch indicates number of characters in t that match the current Sliding Window.

 Please note that,
 tMap[sChar]--; no matter if sArr[right] exists in t,
 countMatch++; only if sArr[right] exists in t, i.e., tMap[sChar] + 1 > 0
 tMap[sChar]++; no matter if sArr[left] exists in t,
 countMatch--; only if sArr[left] exists in t, i.e., tMap[sChar] - 1 >= 0
 */
public class SlidingWindowSubstring {
    public String minWindow(String s, String t) {
        int[] tMap = new int[256];
        for (char c : t.toCharArray()) {
            tMap[c]++;
        }
        char[] sArr = s.toCharArray();
        int left = 0, countMatch = 0, minStart = 0, minLength = Integer.MAX_VALUE;
        char sChar = 0;
        for (int right = 0; right < s.length(); right++) {
            sChar = sArr[right];
            tMap[sChar]--;
            if (tMap[sChar] + 1 > 0) {
                countMatch++;
            }
            if (countMatch == t.length()) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    minStart = left;
                }
                sChar = sArr[left];
                tMap[sChar]++;
                if (tMap[sChar] - 1 >= 0) {
                    countMatch--;
                }
                left++;
            }
        }
        return s.substring(minStart, minStart + minLength);
    }
}
