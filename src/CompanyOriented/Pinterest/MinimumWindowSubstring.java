package CompanyOriented.Pinterest;

public class MinimumWindowSubstring {

    /*

     */
    public String minWindow(String s, String t) {

        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length())
            return "";

        int slen = s.length(), numToMatch = t.length(), j = 0, minLen = Integer.MAX_VALUE, resultStart = 0;
        int[] tMap = new int[256];

        for (char ch : t.toCharArray())
            tMap[ch]++;

        for (int i = 0; i < slen; i++) {
            while (j <= slen && numToMatch != 0) {
                if (j < slen) {
                    tMap[s.charAt(j)]--;
                    if (tMap[s.charAt(j)] + 1 > 0)
                        numToMatch--;
                }
                j++;
            }
            if (numToMatch == 0) {
                if (j - i < minLen) {
                    minLen = j - i;
                    resultStart = i;
                }
            }
            tMap[s.charAt(i)]++;
            if (tMap[s.charAt(i)] - 1 == 0)
                numToMatch++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(resultStart, resultStart + minLen);
    }
}
