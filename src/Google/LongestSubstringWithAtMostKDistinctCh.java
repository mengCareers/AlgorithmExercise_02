package Google;

/**
 * length of th longest substring T
 * that contains at most k distinct chs
 * e.g. eceba, k = 2
 * 3
 * eceba
 * l r
 * sliding window
 * <p>
 * start state, countDif = 0,
 * end state, countDif = k,
 * aim state, when countDif = k, get max (r - l + 1)
 * transition function, l and r pointer maintain k
 * * l = 0, r move step by step
 * * if k, get max(r - l + 1)
 * * if < k, r++ until == k
 * * if > k. l++ until == k
 */
public class LongestSubstringWithAtMostKDistinctCh {
    public static void main(String[] args) {
        LongestSubstringWithAtMostKDistinctCh inst = new LongestSubstringWithAtMostKDistinctCh();
        inst.lengthOfLongestSubstringKDistinct("eceba", 2);
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int l = 0, cntDif = 0;
        int[] map = new int[256];
        int result = 0;

        for (int r = 0; r < s.length(); r++) {
            map[s.charAt(r)]++;
            if (map[s.charAt(r)] == 1) {
                cntDif++;
            }
            if (cntDif <= k) {
                result = Math.max(result, r - l + 1);
            }
            while (cntDif > k) {
                map[s.charAt(l)]--;
                if (map[s.charAt(l)] == 0) {
                    cntDif--;
                }
                l++;
            }
        }
        return result;
    }
}
