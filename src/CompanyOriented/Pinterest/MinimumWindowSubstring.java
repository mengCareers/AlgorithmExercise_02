package CompanyOriented.Pinterest;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0)
            return "";

        int n = s.length(), j = 0, minLength = Integer.MAX_VALUE;
        String minWindow = "";

        int[] tMap = new int[256], curMap = new int[256];
        for (char ch : t.toCharArray()) {
            tMap[ch]++;
        }

        for (int i = 0; i < n; i++) {
            while (j <= n) {
                if (containsAll(curMap, tMap)) {
                    if (minLength > j - i + 1) {
                        minLength = j - i + 1;
                        minWindow = s.substring(i, j);
                    }
                    break;
                }
                if (j < n)
                    curMap[s.charAt(j)]++;
                j++;
            }
            curMap[s.charAt(i)]--;
        }

        return minWindow;
    }

    private boolean containsAll(int[] sMap, int[] tMap) {
        for (int i = 0; i < 256; i++) {
            if (tMap[i] != 0 && sMap[i] < tMap[i])
                return false;
        }
        return true;
    }
}
