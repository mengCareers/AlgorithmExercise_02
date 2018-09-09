package Google.Extension.前向型指针;

public class LengthOfLongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        int i = 0, j = 0, result = Integer.MIN_VALUE, n = s.length();
        int[] map = new int[256];

        for (; i < n; i++) {
            while (j <= n && getDistinct(map) <= k) {
                result = Math.max(result, j - 1 - i + 1);
                if (j < n) {
                    map[s.charAt(j)]++;
                }
                j++;
            }
            map[s.charAt(i)]--;
        }

        return result == Integer.MIN_VALUE ? 0 : result;
    }

    private int getDistinct(int[] map) {

        int cnt = 0;
        for (int tmp : map) {
            if (tmp > 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
