package CompanyOriented.Google;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 We need to know the frequencies of characters in real time, so we build a map (key : characters in the current window, value : their corresponding frequencies in the current window).
 A window is a candidate substring bounded by pointers left and right. It is valid only if there are at most K distinct characters in the window, i.e., map.keySet().size() <= k. When it is not valid, we keep forwarding the left pointer and updating the map until it is valid.
 We keep track of maxVal on the fly.
 */
public class LongestSubstrAtMostKDistinctCh {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        char[] ch = s.toCharArray();
        int left = 0, right = 0;
        int maxVal = 0;
        for (; right < ch.length; right++) {
            map.put(ch[right], map.getOrDefault(ch[right], 0) + 1);
            while (map.keySet().size() > k) {
                map.put(ch[left], map.get(ch[left]) - 1);
                if (map.get(ch[left]) == 0) {
                    map.remove(ch[left]);
                }
                left++;
            }
            maxVal = Math.max(maxVal, right - left + 1);
        }
        return maxVal;
    }

    public static void main(String[] args) {
        LongestSubstrAtMostKDistinctCh inst = new LongestSubstrAtMostKDistinctCh();
        inst.lengthOfLongestSubstringKDistinct("eceba", 2);
    }
}
