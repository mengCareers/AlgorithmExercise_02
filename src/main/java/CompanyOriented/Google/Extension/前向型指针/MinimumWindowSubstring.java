package CompanyOriented.Google.Extension.前向型指针;

// O(256n)
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        int i = 0, j = 0, minLen = Integer.MAX_VALUE, slen = s.length(), tlen = t.length();
        String result = "";
        int[] smap = new int[256], tmap = new int[256];
        char[] tarr = t.toCharArray();
        for (char ch : tarr) {
            tmap[ch]++;
        }

        for (; i < slen; i++) {
            while (j <= slen && !ifContains(smap, tmap)) {
                if (j < slen)
                    smap[s.charAt(j)]++;
                j++;
            }
            if (ifContains(smap, tmap)) {
                if (j - i < minLen) {
                    minLen = j - i;
                    result = s.substring(i, j);
                }
            }
            smap[s.charAt(i)]--;
        }
        return result;
    }

    private boolean ifContains(int[] smap, int[] tmap) {

        for (int i = 0; i < 256; i++) {
            if (tmap[i] > 0 && smap[i] < tmap[i]) {
                return false;
            }
        }
        return true;
    }
}
