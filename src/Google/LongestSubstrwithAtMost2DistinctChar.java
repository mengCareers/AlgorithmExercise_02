package Google;

import java.util.HashMap;
import java.util.Map;

/**
 * output : length of t such that t is longest substr with at most 2 distinct char
 * can we try sliding window
 * size of n such that two distinct
 * how do we check two distinct, map.keySet().size()
 * e.g. eceba
 *         lr
 *
 *  c 1
 *  b 1
 *  a 1
 */
public class LongestSubstrwithAtMost2DistinctChar {
    public static void main(String[] args) {
        LongestSubstrwithAtMost2DistinctChar inst = new LongestSubstrwithAtMost2DistinctChar();
        int len = inst.lengthOfLongestSubstrTwoDistinct("eceba");
        System.out.println(len);
    }

    public int lengthOfLongestSubstrTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chArr = s.toCharArray();
        int maxVal = 0;
        int l = 0, r = 0;
        while (r < s.length()) {
            map.put(chArr[r], map.getOrDefault(chArr[r], 0) + 1);
            while (map.size() > 2) {
                maxVal = Math.max(maxVal, r - l);
                map.put(chArr[l], map.get(chArr[l]) - 1);
                if (map.get(chArr[l]) == 0) {
                    map.remove(chArr[l]);
                }
                l++;
            }
            r++;
        }
        return maxVal;
    }
}
