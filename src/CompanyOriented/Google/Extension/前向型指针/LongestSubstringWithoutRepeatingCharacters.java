package CompanyOriented.Google.Extension.前向型指针;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters inst = new LongestSubstringWithoutRepeatingCharacters();
        inst.lengthOfLongestSubstring("pwwkew");
    }

    public int lengthOfLongestSubstring(String s) {

        int[] map = new int[256];
        int i = 0, j = 0, n = s.length(), result = 0;

        for (; i < n; i++) {
            while (j <= n) {
                if (isUnique(map)) {
                    result = Math.max(result, j - 1 - i + 1);
                    if (j < n)
                        map[s.charAt(j)]++;
                    j++;
                } else {
                    break;
                }
            }
            map[s.charAt(i)]--;
        }

        return result;
    }

    private boolean isUnique(int[] map) {

        for (int tmp : map) {
            if (tmp > 1) {
                return false;
            }
        }
        return true;
    }
}
